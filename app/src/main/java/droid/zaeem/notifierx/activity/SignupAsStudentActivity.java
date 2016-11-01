package droid.zaeem.notifierx.activity;

/**
 * Created by Droid on 6/22/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.helpers.QuickstartPreferences;
import droid.zaeem.notifierx.server.ServerUtilities;
import droid.zaeem.notifierx.services.RegistrationIntentService;


public class SignupAsStudentActivity extends AppCompatActivity {
    private EditText inputRollnumber, inputClass, inputYear, inputName, inputEmail, inputPhone;
    private TextInputLayout inputLayoutRollnumber, inputLayoutClass, inputLayoutYear, inputLayoutName, inputLayoutEmail, inputLayoutPhone;
    private Button btnSignUp;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "SignupAsStudentActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    public static TextView mInformationTextView;
    private LinearLayout progressContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as_student);
        if (CacheModel.getBoolean(Constants.Keys.TOKEN_SENT_TO_SERVER)) {
            finish();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inputLayoutRollnumber = (TextInputLayout) findViewById(R.id.input_layout_rollnumber);
        inputLayoutClass = (TextInputLayout) findViewById(R.id.input_layout_class);
        inputLayoutYear = (TextInputLayout) findViewById(R.id.input_layout_year);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        mInformationTextView = (TextView) findViewById(R.id.informationTextView);
        progressContainer = (LinearLayout) findViewById(R.id.progressContainer);
        progressContainer.setVisibility(View.GONE);
        //  btnSignUp= (Button) findViewById(R.id.btn_signup);


        inputRollnumber = (EditText) findViewById(R.id.input_rollnumber);
        inputClass = (EditText) findViewById(R.id.input_class);
        inputYear = (EditText) findViewById(R.id.input_year);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        btnSignUp = (Button) findViewById(R.id.btn_signup);


        inputRollnumber.addTextChangedListener(new MyTextWatcher(inputRollnumber));
        inputClass.addTextChangedListener(new MyTextWatcher(inputClass));
        inputYear.addTextChangedListener(new MyTextWatcher(inputYear));
        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));

        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));


        handleBroadcastReceiver();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        //setupToolbar();

    }


    private void handleBroadcastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //  SharedPreferences sharedPreferences =PreferenceManager.getDefaultSharedPreferences(context);

                // boolean sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                boolean deviceRegistered = CacheModel.getBoolean(Constants.Keys.DEVICE_REGISTERED);
                String classID="";


                if (deviceRegistered) {
                    if(inputClass.getText().toString().toLowerCase().equals("bscs"))
                    {
                        classID="1";

                    }
                    if(inputClass.getText().toString().toLowerCase().equals("mscs"))
                    {
                        classID="2";
                    }
                    RegisterVisitor registerVisitor = new RegisterVisitor();
                    registerVisitor.execute(inputRollnumber.getText().toString(), classID, inputYear.getText().toString(), inputName.getText().toString(), inputEmail.getText().toString(), inputPhone.getText().toString(), CacheModel.getString(Constants.Keys.GCM_DEVICE_TOKEN), Constants.URLs.SIGNUP_STUDENT);

                } else {
                    //mInformationTextView.setText("Registeration Failed!");
                    // again register when regiteration failed (Extra code)
                    Intent startRegisterationWithGCM = new Intent(getApplicationContext(), RegistrationIntentService.class);
                    startService(startRegisterationWithGCM);
                }
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this screen to get broadcast message
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        // un-register this screen for getting broadcast message
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    private void submitForm() {
        mInformationTextView.setText("");
        if (validateRollnumber() == true && validateClass() == true && validateYear() && validateName() == true && validateEmail() == true && validatePhone() == true) {
            if (checkPlayServices()) {
                CacheModel.putString(Constants.Keys.STUDENT_ROLLNUMBER, inputRollnumber.getText().toString());
                CacheModel.putString(Constants.Keys.STUDENT_CLASS, inputClass.getText().toString());
                CacheModel.putString(Constants.Keys.STUDENT_YEAR, inputYear.getText().toString());
                CacheModel.putString(Constants.Keys.STUDENT_NAME, inputName.getText().toString());
                CacheModel.putString(Constants.Keys.STUDENT_EMAIL, inputEmail.getText().toString());
                CacheModel.putString(Constants.Keys.STUDENT_PHONE, inputPhone.getText().toString());
                progressContainer.setVisibility(View.VISIBLE);

                // Start IntentService to register this application with GCM.
                Intent startRegisterationWithGCM = new Intent(this, RegistrationIntentService.class);
                startService(startRegisterationWithGCM);
            }


        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(), "No Google Play Services Found !", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    private boolean validateRollnumber() {
        if (inputRollnumber.getText().toString().trim().isEmpty() && isValidRollnumber(inputRollnumber.getText().toString())) {
            inputLayoutRollnumber.setError("Error");
            requestFocus(inputRollnumber);
            return false;
        } else {
            inputLayoutRollnumber.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateClass() {
        if (inputClass.getText().toString().trim().isEmpty() && isValidClass(inputClass.getText().toString().toLowerCase())) {
            inputLayoutClass.setError("Error");
            requestFocus(inputClass);
            return false;
        } else {
            inputLayoutClass.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateYear() {
        if (inputYear.getText().toString().trim().isEmpty() && isValidYear(inputYear.getText().toString())) {
            inputLayoutYear.setError("Error");
            requestFocus(inputYear);
            return false;
        } else {
            inputLayoutYear.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty() && isFullname(inputName.getText().toString())) {
            inputLayoutName.setError("Error");
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Error");
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (phone.isEmpty() || !isValidPhone(phone)) {
            inputLayoutPhone.setError("Error");
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidRollnumber(String rollnumber) {
        return !TextUtils.isEmpty(rollnumber) && android.text.TextUtils.isDigitsOnly(rollnumber);
    }

    private static boolean isValidClass(String classID) {
        if ((classID.equals("bscs") || classID.equals("mscs")) && !TextUtils.isEmpty(classID)) {
            return true;
        } else
            return false;

    }

    private static boolean isValidYear(String year) {
        if (year.startsWith("2") && year.length() == 4 && !TextUtils.isEmpty(year)) {
            return true;
        } else
            return false;

    }

    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_rollnumber:
                    validateRollnumber();
                    break;
                case R.id.input_class:
                    validateClass();
                    break;
                case R.id.input_year:
                    validateYear();
                    break;
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    * Register student in background then update UI
    * */

    private class RegisterVisitor extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String result = "";
            int responseCode = 0;
            ServerUtilities serverUtilities = new ServerUtilities();
            responseCode = serverUtilities.registerStudent(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7]);
            if (responseCode == 200) {
                result = "success";
            } else {
                result = "error";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success")) {
                Toast.makeText(getApplicationContext(), "Sign up Successfully!", Toast.LENGTH_LONG).show();
                mInformationTextView.setText("Sign up Successfully!");
                progressContainer.setVisibility(View.GONE);
                CacheModel.putBoolean(Constants.Keys.TOKEN_SENT_TO_SERVER, true);
                CacheModel.putBoolean(Constants.Keys.IS_STUDENT, true);
                Intent startMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startMainActivity);
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong :(", Toast.LENGTH_LONG).show();
                mInformationTextView.setText("Sign up failed!");
                mRegistrationProgressBar.setVisibility(View.GONE);
                progressContainer.setVisibility(View.GONE);
                CacheModel.putBoolean(Constants.Keys.DEVICE_REGISTERED,false);

            }
        }
    }
}

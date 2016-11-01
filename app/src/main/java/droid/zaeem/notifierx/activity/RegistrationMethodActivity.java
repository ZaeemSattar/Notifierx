package droid.zaeem.notifierx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.helpers.Constants;

/**
 * Created by Droid on 1/12/2016.
 */
public class RegistrationMethodActivity extends AppCompatActivity implements View.OnClickListener {
    Button student, visitor;
    Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        CacheModel.activateDatabase(getApplicationContext());
        if(CacheModel.getBoolean(Constants.Keys.TOKEN_SENT_TO_SERVER))
        {
            finish();
        }
        boolean deviceRegistered = CacheModel.getBoolean(Constants.Keys.DEVICE_REGISTERED);
        boolean tokenSentToServer = CacheModel.getBoolean(Constants.Keys.TOKEN_SENT_TO_SERVER);
        if (deviceRegistered == true && tokenSentToServer == true) {
            Intent startMainActivity = new Intent(this, MainActivity.class);
            startActivity(startMainActivity);
        } else {
            setContentView(R.layout.activity_registration_method);
            toolbar=(Toolbar) findViewById(R.id.toolbar) ;
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            student = (Button) findViewById(R.id.student);
            visitor = (Button) findViewById(R.id.visitor);
            student.setOnClickListener(this);
            visitor.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student: {
                Intent intent = new Intent(this, SignupAsStudentActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.visitor: {
                Intent intent = new Intent(this, SignupAsVisitorActivity.class);
                startActivity(intent);
                break;
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

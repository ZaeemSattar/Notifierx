package droid.zaeem.notifierx.services;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import droid.zaeem.notifierx.activity.MainActivity;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.server.ServerUtilities;

/**
 * Created by Droid on 6/25/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if (CacheModel.getBoolean(Constants.Keys.DEVICE_REGISTERED)) {
            CacheModel.putString(Constants.Keys.GCM_DEVICE_TOKEN_NEW, refreshedToken);

            sendRegistrationToServer();

        }


        //Displaying token in logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken);

    }

    private void sendRegistrationToServer() {
        //You can implement this method to store the token on your server
        //Not required for current project

        boolean isStudent = CacheModel.getBoolean(Constants.Keys.IS_STUDENT);
        if (isStudent) {

            RefreshUser refreshUser = new RefreshUser();
            refreshUser.execute(Constants.Keys.STUDENT,CacheModel.getString(Constants.Keys.GCM_DEVICE_TOKEN),CacheModel.getString(Constants.Keys.GCM_DEVICE_TOKEN_NEW), Constants.URLs.REFRESH_USER);

        }
        if (!isStudent) {
            // boolean deviceRegistered = CacheModel.getBoolean(Constants.Keys.DEVICE_REGISTERED);

            RefreshUser refreshUser = new RefreshUser();
            refreshUser.execute(Constants.Keys.VISITOR,CacheModel.getString(Constants.Keys.GCM_DEVICE_TOKEN),CacheModel.getString(Constants.Keys.GCM_DEVICE_TOKEN_NEW), Constants.URLs.REFRESH_USER);


        }

    }

    private class RefreshUser extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String result = "";
            int responseCode = 0;
            ServerUtilities serverUtilities = new ServerUtilities();
            responseCode = serverUtilities.refreshUser(params[0], params[1], params[2], params[3]);
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
                Toast.makeText(getApplicationContext(), "Account Refreshed!", Toast.LENGTH_LONG).show();
                CacheModel.putBoolean(Constants.Keys.TOKEN_SENT_TO_SERVER, true);

            } else {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        }
    }



}

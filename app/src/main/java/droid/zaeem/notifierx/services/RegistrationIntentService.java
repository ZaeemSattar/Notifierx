package droid.zaeem.notifierx.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.helpers.QuickstartPreferences;
import droid.zaeem.notifierx.cachememory.CacheModel;

/**
 * Created by Droid on 6/25/2016.
 */
public class RegistrationIntentService extends IntentService {

    // abbreviated tag name
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        try {

            // Make a call to Instance API
            FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance();
            // String senderId = getResources().getString(R.string.gcm_defaultSenderId);

            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken();
            int len= token.length();
            Log.d(TAG, "FCM Registration Token: " + token);
            CacheModel.putBoolean(Constants.Keys.DEVICE_REGISTERED, true);
            CacheModel.putString(Constants.Keys.GCM_DEVICE_TOKEN, token);

        } catch (Exception e) {

        }

        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}
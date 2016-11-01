package droid.zaeem.notifierx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.helpers.Constants;

/**
 * Created by Droid on 7/16/2016.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(Splash.this, MainActivity.class);
        startActivity(i);


    }
}

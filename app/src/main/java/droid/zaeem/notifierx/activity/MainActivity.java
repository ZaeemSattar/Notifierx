package droid.zaeem.notifierx.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.fragment.DcsAdmissonPortalFragment;
import droid.zaeem.notifierx.fragment.DcsWebsiteFragment;
import droid.zaeem.notifierx.fragment.FragmentDrawer;
import droid.zaeem.notifierx.fragment.HomeFragment;
import droid.zaeem.notifierx.fragment.ImportantFragment;
import droid.zaeem.notifierx.fragment.InboxFragment;
import droid.zaeem.notifierx.fragment.SettingsFragment;
import droid.zaeem.notifierx.fragment.StarredFragment;
import droid.zaeem.notifierx.helpers.Constants;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        if(getIntent().getExtras()!=null)
        {
            String type=getIntent().getExtras().get("type").toString();
            if(type.equals("true"))
            {
                displayView(2);
            }
            else if(type.equals("false"))
            {
                displayView(1);
            }
            else
                displayView(0);

        }
        else
        {
            displayView(0);
        }

    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new InboxFragment();
                title = getString(R.string.title_inbox);
                break;
            case 2:
                fragment = new ImportantFragment();
                title = getString(R.string.title_important);
                break;
            case 3:
                fragment = new StarredFragment();
                title = getString(R.string.title_starred);
                break;
            case 4:
                fragment = new DcsAdmissonPortalFragment();
                title = getString(R.string.title_dcsadmissonportal);
                break;

            case 5:
                fragment = new DcsWebsiteFragment();
                title = getString(R.string.title_dcswebsite);
                break;
            case 6:
                fragment = new SettingsFragment();
                title = getString(R.string.title_settings);
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //moveTaskToBack(true);
        finish();
    }

}
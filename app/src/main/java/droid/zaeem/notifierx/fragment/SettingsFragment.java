package droid.zaeem.notifierx.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;

import java.util.ArrayList;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.activity.DetailedInboxMessageActivity;
import droid.zaeem.notifierx.adapter.MessagesAdapter;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.decorations.DividerItemDecoration;
import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.listeners.RecyclerItemClickListener;


/**
 * Created by Droid on 6/27/2016.
 */
public class SettingsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {


    SwitchCompat switchCompat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        switchCompat = (SwitchCompat) rootView.findViewById(R.id.Switch);
        switchCompat.setOnCheckedChangeListener(this);

        if (CacheModel.getBoolean(Constants.Keys.STOP_NOTIFICATIONS)) {
            switchCompat.setChecked(false);

        }
        if (!CacheModel.getBoolean(Constants.Keys.STOP_NOTIFICATIONS)) {
            switchCompat.setChecked(true);

        }

        return rootView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {

            case R.id.Switch:

                if (isChecked) {
                    CacheModel.putBoolean(Constants.Keys.STOP_NOTIFICATIONS, false);

                }
                if (!isChecked) {
                    CacheModel.putBoolean(Constants.Keys.STOP_NOTIFICATIONS, true);

                }
                break;

            default:
                break;
        }

    }
}

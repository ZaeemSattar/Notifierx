package droid.zaeem.notifierx.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.database.DatabaseHandler;
import droid.zaeem.notifierx.database.Message;
import droid.zaeem.notifierx.helpers.Constants;


/**
 * Created by Droid on 7/15/2016.
 */
public class HomeFragment extends BaseFragment {
    LinearLayout rollNumberLayout;
    AppCompatTextView rollNumber, name, email, phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rollNumberLayout = (LinearLayout) rootView.findViewById(R.id.student_extra_info);
        name = (AppCompatTextView) rootView.findViewById(R.id.name);
        email= (AppCompatTextView) rootView.findViewById(R.id.email);
        phone = (AppCompatTextView) rootView.findViewById(R.id.phone);
        if (CacheModel.getBoolean(Constants.Keys.IS_STUDENT)) {
            rollNumberLayout.setVisibility(View.VISIBLE);
            rollNumber = (AppCompatTextView) rootView.findViewById(R.id.rollnumber);
            rollNumber.setText(CacheModel.getString(Constants.Keys.STUDENT_ROLLNUMBER) + " - " + CacheModel.getString(Constants.Keys.STUDENT_CLASS).toUpperCase() + " - " + CacheModel.getString(Constants.Keys.STUDENT_YEAR));
            name.setText(CacheModel.getString(Constants.Keys.STUDENT_NAME));
            email.setText(CacheModel.getString(Constants.Keys.STUDENT_EMAIL));
            phone.setText(CacheModel.getString(Constants.Keys.STUDENT_PHONE));




        }
        if (!CacheModel.getBoolean(Constants.Keys.IS_STUDENT)) {
            rollNumberLayout.setVisibility(View.GONE);
            name.setText(CacheModel.getString(Constants.Keys.VISITOR_NAME));
            email.setText(CacheModel.getString(Constants.Keys.VISITOR_EMAIL));
            phone.setText(CacheModel.getString(Constants.Keys.VISITOR_PHONE));




        }

        return rootView;
    }


}
package droid.zaeem.notifierx.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import droid.zaeem.notifierx.R;


/**
 * Created by Droid on 6/27/2016.
 */
public class DcsWebsiteFragment extends BaseFragment {
    LinearLayout progressbar;


    public DcsWebsiteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dcswebsite, container, false);
        progressbar = (LinearLayout) rootView.findViewById(R.id.progressbar_layout);
        progressbar.setVisibility(View.VISIBLE);
        WebView myWebView = (WebView) rootView.findViewById(R.id.webview);
        myWebView.loadUrl("http://dcs.gcu.edu.pk/");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressbar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }
}
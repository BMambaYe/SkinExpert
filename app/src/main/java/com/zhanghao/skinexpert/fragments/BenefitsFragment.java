package com.zhanghao.skinexpert.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhanghao.skinexpert.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment {

    private View view;
    private WebView webView;
    private String benefitsURL = "http://www.caimiapp.com/fllbas/?token=&skin=----&source=app";
    public BenefitsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_benefits, container, false);
            webView = (WebView) view.findViewById(R.id.webview_benefits);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(benefitsURL);

        }
        return view;
    }

}

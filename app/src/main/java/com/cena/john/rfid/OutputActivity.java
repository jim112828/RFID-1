package com.cena.john.rfid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Zack on 2016/12/28.
 */

public class OutputActivity extends AppCompatActivity {

    private String Params;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_webview);

        getBundle();

        init();

        webViewSettings();
    }

    private void getBundle() {
        Intent intent = this.getIntent();

        if (intent.getExtras() != null) {
            Params = intent.getExtras().getString("data");
        }
    }

    private void init() {

        webview = (WebView)findViewById(R.id.outputWV);
    }

    private void webViewSettings() {

        WebSettings WebSettings = webview.getSettings();
        WebSettings.setJavaScriptEnabled(true);
        WebSettings.setUseWideViewPort(true);
        WebSettings.setLoadWithOverviewMode(true);
        WebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings.setDomStorageEnabled(true);

        webview.loadUrl(Params);
        webview.setWebViewClient(new webViewClient());
        webview.setWebChromeClient(new webChromeClient());
    }

    private class webViewClient extends WebViewClient {
    }

    private class webChromeClient extends WebChromeClient {
    }
}

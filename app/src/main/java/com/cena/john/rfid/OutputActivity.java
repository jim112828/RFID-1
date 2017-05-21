package com.cena.john.rfid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.cena.john.rfid.R.id.imageView;


/**
 * Created by Zack on 2016/12/28.
 */

public class OutputActivity extends AppCompatActivity {

    private String Params;
    private WebView webview;
    private ImageView imageOne;
    private ImageView imageTwo;
    private String Params2;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_webview);

        getBundle();

        init();

        Picasso.with(this).load(Params)
                .resize(1000,1000)
                .into(imageOne);
        textView5.setText(Params2);
        Picasso.with(this).load("http://i.imgur.com/YYDHz0Y.jpg")
                .resize(600, 800)
                .into(imageTwo);


        }
       // webViewSettings();


    private void getBundle() {
        Intent intent = this.getIntent();

        if (intent.getExtras() != null) {
            Params = intent.getExtras().getString("data");
            Params2 = intent.getExtras().getString("uid");
        }
    }

    private void init() {
        imageOne = (ImageView) findViewById(R.id.imageOne);
        imageTwo = (ImageView) findViewById(R.id.imageTwo);
        textView5 = (TextView) findViewById(R.id.textView5);
        //webview = (WebView)findViewById(R.id.outputWV);
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

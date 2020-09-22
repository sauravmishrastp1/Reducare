package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;

public class LiveClass extends AppCompatActivity {
    android.webkit.WebView webView;
    private String webUrl = "";
    private View view;
    private TextView textView;
    Bundle bundle;
    String test_series_salabyous_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liveclass);
        webView = findViewById(R.id.webView);
        view = findViewById(R.id.toolbar);
        textView = findViewById(R.id.textview);
        bundle = getIntent().getExtras();

      //  test_series_salabyous_id = bundle.getString("id");
        //Toast.makeText(this, ""+TestSeriessQuestionActivity.id+test_series_salabyous_id+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();
        webUrl = "https://us04web.zoom.us/wc/join/6310647200?wpk=wcpke95d64fbc9e2251ee493f1cdee719601";
        loadWebViewLoad(webView);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebViewLoad(final android.webkit.WebView webview) {
        view.setVisibility(View.VISIBLE);
        final ProgressDialog pd = ProgressDialog.show(LiveClass.this, "", "Please wait...", true);
        pd.setCancelable(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(android.webkit.WebView view, int errorCode, String description, String failingUrl) {
                //Toast.makeText(WebView.this, description, Toast.LENGTH_SHORT).show();
                view.setVisibility(View.VISIBLE);
                webview.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                pd.show();

            }


            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                pd.dismiss();
            }


        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(webUrl);
        view.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

}




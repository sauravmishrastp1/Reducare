package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;

public class WebView extends AppCompatActivity {
    android.webkit.WebView webView;
    private String webUrl = "";
    private View view;
    private TextView textView;
    Bundle bundle;
    String test_series_salabyous_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        view = findViewById(R.id.toolbar);
        textView = findViewById(R.id.textview);
        bundle = getIntent().getExtras();

        test_series_salabyous_id = bundle.getString("id");
        //Toast.makeText(this, ""+TestSeriessQuestionActivity.idt+test_series_salabyous_id+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();
        webUrl = "http://swasthyaayur.com/adwogindia.com/raushanedu/public/quiz-question/" + TestSeriessQuestionActivity.idt + "/" + test_series_salabyous_id + "/" + SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();
        loadWebViewLoad(webView);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebViewLoad(final android.webkit.WebView webview) {
        view.setVisibility(View.VISIBLE);
        final ProgressDialog pd = ProgressDialog.show(WebView.this, "", "Please wait...", true);
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




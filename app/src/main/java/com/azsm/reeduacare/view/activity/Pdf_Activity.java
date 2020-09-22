package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pdf_Activity extends AppCompatActivity {
    private TextView txt; // You can remove if you don't want this
    android.webkit.WebView webView;
    private String webUrl,url;
    Bundle bundle;
    ImageView backpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_);
        webView = findViewById(R.id.WebView);
        backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bundle = getIntent().getExtras();

        url = bundle.getString("url");
       // Toast.makeText(this, "url->"+url, Toast.LENGTH_SHORT).show();
        //test_series_salabyous_id = bundle.getString("id");
        webUrl ="https://docs.google.com/gview?embedded=true&url="+url;
        loadWebViewLoad(webView);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebViewLoad(final android.webkit.WebView webview) {
        //view.setVisibility(View.VISIBLE);
        final ProgressDialog pd = ProgressDialog.show(Pdf_Activity.this, "", "Please wait...", true);
        pd.setCancelable(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.setWebViewClient(new WebViewClient(){
            public void onReceivedError(android.webkit.WebView view, int errorCode, String description, String failingUrl) {
                //Toast.makeText(WebView.this, description, Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.VISIBLE);
                //webview.setVisibility(View.GONE);
                webview.setWebChromeClient(new WebChromeClient());
                //Toast.makeText(this, "dff"+webUrl, Toast.LENGTH_SHORT).show();
                webview.loadUrl(webUrl);
                // view.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon)
            {
                pd.show();

            }


            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                pd.dismiss();
            }


        });
        webview.setWebChromeClient(new WebChromeClient());
        //Toast.makeText(this, "dff"+webUrl, Toast.LENGTH_SHORT).show();
        webview.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
       // view.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            this.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                if (webView.canGoBack()){
                    webView.goBack();
                } else {
                    this.finish();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


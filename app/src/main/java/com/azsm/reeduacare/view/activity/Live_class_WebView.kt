package com.azsm.reeduacare.view.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.azsm.reeduacare.R
import com.azsm.reeduacare.constant.SharedPrefManager

class Live_class_WebView : AppCompatActivity() {
    var webView: WebView? = null
    private var webUrl = ""
    private var view: View? = null
    private var textView: TextView? = null
    var bundle: Bundle? = null
    var test_series_salabyous_id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_class__web_view)
        webView = findViewById<WebView>(R.id.webView)
        view = findViewById<View>(R.id.toolbar)
        textView = findViewById<TextView>(R.id.textview)
        bundle = intent.extras

        test_series_salabyous_id = bundle!!.getString("id")
        //Toast.makeText(this, ""+TestSeriessQuestionActivity.id+test_series_salabyous_id+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+TestSeriessQuestionActivity.id+test_series_salabyous_id+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();
        webUrl = "http://swasthyaayur.com/adwogindia.com/raushanedu/public/quiz-question/" + TestSeriessQuestionActivity.idt + "/" + test_series_salabyous_id + "/" + SharedPrefManager.getInstance(applicationContext).user.userid
      //  loadWebViewLoad(webView)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebViewLoad(webview: WebView) {
        view!!.setVisibility(View.VISIBLE)
        val pd = ProgressDialog.show(this, "", "Please wait...", true)
        pd.setCancelable(true)
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.setSupportMultipleWindows(true)
        webview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                //Toast.makeText(WebView.this, description, Toast.LENGTH_SHORT).show();
                view.visibility = View.VISIBLE
                webview.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                pd.show()
            }

            override fun onPageFinished(view: WebView, url: String) {
                pd.dismiss()
            }
        }
        webview.webChromeClient = WebChromeClient()
        webview.loadUrl(webUrl)
        view!!.setVisibility(View.GONE)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, Main2Activity::class.java)
        startActivity(intent)
    }

}




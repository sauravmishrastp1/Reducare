package com.azsm.reeduacare.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azsm.reeduacare.R;
import com.github.barteksc.pdfviewer.PDFView;

public class AboutUsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "aboutus.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

    }
}
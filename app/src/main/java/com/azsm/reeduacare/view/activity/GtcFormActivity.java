package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.azsm.reeduacare.R;
import com.github.barteksc.pdfviewer.PDFView;

public class GtcFormActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "aboutus.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    Toolbar toolbar;
    ImageView backpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtc_form);
        backpress = findViewById(R.id.backMyOrders);
        pdfView = (PDFView) findViewById(R.id.pfd);
        //displayFromAsset(SAMPLE_FILE);


        pdfView.fromAsset("aboutt.pdf")

                .load();
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}

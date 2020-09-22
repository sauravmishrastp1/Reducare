package com.azsm.reeduacare.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azsm.reeduacare.R;
import com.github.barteksc.pdfviewer.PDFView;

public class SubjectPdfNotes extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "pdffile.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_pdf_notes);
        toolbar = findViewById(R.id.coursetoolbar);
        pdfView = (PDFView) findViewById(R.id.pdffileread);
       // displayFromAsset(SAMPLE_FILE);
        toolbar.setTitle("Notes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        pdfView.fromAsset("pdffile.pdf")
                .load();

    }







}

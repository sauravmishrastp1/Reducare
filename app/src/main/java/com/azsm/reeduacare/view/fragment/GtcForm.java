package com.azsm.reeduacare.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.azsm.reeduacare.view.activity.MainActivity;
import com.azsm.reeduacare.R;
import com.github.barteksc.pdfviewer.PDFView;


public class GtcForm extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "aboutus.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.gtc_pdf, container, false);

        pdfView = (PDFView) v.findViewById(R.id.pdf);
         //displayFromAsset(SAMPLE_FILE);


        pdfView.fromAsset("ercc.pdf")

                .load();
        return v;
    }



}

package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.StudyMatrialSubtopic;
import com.azsm.reeduacare.view.activity.Pdf_Activity;

import java.util.ArrayList;

public class   StudyMatrialSuBcLAss extends RecyclerView.Adapter<StudyMatrialSuBcLAss.ViewHolder> {
    private ArrayList<StudyMatrialSubtopic> studyMatrialSubtopics;
    private Context context;

    public StudyMatrialSuBcLAss(ArrayList<StudyMatrialSubtopic> studyMatrialSubtopics, Context context) {
        this.studyMatrialSubtopics = studyMatrialSubtopics;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.studymatrialsubcalss, parent, false);
        return new StudyMatrialSuBcLAss.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       String sno = studyMatrialSubtopics.get(position).getSno();
       String heading = studyMatrialSubtopics.get(position).getHeading();
       holder.sno.setText(sno+".");
       holder.heading.setText(heading);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, Pdf_Activity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("url",studyMatrialSubtopics.get(position).getSno());

               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return studyMatrialSubtopics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sno,heading;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sno = itemView.findViewById(R.id.historyimg);
            heading = itemView.findViewById(R.id.keywordtv);
        }
    }
}

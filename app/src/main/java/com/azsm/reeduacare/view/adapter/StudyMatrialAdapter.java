package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.StudyMatrial;
import com.azsm.reeduacare.view.activity.StudyMatrialActivity2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyMatrialAdapter extends RecyclerView.Adapter<StudyMatrialAdapter.ViewHolder> {
    private ArrayList<StudyMatrial> studyMatrials;
    private Context context;

    public StudyMatrialAdapter(ArrayList<StudyMatrial> studyMatrials, Context context) {
        this.studyMatrials = studyMatrials;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.studymatrialsubject, parent, false);
        return new StudyMatrialAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String name = studyMatrials.get(position).getName();
        Picasso.with(context).load(studyMatrials.get(position).getImage()).into(holder.logo);
        holder.name.setText(name);
        //holder.logo.setImageResource(img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudyMatrialActivity2.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   intent.putExtra("id",studyMatrials.get(position).getId());
                    context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studyMatrials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        ImageView logo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.coursename);
            logo = itemView.findViewById(R.id.courseicon);
        }
    }
}

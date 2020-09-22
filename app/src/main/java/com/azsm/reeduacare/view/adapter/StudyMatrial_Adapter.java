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
import com.azsm.reeduacare.model.ProvideCourceModelClass;
import com.azsm.reeduacare.view.activity.StudyMatrialActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyMatrial_Adapter extends RecyclerView.Adapter<StudyMatrial_Adapter.ViewHolder> {
    private ArrayList<ProvideCourceModelClass> provideCourceModelClasses;

    public StudyMatrial_Adapter(ArrayList<ProvideCourceModelClass> provideCourceModelClasses, Context context) {
        this.provideCourceModelClasses = provideCourceModelClasses;
        this.context = context;
    }

    private Context context;


    @NonNull
    @Override
    public StudyMatrial_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.providedcourcelayout, parent, false);
        return new StudyMatrial_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyMatrial_Adapter.ViewHolder holder, final int position) {
      String img = provideCourceModelClasses.get(position).getCourceimg();
      final String name = provideCourceModelClasses.get(position).getCourcebname();
        Picasso.with(context).load(provideCourceModelClasses.get(position).getCourceimg()).fit().centerCrop()
                .placeholder(R.drawable.placeholder) .into(holder.courceimg);;
      holder.courcename.setText(name);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                  Intent intent=new Intent(context, StudyMatrialActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   intent.putExtra("catname",provideCourceModelClasses.get(position).getCourceid());
                   intent.putExtra("id",provideCourceModelClasses.get(position).getPrice());

                  context.startActivity(intent);

//                  Intent intent = new Intent(context, ProvideCourceCategory.class);
//                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                  intent.putExtra("catname", provideCourceModelClasses.get(position).getCourcebname());

                  context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return provideCourceModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView courceimg;
        TextView courcename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courceimg = itemView.findViewById(R.id.imgview);
            courcename = itemView.findViewById(R.id.courcename);
        }
    }
}

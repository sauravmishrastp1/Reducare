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
import com.azsm.reeduacare.model.AtempttList;
import com.azsm.reeduacare.view.activity.Pdf_Activity;
import com.azsm.reeduacare.view.activity.TestSeriessQuestionActivity;
import com.azsm.reeduacare.view.activity.VideoPlayActivity;

import java.util.List;

public class ActivetestAdapter extends RecyclerView.Adapter<ActivetestAdapter.ViewHolder> {
    private List<AtempttList> atempttLists;
    private Context context;
    public static String id ,time;

    public ActivetestAdapter(List<AtempttList> atempttLists, Context context) {
        this.atempttLists = atempttLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivetestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activequestionlayout, parent, false);
        return new ActivetestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivetestAdapter.ViewHolder holder, final int position) {
     String heading = atempttLists.get(position).getTestheading();
     holder.heading.setText(heading);

     if(atempttLists.get(position).getQuestioncount().equals("v")){
         holder.playbtb.setImageResource(R.drawable.playbtn);
     }if(atempttLists.get(position).getQuestioncount().equals("pdf")) {
         holder.playbtb.setImageResource(R.drawable.pdf);
     }if(atempttLists.get(position).getQuestioncount().equals("t")) {
            holder.playbtb.setImageResource(R.drawable.exammm);
        }
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(atempttLists.get(position).getQuestioncount().equals("v")){
                 Intent intent11 = new Intent(context, VideoPlayActivity.class);
                 intent11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent11.putExtra("url", atempttLists.get(position).getTime());
                 context.startActivity(intent11);
             }if (atempttLists.get(position).getQuestioncount().equals("pdf")){
                 Intent intent11 = new Intent(context, Pdf_Activity.class);
                 intent11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent11.putExtra("url", atempttLists.get(position).getTime());
                 context.startActivity(intent11);
             }if(atempttLists.get(position).getQuestioncount().equals("t")){
                 Intent intent22 = new Intent(context, TestSeriessQuestionActivity.class);
                 intent22.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent22.putExtra("id", atempttLists.get(position).getTime());
                 context.startActivity(intent22);
             }


         }
     });

    }

    @Override
    public int getItemCount() {
        return atempttLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView heading;
        ImageView playbtb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            playbtb = itemView.findViewById(R.id.imageview);

        }
    }
}

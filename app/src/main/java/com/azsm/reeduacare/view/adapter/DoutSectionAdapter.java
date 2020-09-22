package com.azsm.reeduacare.view.adapter;

import android.app.Activity;
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
import com.azsm.reeduacare.model.DoutsectionAnswer;
import com.azsm.reeduacare.view.activity.VideoPlayActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DoutSectionAdapter extends RecyclerView.Adapter<DoutSectionAdapter.ViewHolder> {
    private ArrayList<DoutsectionAnswer> doutsectionAnswers;
    private Context context;

    public DoutSectionAdapter(ArrayList<DoutsectionAnswer> doutsectionAnswers, Context context) {
        this.doutsectionAnswers = doutsectionAnswers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doutanswerlayout, parent, false);
        return new DoutSectionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      String url = doutsectionAnswers.get(position).getVediourl();
        int microSecond = 1000000;// 6th second as an example
        RequestOptions options = new RequestOptions().frame(microSecond).override(80, 40);
        String question= doutsectionAnswers.get(position).getQUESTON();
        holder.dis.setText("Answer:"+""+question);

        Glide.with(context)
                .asBitmap()
                .load(doutsectionAnswers.get(position).getVediourl())
                .apply(options)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("url", doutsectionAnswers.get(position).getVediourl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return doutsectionAnswers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView dis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            dis = itemView.findViewById(R.id.dis);
        }
    }
}

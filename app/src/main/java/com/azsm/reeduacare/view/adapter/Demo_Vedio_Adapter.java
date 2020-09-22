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
import com.azsm.reeduacare.model.Demo_Viedo_Model;
import com.azsm.reeduacare.view.activity.VideoPlayActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Demo_Vedio_Adapter extends RecyclerView.Adapter<Demo_Vedio_Adapter.ViewHolder> {
    private ArrayList<Demo_Viedo_Model>demo_viedo_models;
    private Context context;
    private String vediofile;
    public static String url;

    public Demo_Vedio_Adapter(ArrayList<Demo_Viedo_Model> demo_viedo_models, Context context) {
        this.demo_viedo_models = demo_viedo_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.demo_vedio_item, parent, false);
        return new Demo_Vedio_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String img = demo_viedo_models.get(position).getVedio_thumbnail();
        String title = demo_viedo_models.get(position).getVeddio_title();
         vediofile = demo_viedo_models.get(position).getVedio_file();
         String type = demo_viedo_models.get(position).getType();
         if(type.equals("paid")){
             holder.lockbtn.setVisibility(View.VISIBLE);
             holder.paybtn.setVisibility(View.GONE);
         }else {
             holder.paybtn.setVisibility(View.VISIBLE);
             holder.lockbtn.setVisibility(View.GONE);
         }
        Picasso.with(context).load(img).into(holder.thumbnail);
        holder.tittle.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = vediofile;
            }
        });
        holder.paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url",demo_viedo_models.get(position).getVedio_file());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return demo_viedo_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView tittle;
        ImageView paybtn,lockbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            tittle = itemView.findViewById(R.id.title);
            paybtn = itemView.findViewById(R.id.paybtn);
            lockbtn = itemView.findViewById(R.id.lock);
        }
    }
}

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
import com.azsm.reeduacare.model.Notification_model;
import com.azsm.reeduacare.view.activity.Main2Activity;
import com.azsm.reeduacare.view.activity.VideoDetailsActivity;

import java.util.ArrayList;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ViewHolder> {
    ArrayList<Notification_model>notification_models;
    Context context;

    public Notification_Adapter(ArrayList<Notification_model> notification_models, Context context) {
        this.notification_models = notification_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notify_layout, parent, false);
        return new Notification_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String id = notification_models.get(position).getId();
        String catname = notification_models.get(position).getCategory_name();
        String dis = notification_models.get(position).getDescription();
        holder.dis_crip.setText(dis);
        holder.main_heading.setText(catname);
        String sixe = String.valueOf(notification_models.size());
        Main2Activity.notifiation_count.setText(sixe);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VideoDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("cimage",notification_models.get(position).getCategory_image());
                intent.putExtra("ctitle",notification_models.get(position).getCategory_name());
                intent.putExtra("cdesc",notification_models.get(position).getDescription());
                intent.putExtra("cprice",notification_models.get(position).getPrice());
                intent.putExtra("totalrating", "1");
                intent.putExtra("courseid", notification_models.get(position).getId());
                intent.putExtra("creatername",notification_models.get(position).getCategory_name());
                intent.putExtra("createremail",notification_models.get(position).getType());
                intent.putExtra("intenttype","1");
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notification_models.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView main_heading;
        TextView dis_crip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_heading = itemView.findViewById(R.id.maingheading);
            dis_crip = itemView.findViewById(R.id.dicsrip);
        }
    }
}

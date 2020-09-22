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
import com.azsm.reeduacare.model.Mycourcer_model;
import com.azsm.reeduacare.view.activity.MyCourceSubject_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCourceAdapter extends RecyclerView.Adapter<MyCourceAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mycourcer_model>mycourcer_models;

    public MyCourceAdapter(Context context, ArrayList<Mycourcer_model> mycourcer_models) {
        this.context = context;
        this.mycourcer_models = mycourcer_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycouce_item_layout, parent, false);
        return new MyCourceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.dis.setText(mycourcer_models.get(position).getDisss());
        holder.cource_name.setText(mycourcer_models.get(position).getName());
        Picasso.with(context).load(mycourcer_models.get(position).getDis()).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MyCourceSubject_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catname",mycourcer_models.get(position).getImgurl());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mycourcer_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView cource_name,dis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cource_name = itemView.findViewById(R.id.productname);
            icon = itemView.findViewById(R.id.productimg);
            dis = itemView.findViewById(R.id.productpp);
        }
    }
}

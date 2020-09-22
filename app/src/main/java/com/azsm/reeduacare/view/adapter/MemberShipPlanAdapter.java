package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.MemberShipModel;

import java.util.ArrayList;

public class MemberShipPlanAdapter extends RecyclerView.Adapter<MemberShipPlanAdapter.ViewHolder>{
     ArrayList<MemberShipModel> memberShipModels;
     Context context;

    public MemberShipPlanAdapter(ArrayList<MemberShipModel> memberShipModels, Context context) {
        this.memberShipModels = memberShipModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.membershiplayout, parent, false);
        return new MemberShipPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String courcename = memberShipModels.get(position).getCourcename();
        holder.textView.setText(courcename);

    }

    @Override
    public int getItemCount() {
        return memberShipModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.coyrcename);
        }
    }
}

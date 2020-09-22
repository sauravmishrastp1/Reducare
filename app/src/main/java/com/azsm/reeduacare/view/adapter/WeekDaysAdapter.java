package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.Daysmodelcalss;

import java.util.ArrayList;

public class WeekDaysAdapter extends RecyclerView.Adapter<WeekDaysAdapter.ViewHolder> {
    public WeekDaysAdapter(ArrayList<Daysmodelcalss> daysmodelcalsses, Context context) {
        this.daysmodelcalsses = daysmodelcalsses;
        this.context = context;
    }

    public static ArrayList<Daysmodelcalss> daysmodelcalsses;
    public static ArrayList<Daysmodelcalss> newlistday = new ArrayList<>();
    private Context context;




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weekdayslayout,parent,false);
        return new WeekDaysAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
     String daysname = daysmodelcalsses.get(position).getFeetype();
     holder.textView.setText(daysname);

        final int pos = position;
//        final Daysmodelcalss items = daysmodelcalsses.get(pos);
//        holder.checkBox.setChecked(Boolean.parseBoolean(daysmodelcalsses.get(position).getDays()));
//        holder.checkBox.setChecked(daysmodelcalsses.get(position).isIsselected());
//        holder.checkBox.setTag(position);





    }
    public ArrayList<Daysmodelcalss> getAllData () {
        return daysmodelcalsses;
    }
    public void setCheckBox ( int position){


    }

    @Override
    public int getItemCount() {
        return daysmodelcalsses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.days);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}

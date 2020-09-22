package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.Score_cardDate;

import java.util.ArrayList;

public class Result_Adapter extends RecyclerView.Adapter<Result_Adapter.ViewHolder> {
    private ArrayList<Score_cardDate> score_cardDates;
    private Context context;

    public Result_Adapter(ArrayList<Score_cardDate> score_cardDates, Context context) {
        this.score_cardDates = score_cardDates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scorecard_itemlayout,parent,false);

        return new Result_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = score_cardDates.get(position).getAttribute_image();
        String heading  = score_cardDates.get(position).getAtributename();
        String no = score_cardDates.get(position).getNo();
        holder.marks.setText(no);
        holder.imageView.setImageResource(img);
        holder.textView.setText(heading);

    }

    @Override
    public int getItemCount() {
        return score_cardDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,marks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iamge_view);
            textView = itemView.findViewById(R.id.heading);
            marks = itemView.findViewById(R.id.marks)   ;
        }
    }
}

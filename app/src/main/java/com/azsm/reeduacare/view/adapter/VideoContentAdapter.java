package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.VideoModel;
import com.azsm.reeduacare.view.activity.VideoPlayActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoModel> videoContentMoelList;

    public VideoContentAdapter(Context context, ArrayList<VideoModel> videoContentMoelList) {
        this.context = context;
        this.videoContentMoelList = videoContentMoelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_content_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.titletv.setText(videoContentMoelList.get(position).getVideotitle());
        holder.durationtv.setText("");


      String v = videoContentMoelList.get(position).getVideotitle();
        Toast.makeText(context, "v="+v, Toast.LENGTH_SHORT).show();


//            if (videoContentMoelList.get(position).getPreview().equals("unpaid")) {
//                holder.playbtnimageview.setVisibility(View.VISIBLE);
//
//            }
//
//            else {
//                holder.lockbutton.setVisibility(View.VISIBLE);
//            }
//




//        int microSecond = 1000000;// 6th second as an example
//        RequestOptions options = new RequestOptions().frame(microSecond).override(80, 40);
//
//        Glide.with(context)
//                .asBitmap()
//                .load(videoContentMoelList.get(position).getVideourl())
//                .apply(options)
//                .into(holder.videothumbnail);

        //String duration = getVideoDuration(videoContentMoelList.get(position).getVideourl());


       // holder.durationtv.setText(String.valueOf(position+1));


        holder.playbtnimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url", videoContentMoelList.get(position).getVideourl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return videoContentMoelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titletv, durationtv;
        ImageView playbtnimageview,lockbutton;
        ImageView videothumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titletv = itemView.findViewById(R.id.title);
            durationtv = itemView.findViewById(R.id.videodurationtv);
            playbtnimageview = itemView.findViewById(R.id.playbtn);
            videothumbnail=itemView.findViewById(R.id.videothumbnail);
            lockbutton = itemView.findViewById(R.id.lockimage);

        }
    }


    public String getVideoDuration(String videourl) {
        try {


            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videourl, new HashMap<String, String>());
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long timeInMillisec = Long.parseLong(time);
            retriever.release();
            String duration = convertMillieToHMmSs(timeInMillisec);
            return duration;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "not converted"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return "";
    }

    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
        else {
            return String.format("%02d:%02d" , minute, second);
        }

    }
}

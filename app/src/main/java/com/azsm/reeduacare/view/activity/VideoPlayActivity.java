package com.azsm.reeduacare.view.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import com.azsm.reeduacare.R;

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView videoView;
    private String url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        videoView=findViewById(R.id.videoview);
        progressBar=findViewById(R.id.progressBar);

        Bundle bundle=getIntent().getExtras();

        if (bundle!=null)
        {
            url=bundle.getString("url");
        }
//        Uri uri = Uri.parse(url);
//        Toast.makeText(this, "url="+url, Toast.LENGTH_SHORT).show();
//        MediaController mediaController=new MediaController(this);
//        mediaController.setAnchorView(videoView);
//
//        videoView.setMediaController(mediaController);
//        videoView.requestFocus();
//        progressBar.setVisibility(View.VISIBLE);
//        videoView.setVideoURI(uri);
    final ProgressDialog pDialog = new ProgressDialog(this);

        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);

            Uri videoUri = Uri.parse(url);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(videoUri);

        } catch (Exception e) {

            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
                progressBar.setVisibility(View.GONE);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                    progressBar.setVisibility(View.GONE);
                }
                finish();
            }
        });
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//
//
//
//                mp.start();
//
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
//                        // TODO Auto-generated method stub
//
//                        progressBar.setVisibility(View.GONE);
//                        mp.start();
//                    }
//                });
//
//
//            }
//        });
    }
}

package com.azsm.reeduacare.view.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.azsm.reeduacare.R

class Vedio_palyer_Activity : AppCompatActivity() {
    private var simpleVideoView:VideoView?=null;
    private var mediaControls:MediaController?=null;
    private var bundel:Bundle?=null;
    private var url:String?=null;
    private var progress_bar:ProgressBar?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vedio_palyer_)
        simpleVideoView= findViewById(R.id.videoview);
        progress_bar = findViewById(R.id.progressBar);

        bundel = intent.extras;
        if(bundel!=null){
            url = bundel!!.getString("url")
        }

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        // set the media controller for video view
        simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        simpleVideoView!!.setVideoURI(Uri.parse(url));

        simpleVideoView!!.requestFocus()

        // starting the video
        progress_bar!!.setVisibility(View.GONE)
        simpleVideoView!!.start()


        // display a toast message
        // after the video is completed
        simpleVideoView!!.setOnCompletionListener {
            progress_bar!!.setVisibility(View.GONE)
            Toast.makeText(applicationContext, "Video completed",
                    Toast.LENGTH_LONG).show()
        }

        // display a toast message if any
        // error occurs while playing the video
        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occured " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }

    }

    override fun onBackPressed() {
        super.finish()
    }
}

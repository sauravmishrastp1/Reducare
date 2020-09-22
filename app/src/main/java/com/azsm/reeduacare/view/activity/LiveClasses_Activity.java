package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.azsm.reeduacare.R;

public class LiveClasses_Activity extends AppCompatActivity {
  private View zoom_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_classes_);
        ImageView backpress = findViewById(R.id.backMyOrders);
        zoom_layout = findViewById(R.id.zoom_id);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });
        zoom_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LiveClass.class);
                startActivity(intent );
            }
        });


    }
}

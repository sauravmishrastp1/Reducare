package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;

public class MyProfile_Acrtivity_ extends AppCompatActivity {
    TextView nametxt,emaitxt,phinetxt;
    private View cardlayout;
    private View mycource_layout,logout,result_layut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile__acrtivity_);
        mycource_layout = findViewById(R.id.mycoure);
        nametxt = findViewById(R.id.username);
        emaitxt = findViewById(R.id.mailid);
        cardlayout = findViewById(R.id.logoutcard);
        phinetxt = findViewById(R.id.phoneno);
        result_layut = findViewById(R.id.result_layout);
        String name = SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();
        String phone = SharedPrefManager.getInstance(getApplicationContext()).getUser().getMobilenumber();
        String email = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail();
        nametxt.setText(name);
        emaitxt.setText(email);
        phinetxt.setText(phone);
        result_layut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultAnalysis.class);
                startActivity(intent);
            }
        });
        cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

        mycource_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile_Acrtivity_.this,CourceActivity.class);
                startActivity(intent);
            }
        });
    }
}

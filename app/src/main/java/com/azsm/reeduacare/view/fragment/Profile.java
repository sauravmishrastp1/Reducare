package com.azsm.reeduacare.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.view.activity.CourceActivity;
import com.azsm.reeduacare.view.activity.ResultAnalysis;


public class Profile extends Fragment {
 TextView nametxt,emaitxt,phinetxt;
 private View cardlayout,coucelalogout,result_layut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nametxt = view.findViewById(R.id.username);
        emaitxt = view.findViewById(R.id.mailid);
        cardlayout = view.findViewById(R.id.logoutcard);
        result_layut = view.findViewById(R.id.result_layout);
        phinetxt = view.findViewById(R.id.phoneno);
        String name = SharedPrefManager.getInstance(getContext()).getUser().getName();
        String phone = SharedPrefManager.getInstance(getContext()).getUser().getMobilenumber();
        String email = SharedPrefManager.getInstance(getContext()).getUser().getEmail();
        coucelalogout = view.findViewById(R.id.logoutcard);
        nametxt.setText(name);
        emaitxt.setText(email);
        phinetxt.setText(phone);
        cardlayout = view.findViewById(R.id.mycource);
        result_layut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ResultAnalysis.class);
                startActivity(intent);
            }
        });
        coucelalogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getContext()).logout();
            }
        });

        cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CourceActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}

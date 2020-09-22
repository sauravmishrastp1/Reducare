package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.Notification_Adapter;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.model.Notification_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
     private RecyclerView recyclerView;
     private ArrayList<Notification_model> notificationsmodel = new ArrayList<Notification_model>();
     private ProgressBar progressBar;
     private ImageView notification_img;
     private ImageView backpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recyclerview);
        notification_img = findViewById(R.id.nottotidy);
        progressBar = findViewById(R.id.progeressbar);
        backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getntification();

    }

    private void getntification() {

           progressBar.setVisibility(View.VISIBLE);
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/live-notify?user_id="+ SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject object = new JSONObject(response);

                                String status = object.getString("status");

                                if (status.equals("200")) {


                                    JSONArray ratingarray = object.getJSONArray("notifycourse");


                                    for (int i = 0; i < ratingarray.length(); i++) {

                                        JSONObject jsonObject = ratingarray.getJSONObject(i);
                                       String  id = jsonObject.getString("id");
                                       String category_name =jsonObject.getString("category_name");
                                       String type = jsonObject.getString("type");
                                       String category_image = jsonObject.getString("category_image");
                                       String price = jsonObject.getString("price");
                                       String description = jsonObject.getString("description");
                                       String notification = jsonObject.getString("notification");
                                        //if (paidtype.equals("unpaid")) {
                                         notificationsmodel.add(new Notification_model(id,category_name,type,category_image,price,description,notification));
                                        @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                                = new LinearLayoutManager(Notification.this, LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager(horizontalLayoutManager2);
                                        Notification_Adapter courceProviderAdapter = new Notification_Adapter(notificationsmodel, getApplicationContext());
                                        recyclerView.setAdapter(courceProviderAdapter);
                                        progressBar.setVisibility(View.GONE);
                                       notification_img.setVisibility(View.GONE);

                                    }
//                                JSONArray coursearrayy = object.getJSONArray("videos");
//
//                                for (int j = 0; j < coursearrayy.length(); j++) {
//
//                                    JSONObject jsonObject = coursearrayy.getJSONObject(j);
//
//                                    String videoid = jsonObject.getString("id");
//                                    String videotitle = jsonObject.getString("videos_title");
//                                    String videourl = jsonObject.getString("videos_file");
//                                    String ispreview = jsonObject.getString("payment_type");
//
//                                    videoModelList.add(new VideoModel(videoid, videotitle, "https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + videourl, ispreview));
//
//
//                                    progressBar.setVisibility(View.GONE);
//                                    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
//                                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                                    recyclerView.setLayoutManager(gridLayoutManager);
//                                    VideoContentAdapter courseAdapter = new VideoContentAdapter(VideoDetailsActivity.this, videoModelList);
//                                    recyclerView.setAdapter(courseAdapter);
//                                    courseAdapter.notifyDataSetChanged();
//
//
//                                }
                                } else {

                                    //Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    notification_img.setVisibility(View.VISIBLE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                               // Toast.makeText(Notification.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                notification_img.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {


                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            notification_img.setVisibility(View.VISIBLE);
                        }
                    }) {

                @Override
                public void deliverError(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        Cache.Entry entry = this.getCacheEntry();
                        if (entry != null) {
                            Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                            deliverResponse(response.result);
                            return;
                        }
                    }
                    super.deliverError(error);
                }

            };

            queue.getCache().clear();
            queue.add(stringRequest);


        }

}
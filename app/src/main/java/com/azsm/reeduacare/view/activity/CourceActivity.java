package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.MyCourceAdapter;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.Mycourcer_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourceActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Mycourcer_model>mycourcer_models = new ArrayList<>();
    private View buy_cource_layout;
    private Button btn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cource);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);
        ImageView backpress = findViewById(R.id.backMyOrders);
        buy_cource_layout = findViewById(R.id.buybtnview);
        btn_buy = findViewById(R.id.buycoursebtn);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });



        getcourcecat();
    }

    private void getcourcecat() {

        progressBar.setVisibility(View.VISIBLE);
       // Toast.makeText(this, "id=>"+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();

        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/my-course?user_id="+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("status");

                            if (status.equals("200")) {


                                JSONArray whishlistarray = response.getJSONArray("mycourse");


                                for (int i = 0; i < whishlistarray.length(); i++) {
                                    JSONObject jsonObject = whishlistarray.getJSONObject(i);


                                    String courseid = jsonObject.getString("id");
                                    String course_title = jsonObject.getString("category_name");
                                    String course_image = jsonObject.getString("category_image");
                                    String did = jsonObject.getString("description");
                                    mycourcer_models.add(new Mycourcer_model(course_title,"https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + course_image,  courseid,did));


                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    MyCourceAdapter courceProviderAdapter = new MyCourceAdapter(CourceActivity.this, mycourcer_models);
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);
                                    buy_cource_layout.setVisibility(View.GONE);


                                }


                            } else {

                               // Toast.makeText(CourceActivity.this, "No", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                buy_cource_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CourceActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            buy_cource_layout.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CourceActivity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        buy_cource_layout.setVisibility(View.VISIBLE);
                    }
                }) {

            //    @Override
//            protected Map<String, String> getParams()  {
//                Map<String, String> params = new HashMap<>();
//                params.put("type_id",typeeid );
//                return params;
//            }

        };

        VolleySingleton.getInstance(CourceActivity.this).addToRequestQueue(jor);

    }

}

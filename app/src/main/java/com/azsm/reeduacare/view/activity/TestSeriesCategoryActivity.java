package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.TestsERIES_aDAPTER;
import com.azsm.reeduacare.model.Main_Test_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestSeriesCategoryActivity extends AppCompatActivity {
     private RecyclerView test_recy;
     private ArrayList<Main_Test_Model>main_test_models = new ArrayList<>();
     private ProgressBar progressBar;
     Bundle bundle;
     String type;
     private ImageView bacvkperss;
     private View null_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_series_category);
        test_recy = findViewById(R.id.test_cat_recy);
        bacvkperss = findViewById(R.id.backMyOrders);
        null_layout = findViewById(R.id.buybtnview);
        bacvkperss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        progressBar = findViewById(R.id.progressbar);
        bundle = getIntent().getExtras();
        if(bundle!=null){
            type = bundle.getString("type");

        }

       if(type.equals("iit")){
           gettestCat();
       }else {
           getOtrher();
       }




    }
    private void gettestCat(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/test-series";
        String otheturl ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/test-series-other";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(CourseProvide.this, "stats=>"+status, Toast.LENGTH_SHORT).show();
                            JSONArray whishlistarray=object.getJSONArray("testseries");
                            if (status.equals("200")) {


                                for (int i=0;i<whishlistarray.length();i++)
                                {
                                    JSONObject jsonObject=whishlistarray.getJSONObject(i);


                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("name");
                                     String active=jsonObject.getString("active");
                                    main_test_models.add(new Main_Test_Model(courseid,course_title,active));
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    test_recy.setLayoutManager(layoutManager);
                                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                                    TestsERIES_aDAPTER gridProductAdapter = new TestsERIES_aDAPTER(main_test_models, getApplicationContext());
                                    test_recy.setAdapter(gridProductAdapter);
                                    gridProductAdapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    null_layout.setVisibility(View.GONE);

                                }
                                if(whishlistarray.length()==0){
                                    null_layout.setVisibility(View.VISIBLE);
                                }


                            } else {

                                // progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                null_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Timeout !!!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }

    private void getOtrher(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/test-series";
        String otheturl ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/test-series";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, otheturl,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(CourseProvide.this, "stats=>"+status, Toast.LENGTH_SHORT).show();
                            JSONArray whishlistarray=object.getJSONArray("testseries");
                            if (status.equals("200")) {


                                for (int i=2;i<whishlistarray.length();i++)
                                {
                                    JSONObject jsonObject=whishlistarray.getJSONObject(i);

                                  //  Toast.makeText(TestSeriesCategoryActivity.this, ""+jsonObject, Toast.LENGTH_SHORT).show();

                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("name");
                                     String active=jsonObject.getString("active");
                                    main_test_models.add(new Main_Test_Model(courseid,course_title,active));
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    test_recy.setLayoutManager(layoutManager);
                                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                                    TestsERIES_aDAPTER gridProductAdapter = new TestsERIES_aDAPTER(main_test_models, getApplicationContext());
                                    test_recy.setAdapter(gridProductAdapter);
                                    gridProductAdapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    null_layout.setVisibility(View.GONE);

                                }
                                if(whishlistarray.length()==0){
                                    null_layout.setVisibility(View.VISIBLE);
                                }


                            } else {

                                // progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                null_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Timeout !!!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }


}

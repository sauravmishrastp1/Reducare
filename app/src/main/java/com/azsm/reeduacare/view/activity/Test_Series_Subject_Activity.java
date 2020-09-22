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
import com.azsm.reeduacare.view.adapter.Test_Series_Subject_Adapter;
import com.azsm.reeduacare.model.Main_Test_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test_Series_Subject_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<Main_Test_Model> main_test_models = new ArrayList<>();
    Bundle bundle ;
     public  static  String id;
     private ImageView  backpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_series);
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerviw);
        backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bundle = getIntent().getExtras();
        id = bundle.getString("id");
        gettestCat();
    }

    private void gettestCat(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/test-series-sullebus-subject?test_series_id="+TestSeriessQuestionActivity.idt+"&syllebus_id="+id;
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
                            JSONArray whishlistarray=object.getJSONArray("tesseriessyllebussuject");
                            if (status.equals("200")) {


                                for (int i=0;i<whishlistarray.length();i++)
                                {
                                    JSONObject jsonObject=whishlistarray.getJSONObject(i);


                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("subject_name");
                                    // String course_image=jsonObject.getString("test_time");
                                    main_test_models.add(new Main_Test_Model(courseid,course_title,""));
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                                    Test_Series_Subject_Adapter gridProductAdapter = new Test_Series_Subject_Adapter(main_test_models, getApplicationContext());
                                    recyclerView.setAdapter(gridProductAdapter);
                                    gridProductAdapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);

                                }


                            } else {

                                // progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
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
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }
}

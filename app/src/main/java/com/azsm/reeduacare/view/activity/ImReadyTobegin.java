package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.azsm.reeduacare.model.AtempttList;
import com.azsm.reeduacare.view.adapter.TestSeries_Question_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImReadyTobegin extends AppCompatActivity {
    private Button imready;
    Bundle bundle;
    String id;
    private TextView headings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_ready_tobegin);
        imready = findViewById(R.id.buybutton);
        headings = findViewById(R.id.termcondition1);
        bundle = getIntent().getExtras();
        if(!bundle.isEmpty()){
          id = bundle.getString("id");
        }
        imready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ImReadyTobegin.this, WebView.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        activequestionlist();
    }
   // https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/terms-condition?test_series_syllebus_id=1

    private void activequestionlist() {
        //Toast.makeText(getContext(), "id->"+TestSeriessQuestionActivity.id, Toast.LENGTH_SHORT).show();
       // progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/terms-condition?test_series_syllebus_id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(CourseProvide.this, "stats=>"+status, Toast.LENGTH_SHORT).show();
                            JSONObject whishlistarray=object.getJSONObject("termcondition");
                            if (status.equals("200")) {
                         String heading = whishlistarray.getString("termconditions");
                         headings.setText(heading);



                            } else {

                                // progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
//                                progressBar.setVisibility(View.GONE);
//                                null_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Timeout !!!!Try Again", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();

                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                            null_layout.setVisibility(View.VISIBLE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();

                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();

                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }



}

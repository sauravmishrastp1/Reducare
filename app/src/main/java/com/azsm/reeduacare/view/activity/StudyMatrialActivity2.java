package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.azsm.reeduacare.view.adapter.ExpandStudyMatrialAdapter;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.Menu_model_study_matrial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudyMatrialActivity2 extends AppCompatActivity {
    HashMap<Menu_model_study_matrial, List<Menu_model_study_matrial>> childList = new HashMap<Menu_model_study_matrial, List<Menu_model_study_matrial>>();
    ExpandableListView expandableListView;
    List<Menu_model_study_matrial> headerList = new ArrayList<Menu_model_study_matrial>();
    Toolbar toolbar;
    Bundle bundlel;
    private String id;
    private ProgressBar progressBar;
    private View null_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_matrial2);
        expandableListView = findViewById(R.id.expandableListView);
        ImageView backpress = findViewById(R.id.backMyOrders);
        null_layout = findViewById(R.id.buybtnview);
        progressBar = findViewById(R.id.progressbar);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });
        bundlel = getIntent().getExtras();
        if(bundlel!=null){
           id = bundlel.getString("id");
        }

      //  Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        getwislist();
    }


    private void prepareMenuData() {


    }


    private void getwislist(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/study-material-detail?subject_id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);

                            String status = object.getString("status");
                            JSONArray covidessential = object.getJSONArray("studydetail");

                            //Toast.makeText(StudyMatrialActivity2.this, ""+covidessential, Toast.LENGTH_SHORT).show();
                            if (status.equals("200")) {

                              //  Toast.makeText(StudyMatrialActivity2.this, ""+covidessential, Toast.LENGTH_SHORT).show();

                                for (int n = 0; n < covidessential.length(); n++) {
                                    JSONObject populaproductJSONObject = covidessential.getJSONObject(n);
                                    String id = populaproductJSONObject.getString("id");
                                    String heading = populaproductJSONObject.getString("study_meterial");
                                    String topic = populaproductJSONObject.getString("topic");
                                    String childheading = populaproductJSONObject.getString("videos");
                                    String stdudata= populaproductJSONObject.getString("study_data");
                                    Menu_model_study_matrial menuModel = new Menu_model_study_matrial(heading, "", "", "https://www.journaldev.com/9333/android-webview-example-tutorial",R.drawable.dropdown_icon); //Menu of Android Tutorial. No sub menus
                                    headerList.add(menuModel);
                                    List<Menu_model_study_matrial> childModelsList = new ArrayList<>();
                                    Menu_model_study_matrial childModel = new Menu_model_study_matrial(topic, "https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+stdudata, "", "https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+childheading,R.drawable.playbtn);
                                    childModelsList.add(childModel);
                                        childList.put(menuModel, childModelsList);
                                    ExpandStudyMatrialAdapter expandableListAdapter = new ExpandStudyMatrialAdapter(StudyMatrialActivity2 .this, headerList, childList);
                                    expandableListView.setAdapter(expandableListAdapter);
                                    progressBar.setVisibility(View.GONE);
                                    null_layout.setVisibility(View.GONE);


                                }
                                if(covidessential.length()==0){
                                    null_layout.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }

                            } else {

                                 progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                               progressBar.setVisibility(View.GONE);
                                null_layout.setVisibility(View.VISIBLE);
                               // textView.setVisibility(View.VISIBLE);
                            }
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "somrthing went wrong" + e.getMessage(), Toast.LENGTH_SHORT).show();
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


//        @Override
//        protected Map<String,String> getParams(){
//            Map<String,String> params = new HashMap<String, String>();
//            params.put("user_id", userID);
//            params.put("product_id", productuid);
//
//
//            return params;
//        }


        };


        queue.getCache().clear();

        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



    }


}

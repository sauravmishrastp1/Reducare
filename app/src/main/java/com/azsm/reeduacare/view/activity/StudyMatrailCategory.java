package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.StudyMatrial_Adapter;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.ProvideCourceModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudyMatrailCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    private Toolbar toolbar;
    private Bundle bundle;
    private String title, type;
    private ProgressBar progressBar;
    private String typeeid;
    TextView titletxt;
    private ArrayList<ProvideCourceModelClass> provideCourceModelClasses = new ArrayList<>();
    View null_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_matrail_category);
        titletxt = findViewById(R.id.textview);
        progressBar = findViewById(R.id.prograssbar);
        recyclerView = findViewById(R.id.providcourcerecycle);
        bundle = getIntent().getExtras();
        ImageView backpress = findViewById(R.id.backMyOrders);
        null_layout = findViewById(R.id.buybtnview);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });


        if (bundle != null) {
            //Toast.makeText(this, "title=>"+title, Toast.LENGTH_SHORT).show();

            title = bundle.getString("id");


        }

        getcourcecat();
    }

    private void getcourcecat() {

        progressBar.setVisibility(View.VISIBLE);

        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/type-to-category?type_id="+title;


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("status");

                            if (status.equals("200")) {


                                JSONArray whishlistarray = response.getJSONArray("typetoCategory");


                                for (int i = 0; i < whishlistarray.length(); i++) {
                                    JSONObject jsonObject = whishlistarray.getJSONObject(i);


                                    String courseid = jsonObject.getString("id");
                                    String course_title = jsonObject.getString("category_name");
                                    String course_image = jsonObject.getString("category_image");
                                    provideCourceModelClasses.add(new ProvideCourceModelClass("https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + course_image, course_title, courseid,title));


                                    @SuppressLint("WrongConstant") GridLayoutManager horizontalLayoutManager2
                                            = new GridLayoutManager(getApplicationContext(), 2);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    StudyMatrial_Adapter courceProviderAdapter = new StudyMatrial_Adapter(provideCourceModelClasses, StudyMatrailCategory.this);
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);
                                    null_layout.setVisibility(View.GONE);


                                }
                                if(whishlistarray.length()==0){
                                    null_layout.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }


                            } else {

                                Toast.makeText(StudyMatrailCategory.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                null_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(StudyMatrailCategory.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudyMatrailCategory.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {

            //    @Override
//            protected Map<String, String> getParams()  {
//                Map<String, String> params = new HashMap<>();
//                params.put("type_id",typeeid );
//                return params;
//            }

        };

        VolleySingleton.getInstance(StudyMatrailCategory.this).addToRequestQueue(jor);

    }


}


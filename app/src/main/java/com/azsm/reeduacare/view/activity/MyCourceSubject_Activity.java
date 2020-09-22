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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.StudyMatrial_Cource_Adapter;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.StudyMatrial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyCourceSubject_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<StudyMatrial> studyMatrials = new ArrayList<>();
    Toolbar toolbar;
    Bundle bundle;
    String id ;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cource_subject_);
        recyclerView = findViewById(R.id.recyclervieww);
        // toolbar.setTitle("Study Material");
        ImageView backpress = findViewById(R.id.backMyOrders);
        progressBar = findViewById(R.id.progressbar);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });
        bundle = getIntent().getExtras();
        if(bundle!=null){
            id = bundle.getString("catname");
        }


        getcourcecat();

    }



    private void getcourcecat() {

        progressBar.setVisibility(View.VISIBLE);

        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/category-to-course?cat_id=1&course_type_id=1";


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("status");

                            if (status.equals("200")) {


                                JSONArray whishlistarray = response.getJSONArray("course-information");


                                for (int i = 0; i < whishlistarray.length(); i++) {
                                    JSONObject jsonObject = whishlistarray.getJSONObject(i);


                                    String courseid = jsonObject.getString("id");
                                    String course_title = jsonObject.getString("course_name");
                                    String course_image = jsonObject.getString("course_image");
                                    studyMatrials.add(new StudyMatrial("https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + course_image,course_title, courseid));


                                    GridLayoutManager horizontalLayoutManager2
                                            = new GridLayoutManager(getApplicationContext(),2);;
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    StudyMatrial_Cource_Adapter courceProviderAdapter = new StudyMatrial_Cource_Adapter( studyMatrials,getApplicationContext());
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);


                                }


                            } else {

                                Toast.makeText(MyCourceSubject_Activity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MyCourceSubject_Activity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyCourceSubject_Activity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
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

        VolleySingleton.getInstance(MyCourceSubject_Activity.this).addToRequestQueue(jor);

    }

}


package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.StudyMatrialAdapter;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.StudyMatrial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudyMatrialActivity extends AppCompatActivity {
     private RecyclerView recyclerView;
     ArrayList<StudyMatrial> studyMatrials = new ArrayList<>();
     Toolbar toolbar;
     Bundle bundle;
     String id ,id2;
     ProgressBar progressBar;
     View null_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_matrial);
        recyclerView = findViewById(R.id.recyclervieww);
       // toolbar.setTitle("Study Material");
        ImageView backpress = findViewById(R.id.backMyOrders);
        null_layout = findViewById(R.id.buybtnview);
        progressBar = findViewById(R.id.progressbar);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });
        bundle = getIntent().getExtras();
        if(bundle!=null){
         id = bundle.getString("catname");
         id2 = bundle.getString("id");
        }


        getcourcecat();
       // Toast.makeText(this, ""+id+id2, Toast.LENGTH_SHORT).show();

    }



    private void getcourcecat() {

        progressBar.setVisibility(View.VISIBLE);

        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/category-to-course?cat_id="+id+"&course_type_id="+id2;


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
                                    StudyMatrialAdapter courceProviderAdapter = new StudyMatrialAdapter( studyMatrials,getApplicationContext());
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);
                                    null_layout.setVisibility(View.GONE);


                                }
                                if(whishlistarray.length()==0){
                                    null_layout.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }

                            } else {

                              //  Toast.makeText(StudyMatrialActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                null_layout.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(StudyMatrialActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            null_layout.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudyMatrialActivity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        null_layout.setVisibility(View.VISIBLE);
                    }
                }) {

            //    @Override
//            protected Map<String, String> getParams()  {
//                Map<String, String> params = new HashMap<>();
//                params.put("type_id",typeeid );
//                return params;
//            }

        };

        VolleySingleton.getInstance(StudyMatrialActivity.this).addToRequestQueue(jor);

    }

}

package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.azsm.reeduacare.view.adapter.CourceProviderAdapter;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.ProvideCourceModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourceCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private Toolbar toolbar;
    private Bundle bundle;
    private String title, type;
    private ProgressBar progressBar;
    private String typeeid;
    TextView titletxt;
    private ArrayList<ProvideCourceModelClass> provideCourceModelClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_course);
        titletxt = findViewById(R.id.textview);
        progressBar = findViewById(R.id.prograssbar);
        recyclerView = findViewById(R.id.providcourcerecycle);
        bundle = getIntent().getExtras();
        ImageView backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });


        if (bundle != null) {
            //Toast.makeText(this, "title=>"+title, Toast.LENGTH_SHORT).show();

            title = bundle.getString("tolbar");


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
                                    String price = jsonObject.getString("price");
                                    provideCourceModelClasses.add(new ProvideCourceModelClass("https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + course_image, course_title, courseid,price));


                                    @SuppressLint("WrongConstant") GridLayoutManager horizontalLayoutManager2
                                            = new GridLayoutManager(getApplicationContext(), 2);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    CourceProviderAdapter courceProviderAdapter = new CourceProviderAdapter(provideCourceModelClasses, CourceCategoryActivity.this);
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);


                                }


                            } else {

                                Toast.makeText(CourceCategoryActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CourceCategoryActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CourceCategoryActivity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
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

        VolleySingleton.getInstance(CourceCategoryActivity.this).addToRequestQueue(jor);

    }


}

package com.azsm.reeduacare.view.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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
import com.azsm.reeduacare.view.adapter.ActivetestAdapter;
import com.azsm.reeduacare.model.AtempttList;
import com.azsm.reeduacare.view.activity.MyCource_Tab_Layout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestSeries_Adeapter extends Fragment {


    private RecyclerView recyclerView;
    private List<AtempttList> atempttLists = new ArrayList<>();
    private ProgressBar progressBar;
    private String ain ;
    private TextView textView;

    public TestSeries_Adeapter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.active, container, false);
        recyclerView=view.findViewById(R.id.alldatarecycler);
        progressBar=view.findViewById(R.id.progressbar);
        textView = view.findViewById(R.id.active);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

      activequestionlist();

        return view;
    }
  private void activequestionlist() {
      progressBar.setVisibility(View.VISIBLE);
      RequestQueue queue = Volley.newRequestQueue(getContext());
      String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/subject-according?subject_id="+ MyCource_Tab_Layout.id;
      StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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


                                  String courseid=jsonObject.getString("test_id");
                                  String course_title=jsonObject.getString("category_name");
                                  String course_image=jsonObject.getString("category_image");
                                  atempttLists.add(new AtempttList(course_title,"t",course_image,courseid));
                                  LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                  recyclerView.setLayoutManager(layoutManager);
                                  layoutManager.setOrientation(RecyclerView.VERTICAL);
                                  ActivetestAdapter gridProductAdapter = new ActivetestAdapter(atempttLists, getContext());
                                  recyclerView.setAdapter(gridProductAdapter);
                                  gridProductAdapter.notifyDataSetChanged();
                                  progressBar.setVisibility(View.GONE);

                              }


                          } else {

                              // progressBar.setVisibility(View.VISIBLE);
                              Toast.makeText(getContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.GONE);
                          }
                      } catch (JSONException e) {
                          e.printStackTrace();
                          Toast.makeText(getContext(), "somrthing went wrong", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                      }
                  }
              },
              new Response.ErrorListener() {


                  @Override
                  public void onErrorResponse(VolleyError error) {
                      int errorCode = 0;
                      if (error instanceof TimeoutError) {
                          Toast.makeText(getContext(), "Timeout !!!!Try Again", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          errorCode = -7;
                      } else if (error instanceof NoConnectionError) {
                          Toast.makeText(getContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          errorCode = -1;
                      } else if (error instanceof AuthFailureError) {
                          Toast.makeText(getContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          errorCode = -6;
                      } else if (error instanceof ServerError) {
                          Toast.makeText(getContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          errorCode = 0;
                      } else if (error instanceof NetworkError) {
                          Toast.makeText(getContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          errorCode = -1;
                      } else if (error instanceof ParseError) {
                          Toast.makeText(getContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
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




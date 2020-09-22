package com.azsm.reeduacare.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
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
import com.azsm.reeduacare.view.adapter.StudyMatrialSuBcLAss;
import com.azsm.reeduacare.model.StudyMatrialSubtopic;
import com.azsm.reeduacare.view.activity.DoubtSectionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DoubtPdfSection extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<StudyMatrialSubtopic> studyMatrials = new ArrayList<>();
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doubt_pdf_section, container, false);
         recyclerView = view.findViewById(R.id.recyclervieww);
         progressBar  = view.findViewById(R.id.progressbarr);
          getAllVideos();
        return view;
    }


    private void getsubject(){


    }

    private void getAllVideos()
    {
        // Toast.makeText(DoubtSectionActivity.this, "keyword="+keyword, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        studyMatrials.clear();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section?user_id=1&dout_keyword="+ DoubtSectionActivity.searchkeyword;
       // String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(DoubtSectionActivity.this, "stats=>"+status, Toast.LENGTH_SHORT).show();

                            if (status.equals("200")) {

                                JSONArray whishlistarray=object.getJSONArray("dout");

                                for (int i=0;i<whishlistarray.length();i++)
                                {
                                    JSONObject jsonObject=whishlistarray.getJSONObject(i);


                                    String courseid=jsonObject.getString("id");
                                    String vediofile=jsonObject.getString("answer_images");
                                    String course_image=jsonObject.getString("question");

                                    studyMatrials.add(new StudyMatrialSubtopic("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+vediofile,course_image));

                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    StudyMatrialSuBcLAss courceProviderAdapter = new StudyMatrialSuBcLAss( studyMatrials,getContext());
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    // scanview.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);
                                    // scanviewww.setVisibility(View.GONE);

                                }


                            } if(status.equals("201")){
                                // scanview.animate().cancel();
                                progressBar.setVisibility(View.GONE);
                                // scanview.invalidate();
                                // scanview.setVisibility(View.GONE);
                                // scanviewww.setVisibility(View.GONE);
                               // getAllVideos();
                            }else {

                                // oopsimgview.setVisibility(View.VISIBLE);
                                // Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // scanview.setVisibility(View.GONE);
                                // scanviewww.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
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
                            // scanview.setVisibility(View.GONE);
                            // scanviewww.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // scanviewww.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }


}



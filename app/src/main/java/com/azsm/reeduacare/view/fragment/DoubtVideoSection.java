package com.azsm.reeduacare.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
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
import com.azsm.reeduacare.view.adapter.DoutSectionAdapter;
import com.azsm.reeduacare.model.DoutsectionAnswer;
import com.azsm.reeduacare.view.activity.DoubtSectionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class  DoubtVideoSection extends Fragment {
   private RecyclerView recyclerView;
    ArrayList<DoutsectionAnswer> doutsectionAnswers = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doubt_video_section, container, false);
        recyclerView =  view.findViewById(R.id.alldatarecycler);
        progressBar = view.findViewById(R.id.progressbar);

        getdoutanswer();
       // getAllVideos();
        if(!DoubtSectionActivity.searchkeyword.equals("null")) {
            progressBar.setVisibility(View.VISIBLE);
            getdoutanswer();
        }else {
            getAllVideos();
        }
        return  view;

    }


    private void getdoutanswer() {
        // Toast.makeText(getContext(), "keyword="+DoubtSectionActivity.searchkeyword, Toast.LENGTH_SHORT).show();
       // scanview.startAnimation(animation);
       // scanview.setVisibility(View.VISIBLE);
        doutsectionAnswers.clear();
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getContext());
         String url ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section?user_id=1&dout_keyword="+DoubtSectionActivity.searchkeyword;
       // String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section?user_id="+ SharedPrefManager.getInstance(getContext()).getUser().getUserid()+"&dout_keyword="+keyword+"&image="+"";

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
                                    String vediofile=jsonObject.getString("answer_videos");
                                     String course_image=jsonObject.getString("question");
                                    doutsectionAnswers.add(new DoutsectionAnswer("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+vediofile,course_image));

                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    DoutSectionAdapter examnamtionAdapter = new DoutSectionAdapter(doutsectionAnswers, getContext());
                                    recyclerView.setAdapter(examnamtionAdapter);
                                    //scanview.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);
                                    //scanviewww.setVisibility(View.GONE);

                                }


                            } if(status.equals("201")){
                               // Toast.makeText(getContext(), "hii", Toast.LENGTH_SHORT).show();
                               // scanview.animate().cancel();
                                progressBar.setVisibility(View.GONE);
                                //scanview.invalidate();
                               // scanview.setVisibility(View.GONE);
                               // scanviewww.setVisibility(View.GONE);
                                if(!DoubtSectionActivity.searchkeyword.equals("camera")){
                                showDialogueAfterPayment();
                                    getAllVideos();
                                }

                            }else {

                                // oopsimgview.setVisibility(View.VISIBLE);
                                // Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                //scanview.setVisibility(View.GONE);
                               // scanviewww.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Toast.makeText(getApplicationContext(), "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                           // scanview.setVisibility(View.GONE);
                           // scanviewww.setVisibility(View.GONE);
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
                            //scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                           // scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                           // scanviewww.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                           // scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                           // scanview.setVisibility(View.GONE);
                            //scanviewww.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }

    private void showDialogueAfterPayment()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle("Solution is not found");
        builder1.setMessage("we will notify your doubt solution with in 24 hr");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.atoozlogo);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }



    public class MyDoubtAdapter extends FragmentPagerAdapter {


        private Context myContext;
        int totalTabs;


        public MyDoubtAdapter(Context context, FragmentManager fm, int totalTabs) {
            super(fm);
            myContext = context;
            this.totalTabs = totalTabs;

        }

        // this is for fragment tabs
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    DoubtVideoSection myProducts = new DoubtVideoSection();
                    return myProducts;
                case 1:
                    DoubtPdfSection allProducts = new DoubtPdfSection();
                    return allProducts;

                default:
                    return null;
            }
        }
        // this counts total number of tabs
        @Override
        public int getCount() {
            return totalTabs;
        }
    }

    private void getAllVideos()
    {
        // Toast.makeText(DoubtSectionActivity.this, "keyword="+keyword, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section";

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
                                    String vediofile=jsonObject.getString("answer_videos");
                                    String course_image=jsonObject.getString("question");
                                    doutsectionAnswers.add(new DoutsectionAnswer("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+vediofile,course_image));

                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    DoutSectionAdapter examnamtionAdapter = new DoutSectionAdapter(doutsectionAnswers, getContext());
                                    recyclerView.setAdapter(examnamtionAdapter);
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
                                getAllVideos();
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

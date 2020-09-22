package com.azsm.reeduacare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.model.AtempttList;
import com.azsm.reeduacare.model.Score_cardDate;
import com.azsm.reeduacare.view.adapter.Result_Adapter;
import com.azsm.reeduacare.view.adapter.StudyMatrialAdapter;
import com.azsm.reeduacare.view.adapter.TestSeries_Question_Adapter;
import com.azsm.reeduacare.view.fragment.Inactive_Fragement;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultAnalysis extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private int[] yData ;
    private String[] xData ;
    PieChart pieChart;
    private RecyclerView recyclerView;
    private ArrayList<Score_cardDate>score_cardDates = new ArrayList<>();
    private String non_attempts,attempts;
    private ImageView backpress;
    private View null_layoiut,main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_analysis);
        recyclerView =  findViewById(R.id.recyclerview);
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        backpress = findViewById(R.id.backMyOrders);
        null_layoiut = findViewById(R.id.null_layout);
        main_layout = findViewById(R.id.layout_linear_layout);

        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

           activequestionlist();
          getscore();

       // pieChart.setDescription("Sales by employee (In Thousands $) ");

        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        //pieChart.setCenterText("resul");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

     //   addDataSet();

//        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                Log.d(TAG, "onValueSelected: Value select from chart.");
//                Log.d(TAG, "onValueSelected: " + e.toString());
//                Log.d(TAG, "onValueSelected: " + h.toString());
//
//                int pos1 = e.toString().indexOf("(sum): ");
//                String sales = e.toString().substring(pos1 + 7);
//
//                for(int i = 0; i < yData.length; i++){
//                    if(yData[i] == Float.parseFloat(sales)){
//                        pos1 = i;
//                        break;
//                    }
//                }
//                String employee = xData[pos1 + 1];
//             //   Toast.makeText(ResultAnalysis.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });

    }

    private void addDataSet() {

    }

    private void getscore(){



    }

    private void activequestionlist() {
        //Toast.makeText(getContext(), "id->"+TestSeriessQuestionActivity.id, Toast.LENGTH_SHORT).show();
        //progressBar.setVisibility(View.VISIBLE);
        score_cardDates.clear();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/view-list-test?user_id="+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();
                //+ SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(CourseProvide.this, "stats=>"+status, Toast.LENGTH_SHORT).show();
                            JSONObject whishlistarray=object.getJSONObject("viewuser");
                            if (status.equals("200")) {



                                    String  rank = whishlistarray.getString("rank");
                                    String mask = whishlistarray.getString("mask");
                                   //  attempts = whishlistarray.getString("attempts");
                                     String correct = whishlistarray.getString("correct");
                                     String incorrect = whishlistarray.getString("incorrect");
                                     non_attempts = whishlistarray.getString("un_attempt");
                                    String percentage = whishlistarray.getString("percentile");
                                    String no_of_students = whishlistarray.getString("no_of_students");
                                    //int correct = Integer.parseInt( attempts)-Integer.parseInt(non_attempts);
                                       try {
                                           yData = new int[]{ Integer.parseInt(incorrect), Integer.parseInt(correct), Integer.parseInt(non_attempts)};

                                       }catch (Exception e){

                                       }

                                        xData = new String[]{"correct", "Icorect","utempt"};
                                        Log.d(TAG, "addDataSet started");
                                        ArrayList<PieEntry> yEntrys = new ArrayList<>();
                                        ArrayList<String> xEntrys = new ArrayList<>();

                                        for(int i = 0; i < yData.length; i++){
                                            yEntrys.add(new PieEntry(yData[i] , i));
                                        }

                                        for(int i = 1; i < xData.length; i++){
                                            xEntrys.add(xData[i]);
                                        }

                                        //create the data set
                                        PieDataSet pieDataSet = new PieDataSet(yEntrys, " ");
                                        pieDataSet.setSliceSpace(2);
                                        pieDataSet.setValueTextSize(12);

                                        //add colors to dataset
                                        ArrayList<Integer> colors = new ArrayList<>();

//        colors.add(Color.BLUE);
                                        colors.add(Color.RED);
                                        colors.add(Color.GREEN);
                                        colors.add(Color.GRAY);
//        colors.add(Color.CYAN);
//        colors.add(Color.YELLOW);
//        colors.add(Color.MAGENTA);

                                        pieDataSet.setColors(colors);
                                      Legend leg = pieChart.getLegend();
                                           leg.setEnabled(false);
                                      pieChart.setDescription(null);
                                        //add legend to chart
                                        //Legend legend = pieChart.getLegend();
                                        // legend.setForm(Legend.LegendForm.CIRCLE);
                                        //legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

                                        //create pie data object
                                        PieData pieData = new PieData(pieDataSet);
                                        pieChart.setData(pieData);


                                      pieChart.invalidate();
                                        activequestionlist();

                                      if(rank.equals("null")){
                                          rank ="0";
                                      }


                                   // score_cardDates.add(new Score_cardDate("My Rank",R.drawable.rank,rank));
                                    score_cardDates.add(new Score_cardDate("Number of student",R.drawable.people,no_of_students));
                                    score_cardDates.add(new Score_cardDate("My Marks",R.drawable.rank,mask));
                                    score_cardDates.add(new Score_cardDate("Unattempted",R.drawable.accrucy,non_attempts));
                                    score_cardDates.add(new Score_cardDate("Percentile",R.drawable.percentage,percentage+"%"));
                                    score_cardDates.add(new Score_cardDate("Correct Answer",R.drawable.tikmark,correct));
                                score_cardDates.add(new Score_cardDate("Incorrect Answer",R.drawable.places_ic_clear,incorrect));
                                    GridLayoutManager horizontalLayoutManager2
                                            = new GridLayoutManager(getApplicationContext(),2);;
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    Result_Adapter courceProviderAdapter = new Result_Adapter( score_cardDates,getApplicationContext());
                                    recyclerView.setAdapter(courceProviderAdapter);
                                    null_layoiut.setVisibility(View.GONE);




                            } else {

                                // progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),"No result found", Toast.LENGTH_SHORT).show();
                                null_layoiut.setVisibility(View.VISIBLE);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No result found", Toast.LENGTH_SHORT).show();
                            //null_layoiut.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Timeout !!!!Try Again", Toast.LENGTH_SHORT).show();
                           //progressBar.setVisibility(View.GONE);
                           // null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                          //  progressBar.setVisibility(View.GONE);
                            //null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                           // null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                          //  null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                           // null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                           // null_layoiut.setVisibility(View.VISIBLE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }




}
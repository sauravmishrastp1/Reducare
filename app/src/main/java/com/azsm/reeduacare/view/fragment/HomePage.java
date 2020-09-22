package com.azsm.reeduacare.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
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
import com.azsm.reeduacare.view.adapter.BannerSliderAdapter;
import com.azsm.reeduacare.view.adapter.UserCourcesAdapter;
import com.azsm.reeduacare.api.Url;
import com.azsm.reeduacare.model.BannerSliderModel;
import com.azsm.reeduacare.model.CategoryModel;
import com.azsm.reeduacare.model.UserCourcesModel;
import com.azsm.reeduacare.model.VideoContentMoel;
import com.azsm.reeduacare.model.VideosModel;
import com.azsm.reeduacare.view.activity.DoubtSectionActivity;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class HomePage extends Fragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView mostpopularrecycler;
    private List<VideosModel> videosModelArrayList=new ArrayList<>();
    private SliderView sliderView;
    private List<BannerSliderModel>bannerSliderModelList;
    private RecyclerView trendingrecycler,latestrecycler;
    private ProgressBar progressBar;
    private List<UserCourcesModel>latestcourselist=new ArrayList<>();
    private List<UserCourcesModel>mostpopularlist=new ArrayList<>();
    private List<UserCourcesModel>trendingcourselist=new ArrayList<>();
    private List<CategoryModel>categoryModelList=new ArrayList<>();
    private List<VideoContentMoel>videoslist=new ArrayList<>();
    private RecyclerView valueformoneyrecyclerview;
    private List<UserCourcesModel>valueformoneylist=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    public HomePage activity;
    private FloatingActionButton floatingActionButton;
    private View latestcourseview,trendingcourseview,mostpopularview,valueformoneyview;
    private static final int CAMERA_REQUEST = 1888;
    private EditText searcheditText ;
    private ImageView doutcamera,searchimg;
    private TextView textViewor;
    private String keyword;
    private Uri imgurii;
    private ImageView croperimg;

    private static final int GALLERY_IMAGE = 1;
    AsyncTask<Bitmap, Void, String> theTask;
    private static final int PHOTO_REQUEST_CAMERA = 0;//camera
    private static final int PHOTO_REQUEST_GALLERY = 1;//gallery
    private static final int PHOTO_REQUEST_CUT = 2;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView=view.findViewById(R.id.courcesrecycler);
        sliderView=view.findViewById(R.id.imageSlider);
        croperimg = view.findViewById(R.id.imageviewcrop);
        textViewor = view.findViewById(R.id.textviewor);
        doutcamera = view.findViewById(R.id.doutcameraa);
        searchimg = view.findViewById(R.id.searchicon);
        searcheditText = view.findViewById(R.id.search_eT);
        mostpopularrecycler=view.findViewById(R.id.mostpopularrecycler);
        trendingrecycler=view.findViewById(R.id.trendingrecycler);
        floatingActionButton = view.findViewById(R.id.fab);
        latestrecycler=view.findViewById(R.id.latestrecycler);
        progressBar=view.findViewById(R.id.homeprogressbar);
        valueformoneyrecyclerview=view.findViewById(R.id.valueforoneyrecycler);
        swipeRefreshLayout=view.findViewById(R.id.swipuplayout);
        latestcourseview=view.findViewById(R.id.ltcourshead);
        trendingcourseview=view.findViewById(R.id.trendcourseview);
        mostpopularview=view.findViewById(R.id.mostpopularview);
        valueformoneyview=view.findViewById(R.id.valueformonewview);
        searcheditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = searcheditText.getText().toString();
                // or String txt = s.toString();
                if( !keyword.isEmpty() ) {
                    searchimg.setVisibility(View.VISIBLE);
                    textViewor.setVisibility(View.GONE);
                    doutcamera.setVisibility(View.GONE);

                }
                else {
                    searchimg.setVisibility(View.GONE);
                    textViewor.setVisibility(View.VISIBLE);
                    doutcamera.setVisibility(View.VISIBLE);
                }
            }
        });
        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = searcheditText.getText().toString();
                Intent intent=new Intent(getActivity(), DoubtSectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("keyword",keyword);
                startActivity(intent);
                getActivity().finish();
            }
        });


        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mostpopularrecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager valuelinearLayoutManager=new LinearLayoutManager(getContext());
        valueformoneyrecyclerview.setLayoutManager(valuelinearLayoutManager);
        valuelinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);


        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        trendingrecycler.setLayoutManager(linearLayoutManager1);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext());
        latestrecycler.setLayoutManager(linearLayoutManager2);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);

        // Toast.makeText(getContext(), "id="+ SharedPrefManager.getInstance(getContext()).getUser().getUserid(), Toast.LENGTH_SHORT).show();

        progressBar.setVisibility(View.VISIBLE);

        showHomeData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
                showHomeData();

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Intent intent=new Intent(getActivity(), DoubtSectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();


            }



        });
        doutcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DoubtSectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("keyword","camera");
                startActivity(intent);
                getActivity().finish();


            }
        });
        croperimg.setImageBitmap(null);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri uriresult = CropImage.getPickImageResultUri(getContext(), data);
            if (CropImage.isReadExternalStoragePermissionsRequired(getContext(), uriresult)) {
                imgurii = uriresult;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                startcropimg(uriresult);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                croperimg.setImageURI(result.getUri());
                Toast.makeText(getContext(), "hii", Toast.LENGTH_SHORT).show();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) croperimg.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getContext()).build();
                if (!recognizer.isOperational()) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> item = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < item.size(); i++) {
                        // Toast.makeText(DoubtSectionActivity.this, "hii", Toast.LENGTH_SHORT).show();
                        TextBlock myitem = item.valueAt(i);
                        sb.append(myitem.getValue());
                        sb.append("\n");

                    }

                    Toast.makeText(getContext(), "string="+sb.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void startcropimg(Uri uri) {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(getActivity());

    }


    private void showHomeData()
    {

        progressBar.setVisibility(View.VISIBLE);
        bannerSliderModelList=new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SHOW_HOME_DATA,

                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);
                            String status = object.getString("status");
                            // Toast.makeText(getContext(), "hii=>"+status, Toast.LENGTH_SHORT).show();

                            JSONArray bannerarray = object.getJSONArray("bannerimages");


//                                JSONArray category=object.getJSONArray("category");
//                                JSONArray valueformony=object.getJSONArray("value-for-mony");

                            if (status.equals("200")) {



                                for (int i=0;i<bannerarray.length();i++)
                                {

                                    JSONObject jsonObject = bannerarray.getJSONObject(i);
                                    String bannerimage=jsonObject.getString("image");
                                    String id=jsonObject.getString("id");
                                    //  Toast.makeText(getContext(), "img=>"+bannerimage, Toast.LENGTH_SHORT).show();

                                    bannerSliderModelList.add(new BannerSliderModel("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+bannerimage,id));


                                }

                                BannerSliderAdapter bannerSliderAdapter = new BannerSliderAdapter(bannerSliderModelList, getContext());
                                sliderView.setSliderAdapter(bannerSliderAdapter);
                                sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                                sliderView.setIndicatorSelectedColor(Color.parseColor("#00264d"));
                                sliderView.setIndicatorUnselectedColor(Color.WHITE);
                                sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
                                sliderView.startAutoCycle();
                                progressBar.setVisibility(View.GONE);


                                JSONArray latestcoursearray=object.getJSONArray("course");

                                for (int i=0;i<latestcoursearray.length();i++)
                                {
                                    JSONObject jsonObject=latestcoursearray.getJSONObject(i);

                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("category_name");
                                    // String course_category=jsonObject.getString("course_category");
                                    String course_description=jsonObject.getString("description");
                                    String course_image=jsonObject.getString("category_image");
                                    String price=jsonObject.getString("price");
                                    String rating= "3.5";
                                    // String islive=jsonObject.getString("is_live");
//                                    String createdbyname=jsonObject.getString("name");
//                                    String createdbyemail=jsonObject.getString("email");
                                    latestcourselist.add(new UserCourcesModel(course_title,"",course_description,price,"islive",courseid,"10",course_image,Float.parseFloat(rating),"createdbyname","createdbyemail"));

                                }

                                if (latestcourselist.size()==0)
                                {
                                    latestcourseview.setVisibility(View.GONE);
                                }
                                else {
                                    UserCourcesAdapter latestadapter = new UserCourcesAdapter(getActivity(), latestcourselist, "2");
                                    latestrecycler.setAdapter(latestadapter);
                                    latestadapter.notifyDataSetChanged();
                                }
                                JSONArray trendingcoursearray=object.getJSONArray("test_series");

                                for (int i=0;i<trendingcoursearray.length();i++)
                                {
                                    JSONObject jsonObject=trendingcoursearray.getJSONObject(i);

                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("category_name");
                                    String course_description=jsonObject.getString("description");
                                    String course_image=jsonObject.getString("category_image");
                                    String price=jsonObject.getString("price");
                                    String rating= "3.5";
                                    //  String islive=jsonObject.getString("is_live");
//                                    String createdbyname=jsonObject.getString("name");
//                                    String createdbyemail=jsonObject.getString("email");
                                    trendingcourselist.add(new UserCourcesModel(course_title,"",course_description,price,"islive",courseid,"10",course_image,Float.parseFloat(rating),"createdbyname","createdbyemail"));

                                }
                                if (trendingcourselist.size()==0)
                                {
                                    trendingcourseview.setVisibility(View.GONE);
                                }
                                else {
                                    UserCourcesAdapter trendingadapter = new UserCourcesAdapter(getActivity(), trendingcourselist, "2");
                                    trendingrecycler.setAdapter(trendingadapter);
                                    trendingadapter.notifyDataSetChanged();
                                }

                                JSONArray mostpopulararray=object.getJSONArray("studymatrial");
                                for (int i=0;i<mostpopulararray.length();i++)
                                {
                                    JSONObject jsonObject=mostpopulararray.getJSONObject(i);

                                    String courseid=jsonObject.getString("id");
                                    String course_title=jsonObject.getString("category_name");
                                    String course_category="maths";
                                    String course_description="maths";
                                    String course_image=jsonObject.getString("category_image");
                                    String price="200";
                                    String rating= "3.5";
                                    // String islive=jsonObject.getString("is_live");
//                                    String createdbyname=jsonObject.getString("name");
//                                    String createdbyemail=jsonObject.getString("email");
                                    mostpopularlist.add(new UserCourcesModel(course_title,course_category,course_description,price,"islive",courseid,"10",course_image,Float.parseFloat(rating),"createdbyname","createdbyemail"));

                                }

                                if (mostpopularlist.size()==0)
                                {
                                    mostpopularview.setVisibility(View.GONE);
                                }
                                else {
                                    UserCourcesAdapter mostpopularadapter = new UserCourcesAdapter(getActivity(), mostpopularlist, "2");
                                    mostpopularrecycler.setAdapter(mostpopularadapter);
                                    mostpopularadapter.notifyDataSetChanged();
                                }

//                                for (int i=0;i<valueformony.length();i++)
//                                {
//                                    JSONObject jsonObject=valueformony.getJSONObject(i);
//
//                                    String courseid=jsonObject.getString("id");
//                                    String course_title=jsonObject.getString("course_name");
//                                    String course_category=jsonObject.getString("course_category");
//                                    String course_description=jsonObject.getString("course_discription");
//                                    String course_image=jsonObject.getString("course_image");
//                                    String price=jsonObject.getString("price");
//                                    String rating= "3.5";
//                                   // String islive=jsonObject.getString("is_live");
////                                    String createdbyname=jsonObject.getString("name");
////                                    String createdbyemail=jsonObject.getString("email");
//                                    valueformoneylist.add(new UserCourcesModel(course_title,course_category,course_description,price,"islive",courseid,"10",course_image,Float.parseFloat(rating),"createdbyname","createdbyemail"));
//
//                                }
//
//                                if (valueformoneylist.size()==0)
//                                {
//                                    valueformoneyview.setVisibility(View.GONE);
//                                }
//                                else {
//
//                                    UserCourcesAdapter valueformoneyadapter = new UserCourcesAdapter(getActivity(), valueformoneylist, "2");
//                                    valueformoneyrecyclerview.setAdapter(valueformoneyadapter);
//                                    valueformoneyadapter.notifyDataSetChanged();
//                                }
//
////
//                                for (int i=0;i<category.length();i++)
//                                {
//                                    JSONObject jsonObject=category.getJSONObject(i);
//
//                                    String catid=jsonObject.getString("id");
//                                    String category_name=jsonObject.getString("category_name");
//                                    String category_icon=jsonObject.getString("category_image");
//                                   categoryModelList.add(new CategoryModel(category_name,category_icon,R.drawable.gradient1,catid));
//
//                                }


//                                CourseAdapter courseAdapter=new CourseAdapter(getActivity(),categoryModelList,"2");
//                                recyclerView.setAdapter(courseAdapter);
//                                courseAdapter.notifyDataSetChanged();
//                                progressBar.setVisibility(View.GONE);
//
//
//

                            } else {

                                Toast.makeText(getContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                          //  Toast.makeText(getContext(), "Somthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            // Toast.makeText(getActivity(), "Timeout Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                          //  Toast.makeText(getContext(), "No Connection Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getContext(), "AuthFailure Error Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getContext(), "Server Error Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getContext(), "Network error Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getContext(), "Server rror Try Again", Toast.LENGTH_SHORT).show();
                            mostpopularview.setVisibility(View.GONE);
                            latestcourseview.setVisibility(View.GONE);
                            trendingcourseview.setVisibility(View.GONE);
                            valueformoneyview.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

            @Override
            public void deliverError(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Cache.Entry entry = this.getCacheEntry();
                    if(entry != null) {
                        Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                        deliverResponse(response.result);
                        return;
                    }
                }
                super.deliverError(error);
            }

        };

        queue.getCache().clear();
        queue.getCache().remove(Url.GET_USER_COURCES);
        queue.add(stringRequest);

    }


}

package com.azsm.reeduacare.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.azsm.reeduacare.view.adapter.DoutSectionAdapter;
import com.azsm.reeduacare.constant.MultiPartHelperClass;
import com.azsm.reeduacare.constant.NetworkClient;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.UploadApis;
import com.azsm.reeduacare.model.DoutsectionAnswer;
import com.azsm.reeduacare.model.Example;
import com.azsm.reeduacare.view.fragment.DoubtPdfSection;
import com.azsm.reeduacare.view.fragment.DoubtVideoSection;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.tabs.TabLayout;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DoubtSectionActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private Activity activity;
    private ImageView croperimg, search;
    Bitmap image;
    String targetPath;
    private static final int GALLERY_IMAGE = 1;
    AsyncTask<Bitmap, Void, String> theTask;
    private static final int PHOTO_REQUEST_CAMERA = 0;//camera
    private static final int PHOTO_REQUEST_GALLERY = 1;//gallery
    private static final int PHOTO_REQUEST_CUT = 2;//image crop
    String datapath = "";
    ArrayList<DoutsectionAnswer> doutsectionAnswers = new ArrayList<>();

    public static final String PACKAGE_NAME = "com.datumdroid.android.ocr.simple";
    public static final String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/SimpleAndroidOCR/";

    // You should have the trained data file in assets folder
    // You can get them at:
    // https://github.com/tesseract-ocr/tessdata
    public static final String lang = "eng";

    private static final String TAG = "SimpleAndroidOCR.java";

    protected Button _button;
    // protected ImageView _image;
    protected EditText _field;
    protected String _path;
    protected boolean _taken;

    protected static final String PHOTO_TAKEN = "photo_taken";

    public static final String TESS_DATA = "/tessdata";
    private TextView tv;
    private TessBaseAPI tessBaseAPI;
    private Uri outputFileDir;
    static final int PHOTO_REQUEST_CODE = 1;
    private Uri imguri;

    private String filePathpic = "";
    private String extension;
    private String type = "";
    private Button editprofilbtn;

    private byte pic[] = "00.00.00".getBytes();

    private TextRecognizer recognizer;

    private Paint paint = new Paint();
    private int mPosY = 0;
    private boolean runAnimation = true;
    private boolean showLine = true;
    private Handler handler;
    private Runnable refreshRunnable;
    private boolean isGoingDown = true;
    private int mHeight;
    private int DELAY = 0;
    private View scanview;
    private ProgressBar progressBar;
    private Animation animation;
    private EditText searcheditText;
    private ImageView doutcamera, searchimg;
    private TextView textViewor;
    public static String searchkeyword = "null", camera;
    private Bundle bundle;
    private RelativeLayout scanviewww;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView waht_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt_section);
        croperimg = findViewById(R.id.cropedimg);
        waht_app = findViewById(R.id.whatsapp);
        textViewor = findViewById(R.id.textviewor);
        doutcamera = findViewById(R.id.doutcameraa);
        scanviewww = findViewById(R.id.scanviewlayout);
        searchimg = findViewById(R.id.searchicon);
        searcheditText = findViewById(R.id.search_eT);
        progressBar = findViewById(R.id.progressbar);
        search = findViewById(R.id.imageviewsearch);
        toolbar = findViewById(R.id.toolbar);
        scanview = findViewById(R.id.sacnview);
        recyclerView = findViewById(R.id.recycelrview);
        imageView = findViewById(R.id.doutcameraa);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Doubt Section");

        waht_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+"+91"+"6367319808";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //image = BitmapFactory.decodeResource(getResources(), R.drawable.test_image);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoubtSectionActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });


        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Pdf Notes"));


        final MyDoubtAdapter adapter = new MyDoubtAdapter(getApplicationContext(), getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        animation = AnimationUtils.loadAnimation(DoubtSectionActivity.this, R.anim.anim1);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scanview.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
//
        bundle = getIntent().getExtras();
        if (bundle != null) {

            searchkeyword = bundle.getString("keyword");
            if (searchkeyword.contains("camera")) {
                CropImage.startPickImageActivity(DoubtSectionActivity.this);
                scanviewww.setVisibility(View.VISIBLE);
                searchimg.setVisibility(View.GONE);
                textViewor.setVisibility(View.VISIBLE
                );
                if (searchkeyword.contains("Search Your Doubt")) {
                    searchimg.setVisibility(View.GONE);
                    textViewor.setVisibility(View.VISIBLE);
                    doutcamera.setVisibility(View.VISIBLE);
                    scanviewww.setVisibility(View.GONE);
                }

            } else {
                scanviewww.setVisibility(View.GONE);
                //getdoutanswer(searchkeyword);
                searchimg.setVisibility(View.GONE);
                searcheditText.setText(searchkeyword);
                imageView.setVisibility(View.VISIBLE);
                textViewor.setVisibility(View.GONE);


            }


        }
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
                searchkeyword = searcheditText.getText().toString();
                // or String txt = s.toString();
                if (!searchkeyword.isEmpty()) {
                    searchimg.setVisibility(View.VISIBLE);
                    textViewor.setVisibility(View.GONE);
                    doutcamera.setVisibility(View.GONE);

                } else {
                    searchimg.setVisibility(View.GONE);
                    textViewor.setVisibility(View.VISIBLE);
                    doutcamera.setVisibility(View.VISIBLE);
                }
            }
        });
        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchkeyword = searcheditText.getText().toString();
                scanviewww.setVisibility(View.GONE);


                final MyDoubtAdapter adapter = new MyDoubtAdapter(getApplicationContext(), getSupportFragmentManager(), tabLayout.getTabCount());
                viewPager.setAdapter(adapter);

                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });


                // getdoutanswer(searchkeyword);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(DoubtSectionActivity.this);

                scanviewww.setVisibility(View.VISIBLE);


            }
        });
        croperimg.setImageBitmap(null);
        // getAllVideos();



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri uriresult = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, uriresult)) {
                imguri = uriresult;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                startcropimg(uriresult);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                filePathpic = String.valueOf(result.getUri());
                croperimg.setImageURI(result.getUri());
                BitmapDrawable bitmapDrawable = (BitmapDrawable) croperimg.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(DoubtSectionActivity.this).build();
                if (!recognizer.isOperational()) {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
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
                    searchkeyword = sb.toString();
                    uploadImage();

                    final MyDoubtAdapter adapter = new MyDoubtAdapter(getApplicationContext(), getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(adapter);

                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());

                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });


                    //getdoutanswer(searchkeyword);
                    // Toast.makeText(DoubtSectionActivity.this, "sb=>"+sb.toString(), Toast.LENGTH_SHORT).show();


                }
            }
        }
    }

    private void startcropimg(Uri uri) {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);

    }

    public void buttonClick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(DoubtSectionActivity.this);


            }
        });
        croperimg.setImageBitmap(null);

    }

    private void setHasOptionsMenu(boolean b) {
    }

    private void getdoutanswer(final String keyword) {
        // Toast.makeText(DoubtSectionActivity.this, "keyword="+keyword, Toast.LENGTH_SHORT).show();
        scanview.startAnimation(animation);
        scanview.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section?user_id="+SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid() + "&dout_keyword="+keyword;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(DoubtSectionActivity.this, "stats=>"+status, Toast.LENGTH_SHORT).show();

                            if (status.equals("200")) {

                                JSONArray whishlistarray = object.getJSONArray("dout");

                                for (int i = 0; i < whishlistarray.length(); i++) {
                                    JSONObject jsonObject = whishlistarray.getJSONObject(i);


                                    String courseid = jsonObject.getString("id");
                                    String vediofile = jsonObject.getString("answer_videos");
                                     String course_image=jsonObject.getString("answer_text");
                                    doutsectionAnswers.add(new DoutsectionAnswer("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+vediofile,course_image));

                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(DoubtSectionActivity.this, LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    DoutSectionAdapter examnamtionAdapter = new DoutSectionAdapter(doutsectionAnswers, DoubtSectionActivity.this);
                                    recyclerView.setAdapter(examnamtionAdapter);
                                    scanview.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);
                                    scanviewww.setVisibility(View.GONE);

                                }


                            }
                            if (status.equals("201")) {
                                scanview.animate().cancel();
                                progressBar.setVisibility(View.GONE);
                                scanview.invalidate();
                                scanview.setVisibility(View.GONE);
                                scanviewww.setVisibility(View.GONE);
                                getAllVideos();
                                uploadImage();

                            } else {

                                // oopsimgview.setVisibility(View.VISIBLE);
                                // Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                scanview.setVisibility(View.GONE);
                                scanviewww.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Toast.makeText(getApplicationContext(), "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
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
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

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

    private void getAllVideos() {
        // Toast.makeText(DoubtSectionActivity.this, "keyword="+keyword, Toast.LENGTH_SHORT).show();
        doutsectionAnswers.clear();
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/dout-section";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);

                            String status = object.getString("status");
                            // Toast.makeText(DoubtSectionActivity.this, "stats=>"+status, Toast.LENGTH_SHORT).show();

                            if (status.equals("200")) {

                                JSONArray whishlistarray = object.getJSONArray("dout");

                                for (int i = 0; i < whishlistarray.length(); i++) {
                                    JSONObject jsonObject = whishlistarray.getJSONObject(i);


                                    String courseid = jsonObject.getString("id");
                                    String vediofile = jsonObject.getString("answer_videos");
                                    String course_image=jsonObject.getString("answer_text");
                                    doutsectionAnswers.add(new DoutsectionAnswer("https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + vediofile,course_image));

                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(DoubtSectionActivity.this, LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    DoutSectionAdapter examnamtionAdapter = new DoutSectionAdapter(doutsectionAnswers, DoubtSectionActivity.this);
                                    recyclerView.setAdapter(examnamtionAdapter);
                                    scanview.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);
                                    scanviewww.setVisibility(View.GONE);

                                }


                            }
                            if (status.equals("201")) {
                                scanview.animate().cancel();
                                progressBar.setVisibility(View.GONE);
                                scanview.invalidate();
                                scanview.setVisibility(View.GONE);
                                scanviewww.setVisibility(View.GONE);
                                getAllVideos();
                            } else {

                                // oopsimgview.setVisibility(View.VISIBLE);
                                // Toast.makeText(getApplicationContext(),"No Course Added ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                scanview.setVisibility(View.GONE);
                                scanviewww.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(), "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
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
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error !!!Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror!!! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scanview.setVisibility(View.GONE);
                            scanviewww.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dilogbox() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DoubtSectionActivity.this);
        builder1.setMessage("Match Not Found! we will send solution with in 24 hr");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.atoozlogo);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //uploadImage();

                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void uploadImage() {


            File file = new File(String.valueOf(imguri));
            Retrofit retrofit = NetworkClient.getRetrofit();
            MultiPartHelperClass.getMultipartData(new File(filePathpic), "user_image");

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), filePathpic);
            MultipartBody.Part parts = MultipartBody.Part.createFormData("newimage", file.getName(), requestBody);


            //  RequestBody someData = RequestBody.create(MediaType.parse("text/plain"), "This is a new Image");

            UploadApis uploadApis = retrofit.create(UploadApis.class);
            Call call = uploadApis.doubtsection(SharedPrefManager.getInstance(getApplicationContext()).getUser().getName(),searchkeyword,MultiPartHelperClass.getMultipartData(new File(filePathpic), "image"));
            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(retrofit2.Call<Example> call, retrofit2.Response<Example> response) {
                    try {
                        assert response.body() != null;
                        if (!response.body().getStatus().equals("200")) {
                            Toast.makeText(DoubtSectionActivity.this, "okk", Toast.LENGTH_SHORT).show();

                        }

                    } catch (Exception e) {
                       // progressDialog.dismiss();
//                        AlertDialog.Builder builder1 = new AlertDialog.Builder(DoubtSectionActivity.this);
//                        builder1.setMessage("Thanku! Your Details is Submit");
//                        builder1.setCancelable(true);
//                        builder1.setIcon(R.drawable.atoozlogo);
//
//                        builder1.setPositiveButton(
//                                "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        Intent intent = new Intent(DoubtSectionActivity.this, Main2Activity.class);
//                                        startActivity(intent);
//
//                                    }
//                                });
//
//
//                        AlertDialog alert11 = builder1.create();
//                        alert11.show();
                    }


                }

                @Override
                public void onFailure(Call call, Throwable t) {
                   // progressDialog.dismiss();
                }
            });
        }


}

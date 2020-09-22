package com.azsm.reeduacare.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.Demo_Vedio_Adapter;
import com.azsm.reeduacare.view.adapter.VideoContentAdapter;
import com.azsm.reeduacare.api.Url;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.Demo_Viedo_Model;
import com.azsm.reeduacare.model.UserFeedbackModel;
import com.azsm.reeduacare.model.VideoContentMoel;
import com.azsm.reeduacare.model.VideoModel;
import com.paykun.sdk.eventbus.Events;
import com.paykun.sdk.eventbus.GlobalBus;
import com.paykun.sdk.helper.PaykunHelper;
import com.razorpay.Checkout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoDetailsActivity extends AppCompatActivity {


    private Menu menu;
   // private Toolbar toolbar;
    private TextView discriptiontv;
    private TextView seemoretv;
    private String courseimage,description;
    private View playvideo;
    private ImageView thumbnailview;
    private CardView addtowhishlistcard;
    private TextView pricetv;
    private String courseprice,courserating,username,coursetitle;
    private List<String> videolist=new ArrayList<>();
    private List<UserFeedbackModel> userFeedbackModelList;
    private RecyclerView recyclerView;
    private TextView coursetitletv,bottompricetv,videosubtitletv;
    private ArrayList<Demo_Viedo_Model>demo_viedo_models = new ArrayList<>();
    private List<VideoContentMoel> videoContentMoelList=new ArrayList<>();
    private RecyclerView commenysrecylerview;
    private ArrayList<VideoModel> videoModelList=new ArrayList<>();
    private String courseid;
    private Button buyButton;
    private ProgressBar progressBar;
    private ImageView expandbtn;
    private boolean isexpanded=false;
    private boolean isdesexanded=false;
    private TextView videotittleheading,averageratingtv;
    private String type,creatername,createremail;
    private View priceview;
    private Button submitratingbtn;
    private EditText ratingEt;
    private RatingBar addratingbar;
    private View addratingview,totalratingview;
    private ProgressBar feedbackprogressbar;
    private String transid,orderid,msg;
    private ProgressBar fivestarprogres,fourstarprogress,threestarprogress,twostarprogress,onestarprogress;
    private TextView fivestarcount,fourstarcount,threestarcount,twostarcount,onestarcount,vieallfeedbacktv,creaternametv,createremailtv;
    private View ratingprogressbarcontainer;
    private String userid;
    private TextView totalratingandidate;
    private TextView viedo,stduymatria,testseries;
    private RecyclerView demorecy;
    private VideoView videoView;
    private ImageView play_btn;
    private String transactionid;
    private ImageView backpress;
    private int final_amountl;
    private String price_res;
    private String newDateStr;
    private String paid="null";
    private ImageView whatasspimag;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);
        demorecy = findViewById(R.id.demorecy);
        play_btn = findViewById(R.id.play_btn);
        expandbtn=findViewById(R.id.expandbtn);
        videoView = findViewById(R.id.thumbnaill);
        whatasspimag = findViewById(R.id.whatsapp);
       // toolbar =  findViewById(R.id.toolbar);
        viedo = findViewById(R.id.textvideo_txt);
        stduymatria = findViewById(R.id.study_matrial_txt);
        testseries = findViewById(R.id.testseries_txt);
        discriptiontv=findViewById(R.id.videodiscription);
        seemoretv=findViewById(R.id.showmoretv);
        playvideo=findViewById(R.id.videoview);
        thumbnailview=findViewById(R.id.thumbnail);
        pricetv=findViewById(R.id.pricetv);
        recyclerView=findViewById(R.id.videolistrecycler);
        coursetitletv=findViewById(R.id.coursetitle);

        bottompricetv=findViewById(R.id.bottompricetv);
        videosubtitletv=findViewById(R.id.videosubtitle);
        progressBar=findViewById(R.id.videolistprogressbar);
        expandbtn=findViewById(R.id.expandbtn);
        videotittleheading=findViewById(R.id.videolistheading);
        addtowhishlistcard=findViewById(R.id.addtowhishlist);
        buyButton=findViewById(R.id.buynowbtn);
        priceview=findViewById(R.id.priceview);
        ImageView backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });


        creaternametv=findViewById(R.id.creaternametv);
        createremailtv=findViewById(R.id.createremail);

       // setSupportActionBar(toolbar);

        userid= SharedPrefManager.getInstance(VideoDetailsActivity.this).getUser().getUserid();
       // Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();
        final Bundle bundle=getIntent().getExtras();
        Checkout.preload(getApplicationContext());



        if (bundle!=null)
        {
            courseimage=bundle.getString("cimage");
            price_res=bundle.getString("cprice");
            coursetitle=bundle.getString("ctitle");
            courserating=bundle.getString("totalrating");
            description=bundle.getString("cdesc");
            courseid=bundle.getString("courseid");
            creatername=bundle.getString("creatername");
            createremail=bundle.getString("createremail");
            type=bundle.getString("intenttype");

           //Toast.makeText(this, "c=>"+price_res, Toast.LENGTH_SHORT).show();

        }
        whatasspimag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+"+91"+"6367319808";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        getdemoviedo();
        getAllVideos();
       // getAllRatingAverageAndCount();

//        if (type.equals("3"))
//        {
//            priceview.setVisibility(View.GONE);
//            pricetv.setVisibility(View.GONE);
//            totalratingview.setVisibility(View.GONE);
//
//
//        }

       Picasso.with(getApplicationContext()).load("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+courseimage).placeholder(R.drawable.placeholder).into(thumbnailview);
        pricetv.setText("\u20B9"+price_res);
        bottompricetv.setText("\u20B9"+price_res);
        coursetitletv.setText(coursetitle);
        videosubtitletv.setText(description);
        //averageratingtv.setText(courserating);
        creaternametv.setText(creatername);
        createremailtv.setText(createremail);


       play_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               final ProgressDialog pDialog = new ProgressDialog(getApplicationContext());
//
//        // Set progressbar message
//        pDialog.setMessage("Buffering...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        // Show progressbar
       // pDialog.show();

               try {
                   // Start the MediaController
                   MediaController mediacontroller = new MediaController(getApplicationContext());
                   mediacontroller.setAnchorView(videoView);

                   Uri videoUri = Uri.parse(Demo_Vedio_Adapter.url);
                   videoView.setMediaController(mediacontroller);
                   videoView.setVideoURI(videoUri);

               } catch (Exception e) {

                   e.printStackTrace();
               }

               videoView.requestFocus();
               videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   // Close the progress bar and play the video
                   public void onPrepared(MediaPlayer mp) {
                      // pDialog.dismiss();
                       videoView.start();
                       progressBar.setVisibility(View.GONE);
                   }
               });
               videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                   public void onCompletion(MediaPlayer mp) {
                      // if (pDialog.isShowing()) {
                       //    pDialog.dismiss();
                        //    progressBar.setVisibility(View.GONE);
                     //  }
                       finish();
                   }
               });
           }
       });




//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//
//
//
//                mp.start();
//
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
//                        // TODO Auto-generated method stub
//
//                        progressBar.setVisibility(View.GONE);
//                        mp.start();
//                    }
//                });
//
//
//            }
//        });


//
//        submitratingbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String comment="";
//                Float rating;
//
//                 comment=ratingEt.getText().toString();
//                 rating=addratingbar.getRating();
//                // Toast.makeText(VideoDetailsActivity.this, "rating="+rating, Toast.LENGTH_SHORT).show();
//
//
//                if (comment.equals(""))
//                {Toast.makeText(VideoDetailsActivity.this, "Please Give Some Feedback", Toast.LENGTH_SHORT).show();
//                }
//
//
//                else if (rating==0)
//                {
//                    Toast.makeText(VideoDetailsActivity.this, "Please Rate by clicking at stars", Toast.LENGTH_SHORT).show();
//                }
//
//               else {
//
//                   sendFeedback(rating,comment);
//
//                }
//
//            }
//        });

        discriptiontv.setText(description);
       //  recyclerView.setVisibility(View.GONE);
         expandbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!isexpanded) {
                     recyclerView.setVisibility(View.VISIBLE);
                     isexpanded=true;
                     expandbtn.setImageResource(R.drawable.ic_remove_black_24dp);
                 }

                 else {
                     recyclerView.setVisibility(View.GONE);
                     isexpanded=false;
                     expandbtn.setImageResource(R.drawable.ic_add_black_24dp);
                 }
             }
         });
       // Date today = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy");
        System.out.println();
    //    String  currentDateTimeString = DateFormat.getDateTimeInstance()
               // .format(new Date());
     //   SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
//        Date dateObj = null;
//        try {
//            dateObj = curFormater.parse(String.valueOf(currentDateTimeString));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

         newDateStr = dateFormat.format(cal.getTime());
         if(!paid.equals("null")){
             buyButton.setText("Explore courses");
         }else {
             buyButton.setText("Buy now");
         }
       // Toast.makeText(this, "new date"+newDateStr, Toast.LENGTH_SHORT).show();
        buyButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(paid.equals("null")) {

                     startPayment();
                 }else {

                     Intent intent = new Intent(getApplicationContext(),CourceActivity.class);
                     startActivity(intent);
                 }

//                 String name=SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();
//                 String email=SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail();
//                 String mobile=SharedPrefManager.getInstance(getApplicationContext()).getUser().getMobilenumber();
//
//
//                // Toast.makeText(VideoDetailsActivity.this, ""+System.currentTimeMillis()+""+PaymentGatewayData.MERCHENT_ID+""+PaymentGatewayData.ACCESS_TOKEN+""+""+name+email+""+courseprice+coursetitle ,Toast.LENGTH_LONG).show();
//
//                 JSONObject object = new JSONObject();
//                 try {
//                     object.put("merchant_id", PaymentGatewayData.MERCHENT_ID);
//                     object.put("access_token",PaymentGatewayData.ACCESS_TOKEN);
//                     object.put("customer_name",name);
//                     object.put("customer_email",email);
//                     object.put("customer_phone", mobile);
//                     object.put("product_name",coursetitle);
//                     object.put("order_no",System.currentTimeMillis()); // order no. should have 10 to 30 character in numeric format
//                     object.put("amount",courseprice);  // minimum amount should be 10
//                     object.put("isLive",true); // need to send false if you are in sandbox mode
//
//                 } catch (JSONException e) {
//                     e.printStackTrace();
//                     Toast.makeText(VideoDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                 }
//                 new PaykunApiCall.Builder(VideoDetailsActivity.this).sendJsonObject(object); // Paykun api to initialize your payment and send info.
//


                // sendPaymentData("1223456","123456","57","9","success");


             }

         });


         addtowhishlistcard.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 addToWhishList();
             }
         });


//         vieallfeedbacktv.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//
//                 Intent intent=new Intent(VideoDetailsActivity.this,AllReviewsActivity.class);
//                 intent.putExtra("courseid",courseid);
//                 startActivity(intent);
//
//             }
//         });

//         getUserFeedbacks();

        getdata();


    }
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        //Toast.makeText(this, "c=>"+courseprice, Toast.LENGTH_SHORT).show();

        try{

            final_amountl = 100*(Integer.valueOf(price_res));

        }catch (Exception e){

        }

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Raushan Edu Care");
            options.put("description", coursetitle);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(final_amountl));
            options.put("address","Raushan Edu Care");
            options.put("payment_capture","1");
            JSONObject preFill = new JSONObject();
            preFill.put("email", SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail());
            preFill.put("contact", SharedPrefManager.getInstance(getApplicationContext()).getUser().getMobilenumber());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    //@Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
           // Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            transactionid =razorpayPaymentID;

            rajorpaydata();
        } catch (Exception e) {
            //Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")

    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
            showDialogueAfterPayment();

        } catch (Exception e) {
            // Log.e(TAG, "Exception in onPaymentError", e);
        }
    }



    private void getdata(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/home-page-after?category_id="+courseid;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");


                            if (status.equals("200")) {
                                String video_count = object.getString("videoscount");
                                String studymatrial = object.getString("subjectcount");
                                String test_series = object.getString("testseriescount");
                                paid = object.getString("paid");
                               // Toast.makeText(VideoDetailsActivity.this, ""+paid, Toast.LENGTH_SHORT).show();
                                viedo.setText("Videos");
                                stduymatria.setText("Study Material");
                                testseries.setText("Test series");


                                JSONArray ratingarray=object.getJSONArray("courseCateory");


                                for (int i=0;i<ratingarray.length();i++)
                                {

                                    JSONObject jsonObject = ratingarray.getJSONObject(i);
                                    String cat_name_resr=jsonObject.getString("category_name");
                                    String id_resr=jsonObject.getString("id");
                                    String cat_image_res=jsonObject.getString("category_image");
                                    price_res=jsonObject.getString("price");
                                    String dis_res=jsonObject.getString("description");

                                    Picasso.with(getApplicationContext()).load("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+cat_image_res).placeholder(R.drawable.placeholder).into(thumbnailview);
                                    if(paid.equals("null")) {
                                        pricetv.setText("\u20B9" + price_res);
                                    }else {
                                        pricetv.setText("This course is already buy");
                                    }
                                    bottompricetv.setText("\u20B9"+price_res);
                                    coursetitletv.setText(cat_name_resr);
                                    videosubtitletv.setText(description);
                                    //averageratingtv.setText(courserating);
                                    creaternametv.setText(creatername);
                                    createremailtv.setText(createremail);
                                    discriptiontv.setText(dis_res);

                                }


                           }
                        else {

                               Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                  progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VideoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
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
        queue.add(stringRequest);


    }

    private void getdemoviedo(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/home-page-after?category_id="+courseid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");

                            if (status.equals("200")) {


                                JSONArray ratingarray = object.getJSONArray("videos");



                                for (int i = 0; i < ratingarray.length(); i++) {

                                    JSONObject jsonObject = ratingarray.getJSONObject(i);
                                    String videos_title = jsonObject.getString("videos_title");
                                    String id = jsonObject.getString("id");
                                    String vedios_file = jsonObject.getString("videos_file");
                                    String thumb_nail = jsonObject.getString("thumbnails_image");
                                    String paidtype = jsonObject.getString("payment_type");

                                    //if (paidtype.equals("unpaid")) {
                                        demo_viedo_models.add(new Demo_Viedo_Model(id, videos_title, "https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + vedios_file, "https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + thumb_nail,paidtype));
                                   // }
                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(VideoDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                                    demorecy.setLayoutManager(horizontalLayoutManager2);
                                    Demo_Vedio_Adapter courceProviderAdapter = new Demo_Vedio_Adapter(demo_viedo_models, getApplicationContext());
                                    demorecy.setAdapter(courceProviderAdapter);
                                    progressBar.setVisibility(View.GONE);

                                }
//                                JSONArray coursearrayy = object.getJSONArray("videos");
//
//                                for (int j = 0; j < coursearrayy.length(); j++) {
//
//                                    JSONObject jsonObject = coursearrayy.getJSONObject(j);
//
//                                    String videoid = jsonObject.getString("id");
//                                    String videotitle = jsonObject.getString("videos_title");
//                                    String videourl = jsonObject.getString("videos_file");
//                                    String ispreview = jsonObject.getString("payment_type");
//
//                                    videoModelList.add(new VideoModel(videoid, videotitle, "https://swasthyaayur.com/adwogindia.com/raushanedu/public/" + videourl, ispreview));
//
//
//                                    progressBar.setVisibility(View.GONE);
//                                    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
//                                    gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                                    recyclerView.setLayoutManager(gridLayoutManager);
//                                    VideoContentAdapter courseAdapter = new VideoContentAdapter(VideoDetailsActivity.this, videoModelList);
//                                    recyclerView.setAdapter(courseAdapter);
//                                    courseAdapter.notifyDataSetChanged();
//
//
//                                }
                            }
                            else {

                                Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VideoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
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
        queue.add(stringRequest);


    }
//    public void startPayment() {
//        /*
//          You need to pass current activity in order to let Razorpay create CheckoutActivity
//         */
//        final Activity activity = this;
//
//        final Checkout co = new Checkout();
//
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "Razorpay Corp");
//            options.put("description", "Demoing Charges");
//            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("currency", "INR");
//            options.put("amount", "100");
//
//            JSONObject preFill = new JSONObject();
//            preFill.put("email", "test@razorpay.com");
//            preFill.put("contact", "956018681");
//
//            options.put("prefill", preFill);
//
//            co.open(activity, options);
//        } catch (Exception e) {
//            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
//                    .show();
//            e.printStackTrace();
//        }
//    }
//
//    @SuppressWarnings("unused")
//    //@Override
//    public void onPaymentSuccess(String razorpayPaymentID) {
//        try {
//            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
//            transactionid = razorpayPaymentID;
//            rajorpaydata();
//        } catch (Exception e) {
//            //Log.e(TAG, "Exception in onPaymentSuccess", e);
//
//        }
//    }
//
//    /**
//     * The name of the function has to be
//     * onPaymentError
//     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
//     */
//    @SuppressWarnings("unused")
//
//    public void onPaymentError(int code, String response) {
//        try {
//            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            // Log.e(TAG, "Exception in onPaymentError", e);
//        }
//    }
//


    private void  rajorpaydata()
    {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SEND_PAYMENTDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);

                            String status = obj.getString("status");

                            if (status.equals("200")) {
                                showDialogueAfterSuccessPayment();
//                              Intent intent = new Intent(getApplicationContext(),CourceActivity.class);
//                              startActivity(intent);
                               // Toast.makeText(VideoDetailsActivity.this, "", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            } else {

                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                showDialogueAfterPayment();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showDialogueAfterPayment();
                            Toast.makeText(VideoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userid);
                params.put("category_id", courseid);
                params.put("payment_id", transactionid);
                params.put("buypackage", "one");
                  params.put("status", "success");
                params.put("price", price_res);
                params.put("date1",newDateStr);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }





//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.share) {
//            return true;
//        } else if (id == R.id.action_info) {
//            return true;
//        }
//
//        else if (id==android.R.id.home)
//        {
//            finish();
//
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void hideOption(int id) {
//        MenuItem item = menu.findItem(id);
//        item.setVisible(false);
//    }
//
//    private void showOption(int id) {
//        MenuItem item = menu.findItem(id);
//        item.setVisible(true);
//    }
//
//    public static String convertMillieToHMmSs(long millie) {
//        long seconds = (millie / 1000);
//        long second = seconds % 60;
//        long minute = (seconds / 60) % 60;
//        long hour = (seconds / (60 * 60)) % 24;
//
//        String result = "";
//        if (hour > 0) {
//            return String.format("%02d:%02d:%02d", hour, minute, second);
//        }
//        else {
//            return String.format("%02d:%02d" , minute, second);
//        }
//
//    }
//

//    private void getUserFeedbacks()
//    {
//        userFeedbackModelList=new ArrayList<>();
//        commenysrecylerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        progressBar.setVisibility(View.VISIBLE);
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SHOW_FEEDBACKS+"?course_id="+courseid,
//                new Response.Listener<String>() {
//                    @SuppressLint("WrongConstant")
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//
//                            JSONObject object=new JSONObject(response);
//
//                            String status = object.getString("status");
//
//                            if (status.equals("200")) {
//
//                                JSONArray ratingarray=object.getJSONArray("rating");
//
//                                for (int i=0;i<ratingarray.length();i++)
//                                {
//
//                                    JSONObject jsonObject = ratingarray.getJSONObject(i);
//                                    String rating=jsonObject.getString("rating");
//                                    String id=jsonObject.getString("id");
//                                    String user_name=jsonObject.getString("user_name");
//                                    String created_at=jsonObject.getString("created_at");
//                                    String comment=jsonObject.getString("comment");
//
//                                    userFeedbackModelList.add(new UserFeedbackModel(Float.parseFloat(rating),user_name,created_at,comment));
//
//
//                                }
//                                if (userFeedbackModelList.size()>3)
//                                {
//                                    vieallfeedbacktv.setVisibility(View.VISIBLE);
//                                }
//                                UserFeedbackAdapter userFeedbackAdapter=new UserFeedbackAdapter(VideoDetailsActivity.this,userFeedbackModelList,"1");
//                                commenysrecylerview.setAdapter(userFeedbackAdapter);
//                                userFeedbackAdapter.notifyDataSetChanged();
//                                progressBar.setVisibility(View.GONE);
//                            } else {
//
//                               Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
//                                  progressBar.setVisibility(View.GONE);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(VideoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                       // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//                }) {
//
//            @Override
//            public void deliverError(VolleyError error) {
//                if (error instanceof NoConnectionError) {
//                    Cache.Entry entry = this.getCacheEntry();
//                    if(entry != null) {
//                        Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
//                        deliverResponse(response.result);
//                        return;
//                    }
//                }
//                super.deliverError(error);
//            }
//
//        };
//
//        queue.getCache().clear();
//        queue.add(stringRequest);
//
//    }




    private void getAllVideos()
    {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
             String url ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/home-page-after?category_id="+courseid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                            String status = object.getString("status");

                            if (status.equals("200")) {



                               // Toast.makeText(VideoDetailsActivity.this, ""+coursearray, Toast.LENGTH_SHORT).show();
                                JSONArray coursearrayy = object.getJSONArray("videos");

                                for (int j=0;j<coursearrayy.length();j++)
                                {

                                    JSONObject jsonObject = coursearrayy.getJSONObject(j);

                                    String videoid=jsonObject.getString("id");
                                    String videotitle=jsonObject.getString("videos_title");
                                    String videourl=jsonObject.getString("videos_file");
                                    String ispreview=jsonObject.getString("payment_type");

                                    //Toast.makeText(VideoDetailsActivity.this, "dhbd"+ispreview+videoid+videotitle+videourl, Toast.LENGTH_SHORT).show();
                                    videoModelList.add(new VideoModel(videoid,videotitle,"https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+videourl,ispreview));


                                    progressBar.setVisibility(View.GONE);
                                    @SuppressLint("WrongConstant") LinearLayoutManager horizontalLayoutManager2
                                            = new LinearLayoutManager(VideoDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager(horizontalLayoutManager2);
                                    VideoContentAdapter courseAdapter = new VideoContentAdapter(VideoDetailsActivity.this, videoModelList);
                                    recyclerView.setAdapter(courseAdapter);
                                    courseAdapter.notifyDataSetChanged();


                                }







                            } else {

                                Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VideoDetailsActivity.this, "something went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
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


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)

    public void getResults(Events.PaymentMessage message) {

        if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SUCCESS)){

            if(!TextUtils.isEmpty(message.getTransactionId())) {


                Toast.makeText(VideoDetailsActivity.this, "Your Transaction is succeed with transaction id : "+message.getTransactionId() , Toast.LENGTH_LONG).show();

               // Log.d("order id"," getting order id value : "+message.getTransactionDetail().order.orderId);

                sendPaymentData(message.getTransactionId(),message.getTransactionDetail().order.orderId,courseid,userid,PaykunHelper.MESSAGE_SUCCESS);

            }
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_FAILED)){

            Toast.makeText(VideoDetailsActivity.this,"Your Transaction is failed", Toast.LENGTH_SHORT).show();
            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_FAILED);

        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SERVER_ISSUE)){
            // do your stuff here

            Toast.makeText(VideoDetailsActivity.this,PaykunHelper.MESSAGE_SERVER_ISSUE, Toast.LENGTH_SHORT).show();

            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_SERVER_ISSUE);

        }else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING)){
            // do your stuff here
            Toast.makeText(this, "Access Token Missing", Toast.LENGTH_SHORT).show();
            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING);

        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_MERCHANT_ID_MISSING)){
            // do your stuff here
            Toast.makeText(this, "Mervhant Id Missing", Toast.LENGTH_SHORT).show();
            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_SERVER_ISSUE);

        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_INVALID_REQUEST)){
            Toast.makeText(VideoDetailsActivity.this,"Invalid Request", Toast.LENGTH_SHORT).show();
            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_INVALID_REQUEST);
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE)){
            Toast.makeText(VideoDetailsActivity.this,"Network is not available", Toast.LENGTH_SHORT).show();
            sendPaymentData(message.getTransactionId(),"Not Generated",courseid,userid,PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Register this activity to listen to event.
        GlobalBus.getBus().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Unregister from activity
        GlobalBus.getBus().unregister(this);
    }



    private void addToWhishList()
    {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ADD_TO_WHISHLIST,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object=new JSONObject(response);

                             String status = object.getString("status");

                            if (status.equals("200")) {

                                Toast.makeText(VideoDetailsActivity.this, "Added To Whishlist", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            } else {

                                Toast.makeText(getApplicationContext(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VideoDetailsActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {

            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userid);
                params.put("course_id", courseid);
                return params;
            }

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }

    private void sendFeedback(final Float rating, final String comment)
    {
        feedbackprogressbar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SEND_FEEDBACK+"?user_name="+SharedPrefManager.getInstance(getApplicationContext()).getUser().getName()+"&rating="+rating+"&course_id="+courseid+"&comment="+comment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");

                            if (status.equals("200")) {

                                ratingEt.getText().clear();
                                addratingbar.setRating(0);
                                Toast.makeText(VideoDetailsActivity.this, "Thanks For Feedback", Toast.LENGTH_SHORT).show();
                                feedbackprogressbar.setVisibility(View.GONE);

                            } else {

                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                feedbackprogressbar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VideoDetailsActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            feedbackprogressbar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        feedbackprogressbar.setVisibility(View.GONE);
                    }
                }) {



        };

        VolleySingleton.getInstance(this).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendPaymentData(final String transactionid, final String orderid, final String courseid, final String userid, final String status)
    {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SEND_PAYMENTDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);

                            String status = obj.getString("status");

                            if (status.equals("200")) {

                                Toast.makeText(VideoDetailsActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            } else {

                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                showDialogueAfterPayment();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showDialogueAfterPayment();
                            Toast.makeText(VideoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
               // params.put("user_id", userid);
                params.put("category_id", courseid);
                params.put("payment_id", transactionid);
                params.put("buypackage", orderid);
              //  params.put("status", status);
                params.put("price", courseprice);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private void showDialogueAfterPayment()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(VideoDetailsActivity.this);
        builder1.setTitle("Payment for "+coursetitle);
        builder1.setMessage("Your Payment Is Failed..!!!!!");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.atoozlogo);

        builder1.setPositiveButton(
                "Go To DashBoard",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       startActivity(new Intent(VideoDetailsActivity.this, Main2Activity.class));
                       finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void showDialogueAfterSuccessPayment()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(VideoDetailsActivity.this);
        builder1.setTitle("Payment for "+coursetitle);
        builder1.setMessage("Your Payment Is Succesfull..!");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.atoozlogo);

        builder1.setPositiveButton(
                "Go To DashBoard",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(VideoDetailsActivity.this,CourceActivity.class));
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void getAllRatingAverageAndCount()
    {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.GET_ALL_RATING_OF_COURSE+"?course_id="+courseid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject object = new JSONObject(response);

                            String status=object.getString("status");

                            JSONObject obj=object.getJSONObject("rating-count-avg");

                            if (status.equals("200")) {

                                String fiveaverage=obj.getString("five_avg");
                                String fivecount=obj.getString("five_count");
                                String fouraverage=obj.getString("four_avg");
                                String fourcount=obj.getString("four_count");
                                String threeaverage=obj.getString("three_avg");
                                String threeratingcount=obj.getString("three_count");
                                String tworatingaverage=obj.getString("two_avg");
                                String tworatingcount=obj.getString("two_count");
                                String oneratingavg=obj.getString("one_avg");
                                String oneratingcount=obj.getString("one_count");
                                String totalcandidate=obj.getString("total");
                                totalratingandidate.setText(totalcandidate+" Feedbacks");
                                fivestarcount.setText(fivecount);
                                fourstarcount.setText(fourcount);
                                threestarcount.setText(threeratingcount);
                                twostarcount.setText(tworatingcount);
                                onestarcount.setText(oneratingcount);
                                fivestarprogres.setProgress(Integer.parseInt(fiveaverage));
                                fivestarprogres.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                                fourstarprogress.setProgress(Integer.parseInt(fouraverage));
                                fourstarprogress.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                                threestarprogress.setProgress(Integer.parseInt(threeaverage));
                                threestarprogress.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                                twostarprogress.setProgress(Integer.parseInt(tworatingaverage));
                                twostarprogress.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                                onestarprogress.setProgress(Integer.parseInt(oneratingavg));
                                onestarprogress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                                progressBar.setVisibility(View.GONE);

                            } else {

                                ratingprogressbarcontainer.setVisibility(View.GONE);




                                Toast.makeText(getApplicationContext(), "msg="+obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ratingprogressbarcontainer.setVisibility(View.GONE);
                            Toast.makeText(VideoDetailsActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // ratingprogressbarcontainer.setVisibility(View.GONE);
                      //  Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {


        };

        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}

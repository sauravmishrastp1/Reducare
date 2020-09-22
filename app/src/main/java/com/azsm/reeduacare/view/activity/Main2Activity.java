package com.azsm.reeduacare.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.ExpandAdapter;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.MenuModel;
import com.azsm.reeduacare.model.Notification_model;
import com.azsm.reeduacare.view.fragment.GtcForm;
import com.azsm.reeduacare.view.fragment.HomePage;
import com.azsm.reeduacare.view.fragment.Profile;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    private static final int NOTIFICATION_id=001;

        Fragment selectedFragment=null;
        ConnectivityManager connec;
        Toolbar toolbar;
        ExpandAdapter expandableListAdapter;
        ExpandableListView expandableListView;
        List<MenuModel> headerList = new ArrayList<>();
        BottomNavigationView bottomNavigationView;
        AppBarConfiguration appBarConfiguration;
        HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
        ArrayList<Notification_model>notification_models = new ArrayList<>();
        private Uri imgurii;
        private ImageView croperimg;

        private static final int GALLERY_IMAGE = 1;
        AsyncTask<Bitmap, Void, String> theTask;
        private static final int PHOTO_REQUEST_CAMERA = 0;//camera
        private static final int PHOTO_REQUEST_GALLERY = 1;//gallery
        private static final int PHOTO_REQUEST_CUT = 2;
        TextView name,email;
        public static final String CHANNEL_ID = "chanal1";
        String notifyname,notyfy_dis,notify_type,notyfy_noticatcation;
        View notification;
        public static  TextView notifiation_count;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.toolbar);
        notification = findViewById(R.id.cart);
        notifiation_count = findViewById(R.id.countcart);
       name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

            Boolean islogin= SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn();
            if (!islogin)
            {
                startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                finish();
            }else {
                String name_id = SharedPrefManager.getInstance(getApplicationContext()).getUser().getName();
                String email_id = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail();
               // name.setText("");
               // email.setText("");

            }
            getnotification();
            getntificationnnn();
           // Toast.makeText(this, ""+notifyname+notyfy_dis, Toast.LENGTH_SHORT).show();

           notification.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getApplicationContext(), Notification.class);
                   startActivity(intent);
               }
           });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
            expandableListView = findViewById(R.id.expandableListView);
            prepareMenuData();
            expandableListView.setChildDivider(getResources().getDrawable(R.color.white));
            populateExpandableList();

             FloatingActionButton fab = findViewById(R.id.fab);
          //  Boolean islogin= SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn();


            // connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            //  bottomNavigationView=findViewById(R.id.bottom_navigation);

            // NavigationUI.setupWithNavController(bottomNavigationView,navController);
            //bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelector);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomePage()).commit();

            toggle.syncState();
            appBarConfiguration = new AppBarConfiguration.Builder(R.id.home,R.id.course,R.id.whishlist,R.id.account).setDrawerLayout(drawer).build();
            navigationView.setNavigationItemSelectedListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomePage()).commit();


            connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            bottomNavigationView=findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelector);
        }

    private void getnotification() {
        // Toast.makeText(DoubtSectionActivity.this, "keyword="+keyword, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/live-notify";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            String status =object.getString("status");
                           // Toast.makeText(Main2Activity.this, ""+object, Toast.LENGTH_SHORT).show();

                            if (status.equals("200")) {

                                //Toast.makeText(Main2Activity.this, "hii", Toast.LENGTH_SHORT).show();

                                JSONObject jsonObject = object.getJSONObject("notification");


                                notifyname = jsonObject.getString("category_name");
                                notify_type = jsonObject.getString("type");
                                String course_image = jsonObject.getString("category_image");
                                notyfy_dis = jsonObject.getString("description");
                                notyfy_noticatcation = jsonObject.getString("notification");
                                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
                                NotificationCompat.Builder notify = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
                                notify.setSmallIcon(R.drawable.atoozlogo);
                                notify.setContentTitle(notifyname);
                                notify.setContentText(notyfy_dis);
                                notify.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                notify.setContentIntent(pendingIntent);
                                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                                notificationManagerCompat.notify(NOTIFICATION_id,notify.build());


                            }else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                          //  Toast.makeText(Main2Activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {

                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {

                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {

                            errorCode = -6;
                        } else if (error instanceof ServerError) {

                            errorCode = 0;
                        } else if (error instanceof NetworkError) {

                            errorCode = -1;
                        } else if (error instanceof ParseError) {

                            errorCode = -8;
                        }
                    }
                }) {

        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelector=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId())
            {
                case R.id.home:

                    selectedFragment=new HomePage();
                    toolbar.setTitle("Home");
                    break;
                case R.id.course:
                    Intent intent=new Intent(Main2Activity.this, DoubtSectionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("keyword","camera");
                    startActivity(intent);
                    break;

                case R.id.whishlist:
                    selectedFragment=new GtcForm();
                    toolbar.setTitle("GTC");
                    break;
                case R.id.account:
                    selectedFragment=new Profile();
                    toolbar.setTitle("My Profile");
                    break;

            }
            if (selectedFragment!=null)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            }

            return true;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            Uri uriresult = CropImage.getPickImageResultUri(getApplicationContext(), data);
            if (CropImage.isReadExternalStoragePermissionsRequired(getApplicationContext(), uriresult)) {
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
                //Toast.makeText(getApplicationContext(), "hii", Toast.LENGTH_SHORT).show();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) croperimg.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!recognizer.isOperational()) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(getApplicationContext(), "string="+sb.toString(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.home:

                selectedFragment = new HomePage();
                toolbar.setTitle("Home");
                break;



            case R.id.course:
                // selectedFragment = new CourseProvide();

                toolbar.setTitle("Courses");
                break;

            case R.id.whishlist:

                selectedFragment = new GtcForm();
                toolbar.setTitle("GTC");
                break;

            case R.id.account:

                selectedFragment = new Profile();
                toolbar.setTitle("My Profile");
                break;
//            case R.id.gtrcc:
//                Intent intent = new Intent(Main2Activity.this,MemberSphiPlan.class);
//                startActivity(intent);
//                break;
//            case R.id.regulr:
//                //selectedFragment = new RecMemberShip();
//                toolbar.setTitle("Regular Courses");
//               // selectedFragment = new CourseProvide();
//                break;
//            case R.id.carsah:
//               // selectedFragment = new RecMemberShip();
//                toolbar.setTitle("Crash Courses");
//               // selectedFragment = new CourseProvide();
//                break;
//            case R.id.logoutacout:
//                SharedPrefManager.getInstance(getApplicationContext()).logout();

        }

        if (selectedFragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("My Profile", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial",R.drawable.sidemenuprofile); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Courses", true, true, "",R.drawable.sidecource); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Regular Courses", false, false, "r",R.drawable.sidedout);
        childModelsList.add(childModel);

        childModel = new MenuModel("Crash Courses", false, false, "c",R.drawable.sidedout);
        childModelsList.add(childModel);

        childModel = new MenuModel("GTC", false, false, "g",R.drawable.sidedout);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            // Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Test Series", true, true, "",R.drawable.ic_content_paste_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Regular", false, false, "iit",R.drawable.sidedout);
        childModelsList.add(childModel);

        childModel = new MenuModel("Crash", false, false, "neet",R.drawable.sidedout);
        childModelsList.add(childModel);
        childModel = new MenuModel("Other", false, false, "other",R.drawable.sidedout);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Study Material", true, true, "",R.drawable.sidecource); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Regular", false, false, "S/R",R.drawable.sidedout);
        childModelsList.add(childModel);

        childModel = new MenuModel("Crash", false, false, "S/C",R.drawable.sidedout);
        childModelsList.add(childModel);
        childModel = new MenuModel("GTC", false, false, "S/G",R.drawable.sidedout);
        childModelsList.add(childModel);

        childModel = new MenuModel("Special Foundation", false, false, "S/S",1);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Doubt Section", true, true, "",R.drawable.sidedout); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("About Us", true, true, "",R.drawable.ic_tablet_mac_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Live Classes", true, true, "",R.drawable.ic_live_tv_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Support", true, true, "",R.drawable.ic_phonelink_ring_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("Invite Friends", true, true, "",R.drawable.ic_send_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new MenuModel("Logout", true, true, "",R.drawable.ic_power_settings_new_black_24dp); //Menu of Python Tutorials
        headerList.add(menuModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandAdapter(Main2Activity.this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String headerame = headerList.get(groupPosition).menuName;

                if(headerame.equals("Logout")){
                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                } if(headerame.equals("Doubt Section")){
                    Intent intent = new Intent(Main2Activity.this, DoubtSectionActivity.class);
                    intent.putExtra("keyword","");
                    startActivity(intent);
                }if(headerame.equals("My Profile")){
                   Intent intent = new Intent(Main2Activity.this, MyProfile_Acrtivity_.class);
                    startActivity(intent);
                }if(headerame.equals("About Us")){
                    Intent intent = new Intent(Main2Activity.this, GtcFormActivity.class);
                    startActivity(intent);
                }if(headerame.equals("Invite Friends")){
                    Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                    final String appPackageName = getApplicationContext().getPackageName();
                    String app_url = "https://play.google.com/store/apps/details?id="+appPackageName;
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
                }if(headerame.equals("Support")){
                    Intent intent = new Intent(Main2Activity.this, Support_Activity.class);
                    startActivity(intent);
                }if(headerame.equals("Live Classes")){
                    Intent intent = new Intent(Main2Activity.this, LiveClasses_Activity.class);
                    startActivity(intent);
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.url.equals("r")) {

                        Intent intent2 = new Intent(Main2Activity.this, CourceCategoryActivity.class);
                        intent2.putExtra("tolbar","1");
                        startActivity(intent2);
                    }if (model.url.equals("c")) {

                        Intent intent4 = new Intent(Main2Activity.this,CourceCategoryActivity.class);
                        intent4.putExtra("tolbar","2");
                        startActivity(intent4);
                    }if (model.url.equals("g")) {

                        Intent intent5 = new Intent(Main2Activity.this, MemberSphiPlan.class);
                        startActivity(intent5);
                    }if (model.url.equals("iit")) {

                        Intent intent6 = new Intent(Main2Activity.this, TestSeriesCategoryActivity.class);
                        intent6.putExtra("type","iit");

                        startActivity(intent6);
                    }if (model.url.equals("neet")) {

                        Intent intent7 = new Intent(Main2Activity.this,TestSeriesCategoryActivity.class);
                        intent7.putExtra("type","iit");
                        startActivity(intent7);
                    }if (model.url.equals("other")) {

                        Intent intent8 = new Intent(Main2Activity.this,TestSeriesCategoryActivity.class);
                        intent8.putExtra("type","other");
                        startActivity(intent8);
                    }if (model.url.equals("v")) {

                        Intent intent8 = new Intent(Main2Activity.this, StudyMatrailCategory.class);
                        startActivity(intent8);
                    }if (model.url.equals("S/R")) {
                        Intent intent9 = new Intent(Main2Activity.this, StudyMatrailCategory.class);
                        intent9.putExtra("id","1");
                        startActivity(intent9);
                    }if (model.url.equals("S/C")) {
                        Intent intent9 = new Intent(Main2Activity.this, StudyMatrailCategory.class);
                        intent9.putExtra("id","2 ");
                        startActivity(intent9);
                    }if (model.url.equals("S/G")) {
                        Intent intent9 = new Intent(Main2Activity.this, StudyMatrailCategory.class);
                        intent9.putExtra("id","4");
                        startActivity(intent9);
                    }if (model.url.equals("S/S")) {
                        Intent intent9 = new Intent(Main2Activity.this, StudyMatrailCategory.class);
                        intent9.putExtra("id","6");
                        startActivity(intent9);
                    }
                }
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        requestStoragePermission();
        requestStoragePermission2();
    }

    protected void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(
                this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                Main2Activity.this,
                                new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });

               // ActivityCompat.requestPermissions(, new String[] {Manifest.permission.CAMERA}, requestCode);
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
           // Toast.makeText(this,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }

    protected void requestStoragePermission2(){
        if(ContextCompat.checkSelfPermission(
                this,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.CAMERA)){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Camera permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                Main2Activity.this,
                                new String[]{
                                        Manifest.permission.CAMERA
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });

               // ActivityCompat.requestPermissions(, new String[] {Manifest.permission.CAMERA}, requestCode);
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.CAMERA
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.CAMERA
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
           // Toast.makeText(this,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CODE:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        )
                ){
                   // // Permissions are granted
                    Toast.makeText(this,"Permissions granted.",Toast.LENGTH_SHORT).show();
                }else {
                    // Permissions are denied
                   // Toast.makeText(this,"Permissions denied.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

    }

    private void getnotificationnn(){

        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/live-notify";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String status = response.getString("status");

                            if (status.equals("200")) {

                                Toast.makeText(Main2Activity.this, "hii", Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = response.getJSONObject("notification");


                                    notifyname= jsonObject.getString("category_name");
                                    notify_type = jsonObject.getString("type");
                                    String course_image = jsonObject.getString("category_image");
                                    notyfy_dis = jsonObject.getString("description");
                                    notyfy_noticatcation = jsonObject.getString("notification");





                            } else {

                                // Toast.makeText(Main2Activity.this, "No", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(Main2Activity.this, "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Server Not Responding"+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {

            //    @Override
//            protected Map<String, String> getParams()  {
//                Map<String, String> params = new HashMap<>();
//                params.put("type_id",typeeid );
//                return params;
//            }

        };

        VolleySingleton.getInstance(Main2Activity.this).addToRequestQueue(jor);


    }
    private void getntificationnnn() {


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/live-notify?user_id="+ SharedPrefManager.getInstance(getApplicationContext()).getUser().getUserid();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);

                            String status = object.getString("status");

                            if (status.equals("200")) {


                                JSONArray ratingarray = object.getJSONArray("notifycourse");

                                for (int i = 0; i < ratingarray.length(); i++) {

                                    JSONObject jsonObject = ratingarray.getJSONObject(i);
                                    String  id = jsonObject.getString("id");
                                    String category_name =jsonObject.getString("category_name");
                                    String type = jsonObject.getString("type");
                                    String category_image = jsonObject.getString("category_image");
                                    String price = jsonObject.getString("price");
                                    String description = jsonObject.getString("description");
                                    String notification = jsonObject.getString("notification");
                                    //if (paidtype.equals("unpaid")) {
                                    notification_models.add(new Notification_model(id,category_name,type,category_image,price,description,notification));
                                     notifiation_count.setText(notification.length());

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
                            } else {


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(VideoDetailsActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();

                    }
                }) {

            @Override
            public void deliverError(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Cache.Entry entry = this.getCacheEntry();
                    if (entry != null) {
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

}



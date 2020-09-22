package com.azsm.reeduacare.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.MemberShipPlanAdapter;
import com.azsm.reeduacare.view.adapter.Spinner_ItemAdapter;
import com.azsm.reeduacare.view.adapter.WeekDaysAdapter;
import com.azsm.reeduacare.constant.MultiPartHelperClass;
import com.azsm.reeduacare.constant.MyResponse;
import com.azsm.reeduacare.constant.NetworkClient;
import com.azsm.reeduacare.constant.UploadApis;
import com.azsm.reeduacare.constant.VolleyMultipartRequest;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.Daysmodelcalss;
import com.azsm.reeduacare.model.DoutsectionAnswer;
import com.azsm.reeduacare.model.MemberShipModel;
import com.azsm.reeduacare.model.Spinner_ItemModel;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MemberSphiPlan extends AppCompatActivity {
    private RecyclerView recyclerView, gruopa, gruopb, groupe, groupd, feestruct, fee1, fee2, fee3, fee5;
    // private Toolbar toolbar;
    private Button fee, f2, f3, f4, f5;
    private ArrayList<MemberShipModel> memberSphiPlansa = new ArrayList<>();
    private ArrayList<MemberShipModel> memberSphiPlansb = new ArrayList<>();
    private ArrayList<MemberShipModel> memberSphiPlansc = new ArrayList<>();
    private ArrayList<MemberShipModel> memberSphiPlansd = new ArrayList<>();
    private ArrayList<MemberShipModel> memberSphiPlanse = new ArrayList<>();
    private ArrayList<Daysmodelcalss> daysmodelcalssArrayList = new ArrayList<>();
    private ArrayList<Daysmodelcalss> daysmodelcalssArrayList1 = new ArrayList<>();
    private ArrayList<Daysmodelcalss> daysmodelcalssArrayList2 = new ArrayList<>();
    private ArrayList<Daysmodelcalss> daysmodelcalssArrayList3 = new ArrayList<>();
    private List<Spinner_ItemModel> genderlist = new ArrayList<>();
    private Spinner editText;
    String gender;
    private Button submit;
    private Button uploadsignature, uploadphoto;
    Bitmap image;
    String targetPath;
    private static final int GALLERY_IMAGE = 1;
    private static final int GALLERY_SIGN = 2;
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
    private String click;

    protected static final String PHOTO_TAKEN = "photo_taken";

    public static final String TESS_DATA = "/tessdata";
    private TextView tv;
    private TessBaseAPI tessBaseAPI;
    private Uri outputFileDir;
    static final int PHOTO_REQUEST_CODE = 1;
    private Uri imguri;

    private String filePathpic = "";
    private String extension;
    private String type1= "";
    private String type= "";
    private Button editprofilbtn;
    private EditText nameET, fatherEt, schoolEt, contactEt;
    private byte pic[] = "00.00.00".getBytes();



    private int MEDIA_REQUEST_CODE = 1;
    private int IMAGE_REQUEST_CODE = 1;
    private String selectedCourseIcon1="";
    private String selectedCourseIcon2="";
    private byte []mydata;
    private byte []mydata1;
    private int status=0;
    private Dialog dialog;
    private boolean isImageSelected=false;
    private ImageView selectediconImageview;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    ProgressDialog progressDialog;
    String mUri = "";
    String CDAChannel;
    String account_type = "";
    String filePatadhar = "";
    String filePathpan = "";
    String filePathcheque = "";
    String filePathsign = "";
    String filePathother = "";
    byte Pancard[] = "00.00.00".getBytes();
    byte Adhardata[] = "00.00.00".getBytes();
    byte Cheque[] = "00.00.00".getBytes();
    byte Other[] = "00.00.00".getBytes();
    byte Sign[] = "00.00.00".getBytes();
    private CircleImageView photo_img, signature_img;
    private static final int GALLERY_PAN = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_sphi_plan);
        gruopa = findViewById(R.id.gruupa);
        submit = findViewById(R.id.membershipbtn);
        nameET = findViewById(R.id.name_et);
        fatherEt = findViewById(R.id.father_name_et);
        schoolEt = findViewById(R.id.school_et);
        contactEt = findViewById(R.id.contact_name_et);
        feestruct = findViewById(R.id.groupafee);
        editText = findViewById(R.id.spiner);
        fee1 = findViewById(R.id.groupafee2);
        photo_img = findViewById(R.id.uploadPhoto);
        signature_img = findViewById(R.id.uploadsigncapture);
        f2 = findViewById(R.id.fee1);
        f3 = findViewById(R.id.fee2);
        uploadsignature = findViewById(R.id.uploadSignature_btn);
        uploadphoto = findViewById(R.id.uploadPhoto_btn);
        f4 = findViewById(R.id.fee4);
        fee2 = findViewById(R.id.groupafee3);
        fee3 = findViewById(R.id.groupafee4);
        fee5 = findViewById(R.id.groupafee5);
        fee = findViewById(R.id.feestA);
        groupe = findViewById(R.id.groupe);
        gruopb = findViewById(R.id.groupb);
        groupd = findViewById(R.id.groupd);
        // toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.membershipcourcerecy);
        ImageView backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        genderlist = getGenderList();

        Spinner_ItemAdapter genderadapter = new Spinner_ItemAdapter(this, (ArrayList<Spinner_ItemModel>) genderlist);

        if (editText != null) {
            editText.setAdapter(genderadapter);

            editText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Spinner_ItemModel model = (Spinner_ItemModel) parent.getSelectedItem();

                    gender = model.getSpinnerItemName();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosepic();
               // chooseCourseIcon();
            }
        });
        // uploadphoto.setImageBitmap(null);
        uploadsignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singature();
               // choosesig();
            }
        });
        //uploadsignature.setImageBitmap(null);
        getmemberplan();
        groupc();
        groupd();
        groupb();
        groupe();
        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdays();
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdays1();
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdays2();
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdays3();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
//                  AlertDialog.Builder builder1 = new AlertDialog.Builder(MemberSphiPlan.this);
//                  builder1.setMessage("Your Details is Submit");
//                  builder1.setCancelable(true);
//                  builder1.setIcon(R.drawable.atoozlogo);
//
//                  builder1.setPositiveButton(
//                          "OK",
//                          new DialogInterface.OnClickListener() {
//                              public void onClick(DialogInterface dialog, int id) {
//                                  Intent intent = new Intent(MemberSphiPlan.this, Main2Activity.class);
//                                  startActivity(intent);
//
//                              }
//                          });
//
//
//                  AlertDialog alert11 = builder1.create();
//                  alert11.show();
            }


        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void getmemberplan() {
        memberSphiPlansa.add(new MemberShipModel("Physics"));
        memberSphiPlansa.add(new MemberShipModel("Chemistry"));
        memberSphiPlansa.add(new MemberShipModel("Maths"));
        memberSphiPlansa.add(new MemberShipModel("Biology"));
        memberSphiPlansa.add(new MemberShipModel("Any Other"));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MemberShipPlanAdapter memberShipPlanAdapter = new MemberShipPlanAdapter(memberSphiPlansa, MemberSphiPlan.this);
        recyclerView.setAdapter(memberShipPlanAdapter);
        memberShipPlanAdapter.notifyDataSetChanged();

    }

    private void groupb() {
        memberSphiPlansb.add(new MemberShipModel("NEET"));
        memberSphiPlansb.add(new MemberShipModel("JEE Main"));
        memberSphiPlansb.add(new MemberShipModel("IIT Advance"));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gruopa.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MemberShipPlanAdapter memberShipPlanAdapter = new MemberShipPlanAdapter(memberSphiPlansb, MemberSphiPlan.this);
        gruopa.setAdapter(memberShipPlanAdapter);
        memberShipPlanAdapter.notifyDataSetChanged();

    }

    private void groupc() {
        memberSphiPlansc.add(new MemberShipModel("SCRA"));
        memberSphiPlansc.add(new MemberShipModel("KVPY"));
        memberSphiPlansc.add(new MemberShipModel("NTSE"));
        memberSphiPlansc.add(new MemberShipModel("IJSO "));
        memberSphiPlansc.add(new MemberShipModel("NSEP"));
        memberSphiPlansc.add(new MemberShipModel("NDA"));
        memberSphiPlansc.add(new MemberShipModel("RMO"));
        memberSphiPlansc.add(new MemberShipModel("Any Other"));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gruopb.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MemberShipPlanAdapter memberShipPlanAdapter = new MemberShipPlanAdapter(memberSphiPlansc, MemberSphiPlan.this);
        gruopb.setAdapter(memberShipPlanAdapter);
        memberShipPlanAdapter.notifyDataSetChanged();

    }

    private void groupd() {

        memberSphiPlansd.add(new MemberShipModel("10th"));
        memberSphiPlansd.add(new MemberShipModel("12th"));
        memberSphiPlansd.add(new MemberShipModel("Any Other"));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        groupd.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MemberShipPlanAdapter memberShipPlanAdapter = new MemberShipPlanAdapter(memberSphiPlansd, MemberSphiPlan.this);
        groupd.setAdapter(memberShipPlanAdapter);
        memberShipPlanAdapter.notifyDataSetChanged();

    }

    private void groupe() {

        memberSphiPlanse.add(new MemberShipModel("SC"));
        memberSphiPlanse.add(new MemberShipModel("ST"));
        memberSphiPlanse.add(new MemberShipModel("BPL"));
        // memberSphiPlanse.add(new MemberShipModel("IISO"));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        groupe.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MemberShipPlanAdapter memberShipPlanAdapter = new MemberShipPlanAdapter(memberSphiPlanse, MemberSphiPlan.this);
        groupe.setAdapter(memberShipPlanAdapter);
        memberShipPlanAdapter.notifyDataSetChanged();

    }

    private void getdays() {
        daysmodelcalssArrayList.add(new Daysmodelcalss("Monthly", false));
        daysmodelcalssArrayList.add(new Daysmodelcalss("Quatrly", false));
        daysmodelcalssArrayList.add(new Daysmodelcalss("Pakage", false));
        daysmodelcalssArrayList.add(new Daysmodelcalss("Other", false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        feestruct.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        WeekDaysAdapter gridProductAdapter = new WeekDaysAdapter(daysmodelcalssArrayList, MemberSphiPlan.this);
        feestruct.setAdapter(gridProductAdapter);
        gridProductAdapter.notifyDataSetChanged();
    }

    private void getdays1() {
        daysmodelcalssArrayList1.add(new Daysmodelcalss("Monthly", false));
        daysmodelcalssArrayList1.add(new Daysmodelcalss("Quatrly", false));
        daysmodelcalssArrayList1.add(new Daysmodelcalss("Pakage", false));
        daysmodelcalssArrayList1
                .add(new Daysmodelcalss("Other", false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        fee1.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        WeekDaysAdapter gridProductAdapter = new WeekDaysAdapter(daysmodelcalssArrayList1, MemberSphiPlan.this);
        fee1.setAdapter(gridProductAdapter);
        gridProductAdapter.notifyDataSetChanged();
    }

    private void getdays2() {
        daysmodelcalssArrayList2.add(new Daysmodelcalss("Monthly", false));
        daysmodelcalssArrayList2.add(new Daysmodelcalss("Quatrly", false));
        daysmodelcalssArrayList2.add(new Daysmodelcalss("Pakage", false));
        daysmodelcalssArrayList2.add(new Daysmodelcalss("Other", false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        fee2.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        WeekDaysAdapter gridProductAdapter = new WeekDaysAdapter(daysmodelcalssArrayList2, MemberSphiPlan.this);
        fee2.setAdapter(gridProductAdapter);
        gridProductAdapter.notifyDataSetChanged();
    }

    private void getdays3() {
        daysmodelcalssArrayList3.add(new Daysmodelcalss("Monthly", false));
        daysmodelcalssArrayList3.add(new Daysmodelcalss("Quatrly", false));
        daysmodelcalssArrayList3.add(new Daysmodelcalss("Pakage", false));
        daysmodelcalssArrayList3.add(new Daysmodelcalss("Other", false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        fee3.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        WeekDaysAdapter gridProductAdapter = new WeekDaysAdapter(daysmodelcalssArrayList3, MemberSphiPlan.this);
        fee3.setAdapter(gridProductAdapter);
        gridProductAdapter.notifyDataSetChanged();
    }

    private ArrayList<Spinner_ItemModel> getGenderList() {
        genderlist.add(new Spinner_ItemModel("class"));
        genderlist.add(new Spinner_ItemModel("6-10th"));
          genderlist.add(new Spinner_ItemModel("11th"));
        genderlist.add(new Spinner_ItemModel("12th"));
        genderlist.add(new Spinner_ItemModel("Dropper(after 12th)"));

        return (ArrayList<Spinner_ItemModel>) genderlist;
    }


    //    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
//                && resultCode == Activity.RESULT_OK) {
//            Uri uriresult = CropImage.getPickImageResultUri(this, data);
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, uriresult)) {
//                imguri = uriresult;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//            } else {
//                startcropimg(uriresult);
//            }
//        }
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                if(click.equals("sign")){
//                uploadsignature.setImageURI(result.getUri());
//                }
//                if(click.equals("photo")){
//                    uploadphoto.setImageURI(result.getUri());
//                }
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) uploadsignature.getDrawable();
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                TextRecognizer recognizer = new TextRecognizer.Builder(MemberSphiPlan.this).build();
//
//
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    protected void onActivityResult2(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
//                && resultCode == Activity.RESULT_OK) {
//            Uri uriresult = CropImage.getPickImageResultUri(this, data);
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, uriresult)) {
//                imguri = uriresult;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//            } else {
//                startcropimg(uriresult);
//            }
//        }
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                uploadphoto.setImageURI(result.getUri());
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) uploadsignature.getDrawable();
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//
//            }
//        }
//    }
//
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean check_permissions() {

        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(getApplicationContext(), PERMISSIONS)) {
            requestPermissions(PERMISSIONS, 2);
        } else {

            return true;
        }

        return false;
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//
//        builder.setTitle("Add Photo!");
//
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//
//            public void onClick(DialogInterface dialog, int item) {
//
//                if (options[item].equals("Take Photo"))
//
//                {
//                    if(check_permissions())
//                        openCameraIntent();
//
//                }
//
//                else if (options[item].equals("Choose from Gallery"))
//
//                {
//
//                    if(check_permissions()) {
//
//                    }
//                }
//
//                else if (options[item].equals("Cancel")) {
//
//                    dialog.dismiss();
//
//                }
//
//            }
//
//        });
//

    }


    // below three method is related with taking the picture from camera
    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, 1);
            }
        }
    }

    String imageFilePath;

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();

        return image;
    }

    public String getPath(Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }


    public void onActivityResult1(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                Matrix matrix = new Matrix();
                try {
                    ExifInterface exif = new ExifInterface(imageFilePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            matrix.postRotate(90);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            matrix.postRotate(180);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            matrix.postRotate(270);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri selectedImage = (Uri.fromFile(new File(imageFilePath)));

                InputStream imageStream = null;
                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);

                Bitmap resized = Bitmap.createScaledBitmap(rotatedBitmap, (int) (rotatedBitmap.getWidth() * 0.7), (int) (rotatedBitmap.getHeight() * 0.7), true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                resized.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                pic = baos.toByteArray();

                //Save_Image();

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);

                String path = getPath(selectedImage);
                Matrix matrix = new Matrix();
                ExifInterface exif = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    try {
                        exif = new ExifInterface(path);
                        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                matrix.postRotate(90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                matrix.postRotate(180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                matrix.postRotate(270);
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);


                Bitmap resized = Bitmap.createScaledBitmap(rotatedBitmap, (int) (rotatedBitmap.getWidth() * 0.5), (int) (rotatedBitmap.getHeight() * 0.5), true);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                resized.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                pic = baos.toByteArray();

                // Save_Image();

            }

        }

    }

    private void selectImage2() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);


//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//
//        builder.setTitle("Add Photo!");
//
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//
//            public void onClick(DialogInterface dialog, int item) {
//
//                if (options[item].equals("Take Photo"))
//
//                {
//                    if(check_permissions())
//                        openCameraIntent();
//
//                }
//
//                else if (options[item].equals("Choose from Gallery"))
//
//                {
//
//                    if(check_permissions()) {
//
//                    }
//                }
//
//                else if (options[item].equals("Cancel")) {
//
//                    dialog.dismiss();
//
//                }
//
//            }
//
//        });
//
//        builder.show();

    }


    // below three method is related with taking the picture from camera
    private void openCameraIntent2() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, 1);
            }
        }
    }

    String imageFilePath2;

    private File createImageFile2() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public String getPath2(Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }


    public void onActivityResult2(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                Matrix matrix = new Matrix();
                try {
                    ExifInterface exif = new ExifInterface(imageFilePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            matrix.postRotate(90);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            matrix.postRotate(180);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            matrix.postRotate(270);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri selectedImage = (Uri.fromFile(new File(imageFilePath)));

                InputStream imageStream = null;
                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);
                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);

                Bitmap resized = Bitmap.createScaledBitmap(rotatedBitmap, (int) (rotatedBitmap.getWidth() * 0.7), (int) (rotatedBitmap.getHeight() * 0.7), true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                resized.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                pic = baos.toByteArray();

                //Save_Image();

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);

                String path = getPath(selectedImage);
                Matrix matrix = new Matrix();
                ExifInterface exif = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    try {
                        exif = new ExifInterface(path);
                        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                matrix.postRotate(90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                matrix.postRotate(180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                matrix.postRotate(270);
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Bitmap rotatedBitmap = Bitmap.createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(), imagebitmap.getHeight(), matrix, true);


                Bitmap resized = Bitmap.createScaledBitmap(rotatedBitmap, (int) (rotatedBitmap.getWidth() * 0.5), (int) (rotatedBitmap.getHeight() * 0.5), true);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                resized.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                Pancard = baos.toByteArray();

                // Save_Image();

            }

        }

    }


    private void startcropimg(Uri uri) {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);

    }

    private void register() {

        final String fullname = nameET.getText().toString();
        final String father_name = fatherEt.getText().toString();
        final String school_name = schoolEt.getText().toString();
        final String contact_number = contactEt.getText().toString();

        // Toast.makeText(this, ""+account_type, Toast.LENGTH_SHORT).show();


        if (TextUtils.isEmpty(fullname)) {
            nameET.setError("Field is empty");
            nameET.requestFocus();
            return;
        } else if (TextUtils.isEmpty(father_name)) {
            fatherEt.setError("Field is empty");
            fatherEt.requestFocus();
            return;

        } else if (contact_number.length() <= 9) {
            contactEt.setError("Mobile should be minimum 10 digits");
        } else if (TextUtils.isEmpty(school_name)) {
            schoolEt.setError("Field is empty");
            schoolEt.requestFocus();
            return;
        } else {
            // Toast.makeText(this, "" +account_type, Toast.LENGTH_SHORT).show();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            // progressDialog.setIcon(R.drawable.headwaygmslogo);
            progressDialog.setTitle("Submit.....");
            progressDialog.setMessage("Please wait......");
            progressDialog.show();

            String url = "https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/gtc-form";


            VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
                @Override
                public void onResponse(NetworkResponse response) {
                    String resultResponse = new String(response.data);
                    try {
                        JSONObject result = new JSONObject(resultResponse);
                        // Toast.makeText(Registeration_Activity.this, ""+result, Toast.LENGTH_SHORT).show();
                        String status = result.getString("status");
                        String message = result.getString("msg");

                        if (status.equals("200")) {
                            Toast.makeText(MemberSphiPlan.this, "" + message, Toast.LENGTH_SHORT).show();
                            // Toast.makeText(Registeration_Activity.this, "msg"+resultResponse, Toast.LENGTH_SHORT).show();
                            Intent inten = new Intent(MemberSphiPlan.this, Main2Activity.class);
                            inten.putExtra("type", "success");
                            startActivity(inten);
                            progressDialog.dismiss();
                        } else {
                            Log.i("Unexpected", message);
                            progressDialog.dismiss();
//                            Intent  inten=new Intent(Registeration_Activity.this,MainActivity.class);
//                            inten.putExtra("type","success");
//                            startActivity(inten);
                            Toast.makeText(MemberSphiPlan.this, "" + message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MemberSphiPlan.this, "something went wrong" + e, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    String errorMessage = "Unknown error";
                    if (networkResponse == null) {
                        if (error.getClass().equals(TimeoutError.class)) {
                            errorMessage = "Request timeout";
                            progressDialog.dismiss();
                            Toast.makeText(MemberSphiPlan.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                        } else if (error.getClass().equals(NoConnectionError.class)) {
                            errorMessage = "Failed to connect server";
                            progressDialog.dismiss();
                            Toast.makeText(MemberSphiPlan.this, "Server Not responding", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        String result = new String(networkResponse.data);
                        try {
                            Toast.makeText(MemberSphiPlan.this, "" + result, Toast.LENGTH_SHORT).show();
                            // JSONObject response = new JSONObject(result);
//                            String status = response.getString("status");
//                            String message = response.getString("msg");

                            String status = "";
                            String message = "";

                            if (status.equals("200")) {
                                //Toast.makeText(Registeration_Activity.this, "msg", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(Registeration_Activity.this,MainActivity.class));
                                // finish();
                                progressDialog.dismiss();
                            }

                            if (networkResponse.statusCode == 404) {
                                errorMessage = "Resource not found";
                                progressDialog.dismiss();
                                Toast.makeText(MemberSphiPlan.this, "Server Not responding", Toast.LENGTH_SHORT).show();
                            } else if (networkResponse.statusCode == 401) {
                                errorMessage = message + " Please login again";
                                progressDialog.dismiss();
                                Toast.makeText(MemberSphiPlan.this, "Server Not responding", Toast.LENGTH_SHORT).show();
                            } else if (networkResponse.statusCode == 400) {
                                errorMessage = message + " Check your inputs";
                                progressDialog.dismiss();
                                Toast.makeText(MemberSphiPlan.this, "Server Not responding", Toast.LENGTH_SHORT).show();
                            } else if (networkResponse.statusCode == 500) {
                                errorMessage = message + " Something is getting wrong";
                                progressDialog.dismiss();
                                Toast.makeText(MemberSphiPlan.this, "Server Not responding", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(MemberSphiPlan.this, "Something went wrong" + e, Toast.LENGTH_SHORT).show();

                        }
                    }
                    Log.i("Error", errorMessage);
                    progressDialog.dismiss();
                    Toast.makeText(MemberSphiPlan.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                   // params.put("user_image", new DataPart("pan_doc" +".png", mydata, type));
                    //params.put("signature_image", new DataPart("pan_doc" +".png", mydata, type));
                    params.put("signature_image", new VolleyMultipartRequest.DataPart("course_icon",Pancard, ".png"));
                    params.put("user_image", new VolleyMultipartRequest.DataPart("course_icon",pic, ".png"));
                    return params;

                }

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", fullname);
                    params.put("father_name", father_name);
                    params.put("school_name", school_name);
                    params.put("contact_number", contact_number);
                    params.put("class1", gender);
                    params.put("group_b", "iit");
                    params.put("group_c", "iit");
                    params.put("group_d", "iit");
                    params.put("group_a_fee", "iit");
                    params.put("group_b_fee", "iit");
                    params.put("group_c_fee", "iit");
                    params.put("group_d_fee", "iit");
                    params.put("group_e_fee", "iit");

                    return params;
                }

            };


            VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();

            multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);

        }

    }

    private void uploadImage() {

        final String fullname = nameET.getText().toString();
        final String father_name = fatherEt.getText().toString();
        final String school_name = schoolEt.getText().toString();
        final String contact_number = contactEt.getText().toString();

        // Toast.makeText(this, ""+account_type, Toast.LENGTH_SHORT).show();


        if (TextUtils.isEmpty(fullname)) {
            nameET.setError("Field is empty");
            nameET.requestFocus();
            return;
        } else if (TextUtils.isEmpty(father_name)) {
            fatherEt.setError("Field is empty");
            fatherEt.requestFocus();
            return;

        } else if (contact_number.length() <= 9) {
            contactEt.setError("Mobile should be minimum 10 digits");
        } else if (TextUtils.isEmpty(school_name)) {
            schoolEt.setError("Field is empty");
            schoolEt.requestFocus();
            return;
        } else {
            // Toast.makeText(this, "" +account_type, Toast.LENGTH_SHORT).show();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            // progressDialog.setIcon(R.drawable.headwaygmslogo);
            progressDialog.setTitle("Submit.....");
            progressDialog.setMessage("Please wait......");
            progressDialog.show();


            File file = new File(filePathpic);
            File file1 = new File(filePatadhar);
            Retrofit retrofit = NetworkClient.getRetrofit();
            MultiPartHelperClass.getMultipartData(new File(filePathpic), "user_image");
            MultiPartHelperClass.getMultipartData(new File(filePatadhar), "signature_image");

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), filePathpic);
            MultipartBody.Part parts = MultipartBody.Part.createFormData("newimage", file.getName(), requestBody);
            MultipartBody.Part parts2 = MultipartBody.Part.createFormData("newimage", file1.getName(), requestBody);


            //  RequestBody someData = RequestBody.create(MediaType.parse("text/plain"), "This is a new Image");

            UploadApis uploadApis = retrofit.create(UploadApis.class);
            Call call = uploadApis.updateProfile(fullname, father_name, school_name, contact_number, "SAdf", gender, "SAF", "iit", "XCSD", "xcXA", "ZXCZ", "XCX", "XCzc", "Xz", "", "", MultiPartHelperClass.getMultipartData(new File(filePathpic), "user_image"), MultiPartHelperClass.getMultipartData(new File(filePatadhar), "signature_image"));
            call.enqueue(new Callback<MyResponse>() {
                @Override
                public void onResponse(retrofit2.Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
                    try {
                        assert response.body() != null;
                        if (!response.body().getStatus().equals("200")) {
                            progressDialog.dismiss();

                        }

                    } catch (Exception e) {
                        progressDialog.dismiss();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MemberSphiPlan.this);
                        builder1.setMessage("Thanku! Your Details is Submit");
                        builder1.setCancelable(true);
                        builder1.setIcon(R.drawable.atoozlogo);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(MemberSphiPlan.this, Main2Activity.class);
                                        startActivity(intent);

                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }


                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }
    }


    public void singature () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY_SIGN);


    }
    public void choosepic() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), GALLERY_IMAGE);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
           //  Toast.makeText(this, "you selected="+filePathpic+uri, Toast.LENGTH_SHORT).show();

            try {
                filePatadhar = getPath( uri);
                //imageView.setImageDrawable(Drawable.createFromPath(filePathpic));
                type = com.azsm.reeduacare.constant.FileUtils.getMimeType(this, uri);
               // Toast.makeText(this, "type->"+type, Toast.LENGTH_SHORT).show();
                //extension = (String) com.azsm.reeduacare.constant.FileUtils.getExtension(String.valueOf(uri));
               // Toast.makeText(this, "ex->"+extension, Toast.LENGTH_SHORT).show();


                try {
                    // Toast.makeText(this, ""+filePathpic, Toast.LENGTH_SHORT).show();
                    ContentResolver cr = getBaseContext().getContentResolver();
                    InputStream inputStream = cr.openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeFile(filePatadhar);
                    photo_img.setImageBitmap(bitmap);
                    // Toast.makeText(this, ""+bitmap, Toast.LENGTH_SHORT).show();
                   // ByteArrayOutputStream baos = new ByteArrayOutputStream();
                   // bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    //Pancard = baos.toByteArray();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                    Pancard = baos.toByteArray();
                    //Toast.makeText(this, "pan-"+Pancard, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
            } catch (Exception e) {
                e.printStackTrace();
                  Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }

        }
       else if (requestCode == GALLERY_SIGN && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            //Toast.makeText(this, "you selected="+filePathpic+uri, Toast.LENGTH_SHORT).show();

            try {
                filePathpic = getPath( uri);
                //imageView.setImageDrawable(Drawable.createFromPath(filePathpic));
                type = com.azsm.reeduacare.constant.FileUtils.getMimeType(this, uri);
                extension = (String) com.azsm.reeduacare.constant.FileUtils.getExtension(String.valueOf(uri));
               // Toast.makeText(this, "e->"+extension, Toast.LENGTH_SHORT).show();


                try {
                    // Toast.makeText(this, ""+filePathpic, Toast.LENGTH_SHORT).show();
                    ContentResolver cr = getBaseContext().getContentResolver();
                    InputStream inputStream = cr.openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeFile(filePathpic);
                    signature_img.setImageBitmap(bitmap);
                    // Toast.makeText(this, ""+bitmap, Toast.LENGTH_SHORT).show();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                    pic = baos.toByteArray();
                    //return Base64.encodeToString(pic);

                   // Toast.makeText(this, ""+pic, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
            } catch (Exception e) {
                e.printStackTrace();
                //  Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

    }


//    private void choosesig()
//    {
//        Intent target = FileUtils.createGetContentIntent();
//        // Create the chooser Intent
//        Intent intent = Intent.createChooser(
//                target, getString(R.string.app_name));
//        try {
//            startActivityForResult(intent, IMAGE_REQUEST_CODE);
//        } catch (ActivityNotFoundException e) {
//            // The reason for the existence of aFileChooser
//        }
//    }
//    private void chooseCourseIcon()
//    {
//        Intent target = FileUtils.createGetContentIntent();
//        // Create the chooser Intent
//        Intent intent = Intent.createChooser(
//                target, getString(R.string.app_name));
//        try {
//            startActivityForResult(intent, MEDIA_REQUEST_CODE);
//        } catch (ActivityNotFoundException e) {
//            // The reason for the existence of aFileChooser
//        }
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        Log.d("result",""+resultCode);
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == this.RESULT_CANCELED) {
//            Log.d("what","cancle");
//            return;
//        }
//        if (requestCode == MEDIA_REQUEST_CODE) {
//            Log.d("what", "gale");
//            if (data != null) {
//                Uri contentURI = data.getData();
//
//                selectedCourseIcon1 = com.azsm.reeduacare.constant.FileUtils.getPath(this, contentURI);
//                type = com.azsm.reeduacare.constant.FileUtils.getMimeType(this, contentURI);
//                extension = com.azsm.reeduacare.constant.FileUtils.getExtension(String.valueOf(contentURI));
//                Toast.makeText(this,
//                        "File Selected: " + selectedCourseIcon1, Toast.LENGTH_LONG).show();
//                Toast.makeText(this, ""+extension, Toast.LENGTH_SHORT).show();
//
//                Bitmap src= BitmapFactory.decodeFile(selectedCourseIcon1);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                src.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                mydata = baos.toByteArray();
//                Toast.makeText(this, "my+"+mydata, Toast.LENGTH_SHORT).show();
//                isImageSelected=true;
//
////                selectediconImageview.setVisibility(View.VISIBLE);
//                photo_img.setImageBitmap(src);
////
////                Log.d("path", selectedCourseIcon);
////                selectedCourseIcon=getPath(contentURI);
////                Toast.makeText(this, ""+selectedCourseIcon, Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        }        if (requestCode == IMAGE_REQUEST_CODE) {
//            Log.d("what", "gale");
//            if (data != null) {
//                Uri contentURI = data.getData();
//
//                selectedCourseIcon2 = com.azsm.reeduacare.constant.FileUtils.getPath(this, contentURI);
//                type1 = com.azsm.reeduacare.constant.FileUtils.getMimeType(this, contentURI);
//                extension = com.azsm.reeduacare.constant.FileUtils.getExtension(String.valueOf(contentURI));
//                Toast.makeText(this,
//                        "File Selected: " + selectedCourseIcon2, Toast.LENGTH_LONG).show();
//
//                Bitmap src= BitmapFactory.decodeFile(selectedCourseIcon2);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                src.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                mydata1 = baos.toByteArray();
//                isImageSelected=true;
//
////                selectediconImageview.setVisibility(View.VISIBLE);
//                signature_img.setImageBitmap(src);
////
////                Log.d("path", selectedCourseIcon);
////                selectedCourseIcon=getPath(contentURI);
////                Toast.makeText(this, ""+selectedCourseIcon, Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        }
//    }






}

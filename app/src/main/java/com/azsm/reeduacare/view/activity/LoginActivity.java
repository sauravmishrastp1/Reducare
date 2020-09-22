package com.azsm.reeduacare.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.azsm.reeduacare.R;
import com.azsm.reeduacare.api.Url;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private Button loginbtn;
    private TextView createaccountlink,forgetpasswordlink;
    private EditText emailEt,passEt;
    private String email,password,profilepic="null";
    private ProgressBar progressBar;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private ProfileTracker profileTracker;
    private LayoutInflater layoutInflater;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn = findViewById(R.id.login_btn);
        createaccountlink = findViewById(R.id.createaccountlink);
        emailEt = findViewById(R.id.login_email);
        passEt = findViewById(R.id.login_pass);
        progressBar = findViewById(R.id.loginprogressbar);
        forgetpasswordlink=findViewById(R.id.forgetpass);
        callbackManager = CallbackManager.Factory.create();
       //loginButton = (LoginButton) findViewById(R.id.login_button);
       // loginButton.setReadPermissions(Arrays.asList(EMAIL));
       // SignInButton signInButton = findViewById(R.id.sign_in_button);

      //  boolean loggedOut = AccessToken.getCurrentAccessToken() == null;

//        if (!loggedOut) {
//
//            LoginManager.getInstance().logOut();
//
//        }



        forgetpasswordlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //forgotPassword();
            }
        });





        //////////////////////////////////////Google Login//////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        // Customizing G+ button
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        signInButton.setScopes(gso.getScopeArray());

        ///////////////////////////////////Google Login/////////////////////////






        ////////////////Facebook Login///////////////////////////////////////////

      //  loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();

//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                //loginResult.getAccessToken();
//                //loginResult.getRecentlyDeniedPermissions()
//                //loginResult.getRecentlyGrantedPermissions()
//                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
//                Log.d("API123", loggedIn + " ??");
//
//
//                getUserProfile(AccessToken.getCurrentAccessToken());
//
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });


        ////////////////Facebook Login///////////////////////////////////////////

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyFields();

            }
        });


        createaccountlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });



    }


    private void getUserProfile(final AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String id = object.getString("id");
                            profilepic = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            String name=first_name+" "+last_name;



                            LoginThroughSocialMedia(name,email);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }



    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("result", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e("Username", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            Uri image=acct.getPhotoUrl();
            if (image!=null) {
                profilepic =image.toString();
            }
            String email = acct.getEmail();

            Log.e("data", "Name: " + personName + ", email: " + email
                    + ", Image: " + profilepic);


            LoginThroughSocialMedia(personName,email);

        } else {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void verifyFields()
    {
        email=emailEt.getText().toString();
        password=passEt.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            emailEt.requestFocus();
            emailEt.setError("Email cant Empty");
        }

        else if (TextUtils.isEmpty(password))
        {
            passEt.requestFocus();
            passEt.setError("Password can't Empty");
        }


        else if (password.length()<8)
        {

           passEt.requestFocus();
           passEt.setError("Password should be minimum 8 digits");
        }

        else {

            signin();
        }
    }


    private void signin()
    {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");

                            if (status.equals("200")) {

                                JSONObject userJson = obj.getJSONObject("user");

                                String userId = userJson.getString("id");
                                String username = userJson.getString("name");
                                String userEmail = userJson.getString("email");
                                String userPhone = userJson.getString("mobile_no");
                                String address=userJson.getString("address");
                              //  String instructorid=userJson.getString("instructor");


                                User user = new User(username, userEmail, address, userPhone, userId,"instructorid","hjdlshffhd");

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivity(intent);
                                finish();
                                progressBar.setVisibility(View.GONE);

                            } else {
                              //  User user = new User(username, userEmail, address, userPhone, userId,"instructorid","hjdlshffhd");

                             //  SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                               // finish();
                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivity(intent);
                                finish();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "somrthing went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", email);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


//    public void forgotPassword(){
//        layoutInflater= LayoutInflater.from(getApplicationContext());
//        View view1 = layoutInflater.inflate(R.layout.forgot_password_dialog, null, false);
//        final AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
//                .setView(view1)
//                .create();
//
//        final EditText emailForgot_eT = view1.findViewById(R.id.emailForgot_eT);
//        final Button okBtnForgot = view1.findViewById(R.id.okBtnForgot);
//        final Button cancelBtnForgot = view1.findViewById(R.id.cancelBtnForgot);
//
//        emailForgot_eT.setText(emailEt.getText().toString().trim());
//
//        cancelBtnForgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        okBtnForgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailForgot_eT.getText().toString().trim();
//                if (email.equals(""))
//                {
//                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    hitForgotPassApi(email,dialog);
//                }
//            }
//        });
//
//        dialog.show();
//    }
//
//
//
//


    private void LoginThroughSocialMedia(final String username, final String email)
    {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SOCIAL_LOGIN+"?email="+email+"&name="+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                            String status = obj.getString("status");

                            if (status.equals("200")) {

                                JSONObject userJson = obj.getJSONObject("socialLogin");

                                String userId = userJson.getString("id");
                                String username = userJson.getString("name");
                                String userEmail = userJson.getString("email");
                                String userPhone = userJson.getString("mobile_no");
                                String address=userJson.getString("address");
                                String instructorid=userJson.getString("instructor");


                                User user = new User(username, userEmail, address, userPhone, userId,instructorid,profilepic);

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivity(intent);
                                finish();
                                progressBar.setVisibility(View.GONE);

                            } else {

                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {

        };
        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private  void hitForgotPassApi(final String email, final AlertDialog dialog)
    {
        //Toast.makeText(this, "email="+email, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.FORGETPASS_URL+"?email="+email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                          //  Toast.makeText(LoginActivity.this, ""+email+" "+obj, Toast.LENGTH_SHORT).show();

                            String status = obj.getString("status");

                            if (status.equals("200")) {

                                Toast.makeText(LoginActivity.this, "Your Password Reset Link has been sent to your Email ", Toast.LENGTH_LONG).show();
                                 dialog.dismiss();


                            } else {

                                Toast.makeText(getApplicationContext(), "Not Registered !!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Server Not Responding", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {


        };
        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}

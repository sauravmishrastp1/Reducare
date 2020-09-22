package com.azsm.reeduacare.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.constant.SharedPrefManager;
import com.azsm.reeduacare.constant.VolleySingleton;
import com.azsm.reeduacare.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Button registerbtn;
    private TextView signinlink;
    private EditText nameEt,emailEt,mobileEt,addressEt,passwordEt;
    private String name,email,mobile,address,password;
    private FirebaseFirestore firestore;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registerbtn=findViewById(R.id.registerbtn);
        signinlink=findViewById(R.id.signinlink);
        nameEt=findViewById(R.id.name);
        emailEt=findViewById(R.id.email);
        mobileEt=findViewById(R.id.phone);
        addressEt=findViewById(R.id.address);
        passwordEt=findViewById(R.id.pass);
        progressBar=findViewById(R.id.progressbar);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyFields();


            }
        });


        signinlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


    private void verifyFields()
    {
        name=nameEt.getText().toString();
        email=emailEt.getText().toString();
        mobile=mobileEt.getText().toString();
        address=addressEt.getText().toString();
        password=passwordEt.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            nameEt.requestFocus();
            nameEt.setError("Name Required");
        }

        else if (TextUtils.isEmpty(email))
        {
            emailEt.requestFocus();
            emailEt.setError("Email Required");
        }

        else if (TextUtils.isEmpty(mobile))
        {
            mobileEt.requestFocus();
            mobileEt.setError("Mobile Number Required");
        }


        else if (TextUtils.isEmpty(address))
        {
            addressEt.requestFocus();
            addressEt.setError("Address Required");
        }

        else if (TextUtils.isEmpty(password))
        {
            passwordEt.requestFocus();
            passwordEt.setError("Password Required");
        }

        else if (password.length()<8)
        {

            passwordEt.requestFocus();
            passwordEt.setError("Password should be minimum 8 digits");
        }


        else {

            registerUser();

        }
    }



    private void registerUser()
    {
        progressBar.setVisibility(View.VISIBLE);
        String url ="https://swasthyaayur.com/adwogindia.com/raushanedu/public/api/register";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                               // String instructorid=userJson.getString("instructor");

                                Toast.makeText(getApplicationContext(), obj.getString("msg"), Toast.LENGTH_SHORT).show();

                                User user = new User(username, userEmail, address, userPhone, userId,"","ertjvhj");

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
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
                            Toast.makeText(RegistrationActivity.this, "somrthing went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Timeout Try Agaig", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection Try Agaig", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "AuthFailure Error Try Agaig", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error Try Agaig", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network error Try Agaig", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Server rror Try Againg", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            errorCode = -8;
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("mobile_no", mobile);
                params.put("password", password);
                params.put("address", address);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }



}

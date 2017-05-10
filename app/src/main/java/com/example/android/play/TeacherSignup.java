package com.example.android.play;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class TeacherSignup extends AppCompatActivity {
TextView tlogin;
    Button tsignup;
    EditText editTextUsername,editTextPassword,editTextEmail,getEditTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);
        editTextUsername=(EditText)findViewById(R.id.tusername);
        getEditTextConfirmPassword=(EditText)findViewById(R.id.tcpassword);
        editTextPassword=(EditText) findViewById(R.id.tpassword);
        tsignup=(Button)findViewById(R.id.tsign_btn);
        tsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        editTextEmail=(EditText) findViewById(R.id.temail);
        tlogin=(TextView)findViewById(R.id.tlogin);
        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeacherSignup.this,TeacherLogin.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void registerUser(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String confirmPassword=getEditTextConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }



        if(!password.equals(confirmPassword) )
        {
            Toast.makeText(getApplicationContext(), "Password and confirm password do not match ,Try again!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.T_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        Toast.makeText(TeacherSignup.this,response,Toast.LENGTH_LONG).show();
                        response = response.trim();
                        if(response.equalsIgnoreCase("Successfully Registered"))
                        {

                            SharedPreferences settings = getSharedPreferences(Config.SHARED_PREF_NAME, 0); // 0 - for private mode
                            SharedPreferences.Editor editor = settings.edit();

//Set "hasLoggedIn" to true
                            editor.putBoolean("hasLoggedIn", true);
                            editor.putString("Username",editTextUsername.getText().toString());
                            Config.TUSERNAME = editTextUsername.getText().toString();
                            //  Log.w("",_emailText.getText().toString());
// Commit the edits!
                            editor.apply();
                            editor.commit();



                            next();
                        }
                        //next();
                    }
                },
                new Response.ErrorListener() {
                    @Override

                    public void onErrorResponse(VolleyError volleyError) {
                        String message = null;
                        if (volleyError instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (volleyError instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (volleyError instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }

                        Toast.makeText(TeacherSignup.this,message.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(Config.T_KEY_USERNAME,username);
                params.put(Config.T_KEY_PASSWORD,password);
                params.put(Config.T_KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void next()
    {
        Intent i=new Intent(getApplicationContext(),TMainActivity.class);
        startActivity(i);
        finish();
    }

}

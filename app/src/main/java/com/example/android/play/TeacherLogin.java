package com.example.android.play;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.play.Config.LOGIN_SUCCESS;
import static com.example.android.play.R.id.editText;

public class TeacherLogin extends AppCompatActivity {
    private boolean loggedIn = false;
    EditText editTextUsername,editTextPassword;
    Button tlogin;
TextView tsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        ActionBar  myActionOrActionSupportBar = getSupportActionBar();

        myActionOrActionSupportBar.setDisplayHomeAsUpEnabled(true);
        editTextUsername=(EditText)findViewById(R.id.tusername);
        editTextPassword=(EditText)findViewById(R.id.tpassword);
        editTextPassword.setTransformationMethod(new PasswordTransformationMethod());


        /*editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        editTextPassword.setSelection(editTextPassword.length());*/
        tlogin=(Button)findViewById(R.id.tlog_in);
        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
        tsignup=(TextView) findViewById(R.id.tsignup1);
        tsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(TeacherLogin.this,TeacherSignup.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void LoginUser(){

        final String password = editTextPassword.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();



        if (TextUtils.isEmpty(username)) {
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.T_LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TeacherLogin.this,response,Toast.LENGTH_LONG).show();
                        response = response.trim();
                        if(response.equalsIgnoreCase("logged in successfully"))
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

                            Intent i=new Intent(TeacherLogin.this,TMainActivity.class);
                            startActivity(i);
                            finish();
                           //next();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeacherLogin.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(Config.T_KEY_USERNAME, username);
                params.put(Config.T_KEY_PASSWORD,password);

                return params;
            }

        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(TeacherLogin.this, TMainActivity.class);
            startActivity(intent);
        }
    }

    public void next()
    {
        Intent i=new Intent(TeacherLogin.this,TMainActivity.class);
        startActivity(i);
    }


}

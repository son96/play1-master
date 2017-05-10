package com.example.android.play;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.play.R.id.toolbar;
import static com.example.android.play.R.styleable.MenuItem;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener{
TextView psignup;
    EditText editTextUsername,editTextPassword;
    private boolean loggedIn = false;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);


        psignup=(TextView) findViewById(R.id.psignup);
        Button plogin=(Button)findViewById(R.id.clicklogin);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
        editTextUsername= (EditText) findViewById(R.id.pusername);
        editTextPassword=(EditText)findViewById(R.id.ppassword);

        psignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progress;

                progress = new ProgressDialog(view.getContext());
                progress.setTitle("Please Wait!!");
                progress.setMessage("Wait!!");
                progress.setCancelable(true);
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setProgress(0);
                progress.setMax(100);

                progress.show(); Intent i = new Intent(ParentLogin.this,ParentSignup.class);
                startActivity(i);
                progress.dismiss();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.P_LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();
                        Toast.makeText(ParentLogin.this,response,Toast.LENGTH_LONG).show();
                        if(response.toString().trim().equals("logged in successfully"))
                        {



                            SharedPreferences settings = getSharedPreferences(Config.SHARED_PREF_NAME, 0); // 0 - for private mode
                            SharedPreferences.Editor editor = settings.edit();

//Set "hasLoggedIn" to true
                            editor.putBoolean("hasLoggedIn", true);
                            editor.putString("Username",editTextUsername.getText().toString());
                            Config.PUSERNAME = editTextUsername.getText().toString();


                            Intent i=new Intent(ParentLogin.this,PMainActivity.class);
                            startActivity(i);

                           // next();
                        //
                         }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParentLogin.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(Config.P_KEY_USERNAME, username);
                params.put(Config.P_KEY_PASSWORD,password);

                return params;
            }

        };

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
            Intent intent = new Intent(ParentLogin.this, PMainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
    //    NavUtils.navigateUpFromSameTask(activity);
    }
}

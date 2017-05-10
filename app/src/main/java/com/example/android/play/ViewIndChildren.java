package com.example.android.play;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Mukesh on 30-04-2017.
 */

public class ViewIndChildren extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextpname;

    private Button buttonUpdate;
    private Button buttonAdd;

    private String id,name,desg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.children);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextpname = (EditText) findViewById(R.id.editTextpname);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewIndChildren.this,UpdateDetails.class);
                intent.putExtra("id",id);
                intent.putExtra("cname",name);
                intent.putExtra("cage",desg);
                Log.d("id",id);
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewIndChildren.this,ViewDetails.class);
                intent.putExtra("id",id);

                Log.d("id",id);
//                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

                startActivity(intent);
                finish();

            }
        });

        //editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewIndChildren.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(Config.TAG_CHILD_ID,id);//fetch value for details

                String s = rh.sendPostRequest(Config.URL_GET_CHILD,hashMap);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
             name = c.getString(Config.TAG_CHILD_USERNAME);
             desg = c.getString(Config.TAG_CHILD_AGE);
             String sal = c.getString(Config.TAG_CHILD_PARENTNAME);
         //   Log.d(sal,"hello world");
            editTextName.setText(name);
            editTextAge.setText(desg);
            editTextpname.setText(sal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

/*
    private void updateEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String pname = editTextpname.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewIndChildren.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewIndChildren.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(Config.KEY_CHILD_ID,id);
                hashMap.put(Config.KEY_CHILD_USERNAME,name);
                hashMap.put(Config.KEY_CHILD_AGE,age);
                hashMap.put(Config.KEY_CHILD_PARENTNAME,pname);

                RequestHandler rh = new RequestHandler();

               String s = rh.sendPostRequest(Config.URL_UPDATE_CHILD,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getApplication(), "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplication(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_ADD_DETAILS, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

*/
    @Override
    public void onClick(View view) {

    }
}

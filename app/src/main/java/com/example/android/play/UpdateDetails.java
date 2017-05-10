package com.example.android.play;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

/**
 * Created by Mukesh on 30-04-2017.
 */

public class UpdateDetails extends AppCompatActivity implements View.OnClickListener {
    private RadioButton l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    private RadioButton b1,b2,b3,b4,b5;
    private Button update;
    private EditText cname,cage;
    private String id,name,age;
    private TextView a1,a2,a3,a4,a5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);



        Intent i=getIntent();
        id=i.getStringExtra("id");
        name=i.getStringExtra("cname");
        age=i.getStringExtra("cage");
        l1=(RadioButton) findViewById(R.id.radioButton11);
        l2=(RadioButton) findViewById(R.id.radioButton12);
        l3=(RadioButton) findViewById(R.id.radioButton21);
        l4=(RadioButton) findViewById(R.id.radioButton22);
        l5=(RadioButton) findViewById(R.id.radioButton31);
        l6=(RadioButton) findViewById(R.id.radioButton32);
        l7=(RadioButton) findViewById(R.id.radioButton41);
        l8=(RadioButton) findViewById(R.id.radioButton42);
        l9=(RadioButton) findViewById(R.id.radioButton51);
        l10=(RadioButton) findViewById(R.id.radioButton52);



        update=(Button)findViewById(R.id.buttonUpdate);

        a1=(TextView)findViewById(R.id.ans1);
        a2=(TextView)findViewById(R.id.ans2);
        a3=(TextView)findViewById(R.id.ans3);
        a4=(TextView)findViewById(R.id.ans4);
        a5=(TextView)findViewById(R.id.ans5);
        cname=(EditText) findViewById(R.id.editTextName);
        cage=(EditText)findViewById(R.id.editTextAge);


        cname.setText(name);
        cage.setText(age);
        update.setOnClickListener(this);
        //update();
        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateDetails.this,"Fetching...","Wait...",false,false);
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
//                hashMap.put(Config.KEY_CHILD_ID,id);
                hashMap.put(Config.TAG_CHILD_ID, id);

                String s = rh.sendPostRequest(Config.URL_VIEW_FOR_UPDATE,hashMap);
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



            String name = c.getString(Config.TAG_CHILD_USERNAME);
            String desg = c.getString(Config.TAG_CHILD_AGE);
            String s1=c.getString(Config.TAG_L1);
            String s2=c.getString(Config.TAG_L2);
            String s3=c.getString(Config.TAG_L3);
            String s4=c.getString(Config.TAG_L4);
            String s5=c.getString(Config.TAG_L5);

            // String sal = c.getString(Config.TAG_CHILD_PARENTNAME);
            //   Log.d(sal,"hello world");
            cname.setText(name);
            cage.setText(desg);
            a1.setText(s1);
            a2.setText(s2);
            a3.setText(s3);
            a4.setText(s4);
            a5.setText(s5);
                // editTextpname.setText(sal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String name = cname.getText().toString().trim();
        final String age = cage.getText().toString().trim();
        final String s1,s2,s3,s4,s5;
    /*    s1=b1.getText().toString().trim();
        s2=b2.getText().toString().trim();
        s3=b3.getText().toString().trim();
        s4=b4.getText().toString().trim();
        s5=b5.getText().toString().trim();



        Log.v("S1",s1);
        Log.v("S2",s2);
        Log.v("S3",s3);
        */


       if(l1.isChecked())
           s1 = "yes";
   //     else (l2.isChecked())s1="no";
       else
           s1="no";


        if(l3.isChecked())
            s2 = "yes";
            //     else (l2.isChecked())s1="no";
        else
            s2="no";
        if(l5.isChecked())
            s3 = "yes";
            //     else (l2.isChecked())s1="no";
        else
            s3="no";
        if(l7.isChecked())
            s4 = "yes";
            //     else (l2.isChecked())s1="no";
        else
            s4="no";
        if(l9.isChecked())
            s5 = "yes";
            //     else (l2.isChecked())s1="no";
        else
            s5="no";
        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateDetails.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();


                Toast.makeText(UpdateDetails.this,s,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(Config.KEY_CHILD_ID,id);
                hashMap.put(Config.KEY_CHILD_USERNAME,name);
                hashMap.put(Config.KEY_CHILD_AGE,age);
                hashMap.put(Config.KEY_L1,s1);
                hashMap.put(Config.KEY_L2,s2);
                hashMap.put(Config.KEY_L3,s3);
                hashMap.put(Config.KEY_L4,s4);
                hashMap.put(Config.KEY_L5,s5);



                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_CHILD,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }


    @Override
    public void onClick(View v) {
        if(v == update){
            updateEmployee();
       /*                   int selectedId = l1.getCheckedRadioButtonId();
                b1 = (RadioButton) findViewById(selectedId);


                int selectedId2 = l2.getCheckedRadioButtonId();
                b2 = (RadioButton) findViewById(selectedId2);


                int selectedId3 = l3.getCheckedRadioButtonId();
                b3 = (RadioButton) findViewById(selectedId3);


                int selectedId4 = l4.getCheckedRadioButtonId();
                b4 = (RadioButton) findViewById(selectedId4);


                int selectedId5 = l5.getCheckedRadioButtonId();
                b5 = (RadioButton) findViewById(selectedId5);


       //         updateEmployee();


            //Intent i = new Intent(.this,UpdateDetails.class);
            //startActivity(i);
*/
        }


    }






}

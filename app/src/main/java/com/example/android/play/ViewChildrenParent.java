package com.example.android.play;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Mukesh on 04-05-2017.
 */

public class ViewChildrenParent extends AppCompatActivity {
    TextView cname,cage,pname;
    TextView l1,l2,l3,l4,l5;
    Button go_back;
    private String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_parents);
        cname=(TextView) findViewById(R.id.editTextName);
        cage=(TextView) findViewById(R.id.editTextAge);
        pname=(TextView) findViewById(R.id.editTextt1name);

        l1=(TextView)findViewById(R.id.response1);
        l2=(TextView)findViewById(R.id.response2);
        l3=(TextView)findViewById(R.id.response3);
        l4=(TextView)findViewById(R.id.response4);
        l5=(TextView)findViewById(R.id.response5);


        Intent intent = getIntent();


        id = intent.getStringExtra("id");
//        Log.d("id",id);
        getEmployee();

    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewChildrenParent.this,"Fetching...","Wait...",false,false);
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

                hashMap.put(Config.TAG_CHILD_ID, id);//fetch value for details
                hashMap.put(Config.TAG_KEY_PUSERNAME,Config.PUSERNAME);
                String s = rh.sendPostRequest(Config.URL_CHILDREN_PARENT_VIEW,hashMap);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            //Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_CHILD_USERNAME);
            String desg = c.getString(Config.TAG_CHILD_AGE);
            String sal = c.getString(Config.TAG_KEY_TUSERNAME);
            String le1 = c.getString(Config.TAG_L1);
            String le2 = c.getString(Config.TAG_L2);
            String le3 = c.getString(Config.TAG_L3);
            String le4 = c.getString(Config.TAG_L4);
            String le5 = c.getString(Config.TAG_L5);

            // /   Log.d(sal,"hello world");
            cname.setText(name);
            cage.setText(desg);
            pname.setText(sal);
            Log.d("pname",sal);
            //Toast.makeText(getApplicationContext(),sal,Toast.LENGTH_LONG).show();
            l1.setText(le1);
            l2.setText(le2);
            l3.setText(le3);
            l4.setText(le4);
            l5.setText(le5);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }









}

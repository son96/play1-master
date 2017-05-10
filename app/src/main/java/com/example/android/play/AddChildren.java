package com.example.android.play;

//import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mukesh on 19-04-2017.
 */

public class AddChildren extends Fragment implements View.OnClickListener {

    EditText editTextName,editTextAge,editTextParentname;
    Button submit;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("ADD CHILDREN");

        editTextName=(EditText)getView().findViewById(R.id.editTextName);
        editTextAge=(EditText)getView().findViewById(R.id.editTextAge);
        editTextParentname=(EditText)getView().findViewById(R.id.editTextParentName);
        submit=(Button)getView().findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_children,container,false);
    }

    private void registerUser(){
       // super.registerUser();
        final String tname = Config.TUSERNAME;
        final String username = editTextName.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final int a1=Integer.parseInt(age);
        final String parentname = editTextParentname.getText().toString().trim();
        if (TextUtils.isEmpty(parentname)) {
            Toast.makeText(getContext(), "Enter parent name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getContext(), "Enter children name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(age)) {
            Toast.makeText(getContext(), "Enter age!", Toast.LENGTH_SHORT).show();
            return;
        }


        if(a1>=8)
        {
            Toast.makeText(getContext(), "Age range should be less than 8!!!", Toast.LENGTH_SHORT).show();
            return;


        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.REGISTER_CHILD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                        if(response.toString().trim().equals("Children added successfully!!"))
                        {
                            //next();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(Config.KEY_CHILD_USERNAME,username);
                params.put(Config.KEY_CHILD_AGE,age);
                params.put(Config.KEY_CHILD_PARENTNAME, parentname);
                params.put(Config.KEY_TEACHER_NAME,tname);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

    }




    @Override
    public void onClick(View view) {
        registerUser();
    }
}

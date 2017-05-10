package com.example.android.play;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.play.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by R on 2/24/2017.
 */
public class ViewChildren extends Fragment {

    View view;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private String JSON_STRING;
   // private  String pid = "142001";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.all_children, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                //Toast.makeText(getContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(),ViewIndChildren.class);
                 i.putExtra("id",movie.getTitle());
                startActivity(i);

            }



            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        getJSON();


        return view;
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
//                Log.w("pid",Config.TUSERNAME);
                HashMap<String, String> employees = new HashMap<>();
                employees.put(Config.TAG_KEY_TUSERNAME, Config.TUSERNAME);
              //  employees.put(Config.TAG_NAME, name)
                String s = rh.sendPostRequest(Config.URL_GET_ALL,employees);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showEmployee() {
        JSONObject jsonObject = null;
        //  ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {

                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_CHILD_ID);
                String name = jo.getString(Config.TAG_CHILD_USERNAME);
                String des = jo.getString(Config.TAG_CHILD_AGE);

                Movie movie = new Movie(id,name,des);
                movieList.add(movie);
             /*   HashMap<String, String> employees = new HashMap<>();
                employees.put(Config.TAG_ID, id);
                employees.put(Config.TAG_NAME, name);*/
                //   list.add(employees);

            }

            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}


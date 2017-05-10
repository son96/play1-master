package com.example.android.play;
//

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mukesh on 19-04-2017.
 */


public class Menu1 extends Fragment {
    Button b1;
    EditText ed1;
    private WebView wv1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("CAMERA");
        b1=(Button)getActivity().findViewById(R.id.button);
        ed1=(EditText) getActivity().findViewById(R.id.editText);

        wv1=(WebView) getActivity().findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ed1.getText().toString();

                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                Log.d("url",""+url);
                wv1.loadUrl(url);
            }
        });


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera,container,false);
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
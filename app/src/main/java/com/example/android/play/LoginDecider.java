package com.example.android.play;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginDecider extends AppCompatActivity {
TextView tlogin,plogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_decider);
        tlogin=(TextView) findViewById(R.id.teacher);
        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginDecider.this,TeacherLogin.class);
                startActivity(i);
                finish();
            }
        });

        plogin=(TextView) findViewById(R.id.parent);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginDecider.this,ParentLogin.class);
                startActivity(i);
                finish();

            }
        });

    }
}

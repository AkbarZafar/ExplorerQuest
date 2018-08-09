package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class gamescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        Bundle extras = getIntent().getExtras();

        String user = extras.getString("value");


        TextView username=(TextView)findViewById(R.id.name);
        username.setText("Journey of: "+user);
    }
}

package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class bag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        String user=getIntent().getStringExtra("user");

        TextView username=(TextView)findViewById(R.id.Backpack);
        username.setText(user+"'s Backpack");
    }
}

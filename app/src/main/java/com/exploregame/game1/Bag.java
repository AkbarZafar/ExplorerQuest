package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

public class Bag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        String user=getIntent().getStringExtra("user");

        TextView username=(TextView)findViewById(R.id.Backpack);
        username.setText(user+"'s Backpack");
    }

    public void back(View x){
        Intent back=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(back);
        finish();
    }
}

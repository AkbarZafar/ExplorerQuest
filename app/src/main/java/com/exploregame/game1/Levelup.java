package com.exploregame.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Levelup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelup);


    }

    public void OK(View X){
        Intent back=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(back);
        finish();
    }
}

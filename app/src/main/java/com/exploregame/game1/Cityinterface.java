package com.exploregame.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cityinterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityinterface);
    }

    public void OK(View x){
        Intent okay= new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(okay);
        finish();
    }
}

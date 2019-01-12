package com.heekostudios.explorerquest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Levelup extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelup);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        level=sharedPreferences.getInt("level",1);

        TextView levelnow=(TextView)findViewById(R.id.levelnow);
        if (level==12){
            levelnow.setText("You've reached the max level!");
        }
        levelnow.setText("You are now level "+level);
    }

    @Override
    public void onBackPressed(){

    }

    public void OK(View X){
        Intent back=new Intent(getApplicationContext(),Selectperk.class);
        startActivity(back);
        finish();
    }
}

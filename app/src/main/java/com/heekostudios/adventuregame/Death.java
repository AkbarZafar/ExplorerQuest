package com.heekostudios.adventuregame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Death extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        name=sharedPreferences.getString("user",null);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        editor.putString("user",name);
        editor.apply();
    }

    @Override
    public void onBackPressed(){}

    public void restart(View X){
        Intent restart=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(restart);
        finish();
    }
}

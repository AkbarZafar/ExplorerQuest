package com.exploregame.game1;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;


public class Settings extends AppCompatActivity {

        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    public void resetname(View x){
        SharedPreferences.Editor editor = sharedPreferences.edit();


    }

    public void resetgame(View x){

    }

    public void cross(View y){
        finish();
    }
}

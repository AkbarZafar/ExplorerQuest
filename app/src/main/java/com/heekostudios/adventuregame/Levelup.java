package com.heekostudios.adventuregame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

public class Levelup extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    int level;
    TextView levelnumber;
    CircularProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelup);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        level=sharedPreferences.getInt("level",1);

        TextView levelnow= findViewById(R.id.levelnow);
        if (level==12){
            levelnow.setText(R.string.maxlevelreach);
        }
        levelnow.setText(getString(R.string.levelstate)+" "+level);


        levelnumber=findViewById(R.id.level);

        levelnumber.setText(""+(level-1));


        progressBar = findViewById(R.id.expfull);

        findViewById(R.id.selectperk).setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                progressBar.setProgressAnimationDuration(1500);
                progressBar.setProgress(100);
                progressBar.animate();
            }
        }, 250);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                levelnumber.setText(""+level);

                findViewById(R.id.selectperk).setEnabled(true);

            }
        }, 1750);

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

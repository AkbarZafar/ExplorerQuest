package com.heekostudios.explorerquest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Math.round;

public class Ditch extends AppCompatActivity {

    Integer HP, difficulty;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditch);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        unload();
        fall();
        updater();
    }

    public void unload() {
        HP = sharedPreferences.getInt("HP", 100);
        difficulty = sharedPreferences.getInt("difficulty", 1);
    }

    public void fall() {
        TextView fell = (TextView) findViewById(R.id.youfell);
        TextView dmglost = (TextView) findViewById(R.id.lost);

        fell.setText("You fell in a ditch");

        if (HP > 4 * difficulty) {
            Random random = new Random();

            double fall = random.nextInt(25) + 35;


            int hplost=(int)Math.round((fall/100)*HP);

            HP -= hplost;
            dmglost.setText("You lost " + hplost + " HP");
        } else {
            dmglost.setText("Luckily, you lost no HP");
        }


    }

    public void ok(View x) {

        Intent home = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(home);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        finish();
    }

    @Override
    public void onBackPressed() {

    }

    public void updater() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HP", HP);
        editor.putInt("difficulty", difficulty);
        editor.apply();
    }
}

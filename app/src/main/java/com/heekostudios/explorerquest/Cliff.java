package com.heekostudios.explorerquest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Random;

public class Cliff extends AppCompatActivity {

    Integer HP, difficulty;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliff);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        animation();
        unload();
        fall();
        updater();
    }

    public void animation() {

        TranslateAnimation walkoff = new TranslateAnimation(0, -750, 0, 100);
        walkoff.setDuration(750);

        RotateAnimation spin = new RotateAnimation(0, -270);
        spin.setDuration(700);
        spin.setStartOffset(600);

        TranslateAnimation falling = new TranslateAnimation(0, 150, 0, 900);
        falling.setDuration(850);
        falling.setStartOffset(650);

        final AnimationSet fall = new AnimationSet(true);

        fall.addAnimation(spin);
        fall.addAnimation(falling);
        fall.addAnimation(walkoff);
        fall.setFillAfter(true);


        findViewById(R.id.player).startAnimation(fall);

        fall.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void unload() {
        HP = sharedPreferences.getInt("HP", 50);
        difficulty = sharedPreferences.getInt("difficulty", 1);
    }

    public void fall() {
        TextView fell = findViewById(R.id.youfell);
        TextView dmglost = findViewById(R.id.lost);

        fell.setText(R.string.fell_in_ditch);

        if (HP > 4 * difficulty) {
            Random random = new Random();

            double fall = random.nextInt(6) + 17;


            int hplost = (int) Math.round((fall / 100) * HP);

            HP -= hplost;
            dmglost.setText("You lost " + hplost + " HP");
        } else {
            dmglost.setText(R.string.lost_no_hp);
        }


    }

    public void OK(View X) {

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
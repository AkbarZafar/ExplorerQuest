package com.exploregame.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;


public class Terrain extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain);

        gif();
        backgroundset();
        walker();
    }

    public void backgroundset() {


        ImageView background = (ImageView) findViewById(R.id.background);

        Random rand = new Random();

        int i = rand.nextInt(5);

        switch (i) {
            case 0: {
                background.setImageResource(R.drawable.mountains_grassnrock);
                break;
            }
            case 1: {
                background.setImageResource(R.drawable.forest);
                break;
            }
            case 2: {
                background.setImageResource(R.drawable.mountains_rock);
                break;
            }
            case 3: {
                background.setImageResource(R.drawable.mountains_winter);
                break;
            }
            case 4: {
                background.setImageResource(R.drawable.pyramids);
                break;
            }

        }
    }

    public void walker() {

        image = findViewById(R.id.idle);
        Animation walk = new TranslateAnimation(0, -1700, 0, 0);
        walk.setDuration(2000);

        image.startAnimation(walk);

        walk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setX(-1000);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void gif() {
        try {
            GifDrawable warrior = new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold = findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

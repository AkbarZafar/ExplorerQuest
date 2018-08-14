package com.exploregame.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;


public class Terrain extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain);

        gif();
        walker();

    }


    public void walker(){

        image = findViewById(R.id.idle);
        Animation walk = new TranslateAnimation(0, -1500, 0, 0);
        walk.setDuration(2000);

        image.startAnimation(walk);

        walk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setX(-1000);
                cross();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    public void gif(){
        try{
            GifDrawable warrior=new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold=findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cross(){
        Intent backer=new Intent(getApplicationContext(),Gamescreen.class);

        startActivity(backer);
        finish();
    }

}

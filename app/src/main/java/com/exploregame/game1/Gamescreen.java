package com.exploregame.game1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Random;

import javax.xml.datatype.Duration;

import pl.droidsonroids.gif.GifDrawable;

public class Gamescreen extends AppCompatActivity {

    String user;
    Integer HP, money, level, experience,randoms;
    TranslateAnimation walk;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        extract();
        gif();
        setup();

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

    public void setup(){
        TextView username=(TextView)findViewById(R.id.name);
        username.setText(user);

        TextView mula=(TextView)findViewById(R.id.money);
        mula.setText(""+money);

        TextView hp=(TextView)findViewById(R.id.HP);
        hp.setText(""+HP);

        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress(experience);

        TextView leveldisplay=(TextView)findViewById(R.id.level);
        leveldisplay.setText(""+level);
    }

    public void extract(){
         user = getIntent().getStringExtra("user");
         money = getIntent().getIntExtra("money", 0);
         HP = getIntent().getIntExtra("HP", 100);
         level=getIntent().getIntExtra("level",1);
         experience=getIntent().getIntExtra("experience",0);
    }

    public void backpack(View x){
        Intent bag=new Intent(getApplicationContext(), Bag.class);
        bag.putExtra("user", user);
        startActivity(bag);
        finish();
    }

     public void explore(View view){
         Button press=findViewById(R.id.explore);
         press.setEnabled(false);
         Button bagpress=findViewById(R.id.inventory);
         bagpress.setEnabled(false);
         ImageButton presssettings=findViewById(R.id.settingsbutton);
         presssettings.setEnabled(false);

         walker();
     }

    public void walker() {


        image = findViewById(R.id.idle);
        walk = new TranslateAnimation(0, -1000, 0, 0);
        walk.setDuration(1500);
        walk.setFillAfter(true);

        image.startAnimation(walk);

        walk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {

                randomer();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
    public void randomer(){
        Random rand=new Random();
        randoms=rand.nextInt(5)+1;

        int events=rand.nextInt(6);

        background();

        switch (events){
            case 0:{
                
            break;}
            case 1:{

                break;}
            case 2:{

                break;}
            case 3:{

                break;}
            case 4:{

                break;}
            case 5:{

                break;}
        }

        resetpage();

    }

    public void background(){
        Intent explorer=new Intent(getApplicationContext(),Terrain.class);
        for (int i=0;i<=randoms;i++){
            startActivity(explorer);
        }
    }

    public void resetpage(){

        Button press=findViewById(R.id.explore);
        press.setEnabled(true);
        Button bagpress=findViewById(R.id.inventory);
        bagpress.setEnabled(true);
        ImageButton presssettings=findViewById(R.id.settingsbutton);
        presssettings.setEnabled(true);


        ImageView character=(ImageView)findViewById(R.id.idle);
        TranslateAnimation setpostion=new TranslateAnimation(1500,0,0,0);
        setpostion.setDuration((randoms+1)*3100);
        setpostion.setFillAfter(true);
        character.startAnimation(setpostion);

        setpostion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                //image.setX(490);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

     public void settings(View y){
        Intent settings=new Intent(getApplicationContext(),Settings.class);

        startActivity(settings);
        finish();
     }
}

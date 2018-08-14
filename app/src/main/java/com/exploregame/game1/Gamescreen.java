package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import org.w3c.dom.Text;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Gamescreen extends AppCompatActivity {

    String user;
    Integer HP, money, level, experience;
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
    }

     public void explore(View view){
         Button press=findViewById(R.id.explore);
         press.setEnabled(false);
         Button bagpress=findViewById(R.id.inventory);
         bagpress.setEnabled(false);

         ImageView image=findViewById(R.id.idle);
         TranslateAnimation walk= new TranslateAnimation(0,-1000,0,0);
         walk.setDuration(1000);

         image.startAnimation(walk);

     }

     public void settings(View y){
        Intent settings=new Intent(getApplicationContext(),Settings.class);
        startActivity(settings);
     }
}

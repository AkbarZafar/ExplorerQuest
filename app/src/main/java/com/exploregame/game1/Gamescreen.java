package com.exploregame.game1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    SharedPreferences sharedPreferences;
    String user;
    Integer HP, money, level, experience,randoms,apple,bread,rawmeat;
    TranslateAnimation walk;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        warriorwalkon();
        extract();

        levelcheck();
        gif();
        setup();

    }

    public void levelcheck(){
        if (experience>=100){
            level=level+1;
            experience=experience-100;

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("experience",experience);
            editor.putInt("level",level);
            editor.commit();

            Intent levelup=new Intent(getApplicationContext(),Levelup.class);
            startActivity(levelup);
            finish();
        }else{

        }
    }

    public void warriorwalkon(){
        ImageView character=(ImageView)findViewById(R.id.idle);
        TranslateAnimation setpostion=new TranslateAnimation(1500,0,0,0);

        if (randoms!=null) {
            setpostion.setDuration((randoms)+1* 3100);
        }else{
            setpostion.setDuration(1000);
        }
        setpostion.setFillAfter(true);
        character.startAnimation(setpostion);

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
        user=sharedPreferences.getString("user",user);
        HP=sharedPreferences.getInt("HP",100);
        money=sharedPreferences.getInt("money",0);
        level=sharedPreferences.getInt("level",1);
        experience=sharedPreferences.getInt("experience",0);
        apple=sharedPreferences.getInt("apple",0);
        rawmeat=sharedPreferences.getInt("rawmeat",0);
        bread=sharedPreferences.getInt("bread",0);
    }

    public void backpack(View x){
        Intent bag=new Intent(getApplicationContext(), Bag.class);
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
        walk.setDuration(1000);
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
        randoms=rand.nextInt(3);

        background();
    }

    public void background(){
        Intent explorer=new Intent(getApplicationContext(),Terrain.class);

        Intent itemfound=new Intent(getApplicationContext(),Found.class);
        startActivity(itemfound);

        for (int i=0;i<=randoms;i++){
            startActivity(explorer);
        }
        finish();
    }

     public void settings(View y){
        Intent settings=new Intent(getApplicationContext(),Settings.class);

        startActivity(settings);
        finish();
     }
}

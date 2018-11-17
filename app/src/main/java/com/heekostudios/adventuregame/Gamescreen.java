package com.heekostudios.adventuregame;

import android.content.SharedPreferences;
import android.os.Handler;
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

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;

public class Gamescreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String user;
    Integer HP, money, level, experience, randoms, apple, bread, rawmeat, attack, defence, maxhp, city, distance, difficulty, cookedmeat, lifesteal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        extract();

        setup();

        warriorwalkon();
        cityset();
        gif();
    }

    public void cityset() {
        if (city == 0) {
            Random random = new Random();
            city = random.nextInt(5) + 7;
            distance=0;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("city", city);
            editor.apply();
        }
        citycheck();
    }

    public void levelcheck() {
        if (experience >= 100 && level < 12) {
            level += 1;
            experience -= 100;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("experience", experience);
            editor.putInt("level", level);
            editor.apply();

            Intent levelup = new Intent(getApplicationContext(), Levelup.class);
            startActivity(levelup);
            finish();
        } else if (level == 12) {
            experience = 100;
        }
    }

    public void warriorwalkon() {
        ImageView character = findViewById(R.id.idle);
        TranslateAnimation setpostion = new TranslateAnimation(550, 0, 0, 0);


        setpostion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                extract();
                hpsetup();
                lvlsetup();
                levelcheck();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        setpostion.setDuration(500);
        setpostion.setFillAfter(true);


        character.startAnimation(setpostion);

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

    public void gifwalk(){
        try {
            GifDrawable warrior = new GifDrawable(getResources(), R.drawable.warriorwalk);
            ImageView gifhold = findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup() {
        TextView username = findViewById(R.id.name);
        username.setText(user);

        TextView mula = findViewById(R.id.money);
        mula.setText("" + money);

        hpsetup();

        lvlsetup();
    }

    public void lvlsetup() {
        final TextView leveldisplay = findViewById(R.id.level);
        final CircularProgressBar progressBar = findViewById(R.id.progress_bar);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (level == 12) {
                    progressBar.setProgressAnimationDuration(5000);
                    progressBar.setProgress(100);
                    progressBar.animate();
                    leveldisplay.setText("12");
                } else {
                    progressBar.setProgressAnimationDuration(500);
                    progressBar.setProgress(experience);
                    progressBar.animate();
                    leveldisplay.setText("" + level);

                }
            }
        }, 20);
    }

    public void hpsetup() {

        TextView hp = findViewById(R.id.HP);
        hp.setText(HP + "/" + maxhp);
    }

    public void extract() {
        user = sharedPreferences.getString("user", user);
        HP = sharedPreferences.getInt("HP", 100);
        money = sharedPreferences.getInt("money", 0);
        level = sharedPreferences.getInt("level", 1);
        experience = sharedPreferences.getInt("experience", 0);
        apple = sharedPreferences.getInt("apple", 0);
        rawmeat = sharedPreferences.getInt("rawmeat", 0);
        bread = sharedPreferences.getInt("bread", 0);
        attack = sharedPreferences.getInt("attack", 1);
        defence = sharedPreferences.getInt("defence", 1);
        maxhp = sharedPreferences.getInt("maxhp", 100);
        distance = sharedPreferences.getInt("distance", 0);
        city = sharedPreferences.getInt("city", 0);
        difficulty = sharedPreferences.getInt("difficulty", 1);
        cookedmeat = sharedPreferences.getInt("cookedmeat", 0);
        lifesteal = sharedPreferences.getInt("lifesteal", 0);
    }

    public void backpack(View x) {
        Intent bag = new Intent(getApplicationContext(), Bag.class);
        startActivity(bag);

        warriorwalkon();
    }

    public void explore(View view) {

        gifwalk();

        distance += 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("distance", distance);
        editor.apply();

        Button press = findViewById(R.id.explore);
        press.setEnabled(false);

        Button bagpress = findViewById(R.id.inventory);
        bagpress.setEnabled(false);

        ImageButton presssettings = findViewById(R.id.settingsbutton);
        presssettings.setEnabled(false);

        walker();
    }

    public void walker() {

        ImageView image = findViewById(R.id.idle);
        TranslateAnimation walk = new TranslateAnimation(0, -1500, 0, 0);
        walk.setDuration(1350);
        walk.setFillAfter(true);

        image.startAnimation(walk);

        walk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                background();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    public void background() {
        Intent explorer = new Intent(getApplicationContext(), Terrain.class);

        event();

        Random rand = new Random();
        randoms = rand.nextInt(3);

        for (int i = 0; i <= randoms; i++) {
            startActivity(explorer);
        }
    }

    public void event() {

        Random random = new Random();
        int events = random.nextInt(11);

        //int events=10;
        switch (events) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                Intent home=new Intent(getApplicationContext(),Gamescreen.class);
                startActivity(home);
                Intent item = new Intent(getApplicationContext(), Found.class);
                startActivity(item);
                break;
            }
            case 6:
            case 7:
            case 8:
            case 9: {
                Intent fight = new Intent(getApplicationContext(), Fight.class);
                startActivity(fight);
                break;
            }
            case 10: {
                Intent ditch = new Intent(getApplicationContext(), Cliff.class);
                startActivity(ditch);
                break;
            }
            //case 4:{
            //hut
            //break;}
            default: {

            }
        }
        finish();
    }

    public void citycheck() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (distance == city) {
            difficulty += 1;
            editor.putInt("difficulty", difficulty);
            editor.apply();

            Intent reach = new Intent(getApplicationContext(), Cityinterface.class);
            startActivity(reach);

            city=0;

            cityset();
            finish();
        } else {

            editor.putInt("distance", distance);
            editor.apply();
        }
    }

    public void settings(View y) {
        Intent settings = new Intent(getApplicationContext(), Settings.class);

        startActivity(settings);
        finish();
    }
}

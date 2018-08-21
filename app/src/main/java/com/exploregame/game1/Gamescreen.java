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
    Integer HP, money, level, experience, randoms, apple, bread, rawmeat, attack, defence, maxhp, city, distance, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        warriorwalkon();
        extract();
        cityset();
        gif();
        setup();

    }

    public void cityset() {
        if (city == 0) {
            Random random = new Random();
            city = random.nextInt(5) + 20;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("city", city);
            editor.apply();
        }
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
        ImageView character = (ImageView) findViewById(R.id.idle);
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

    public void setup() {
        TextView username = (TextView) findViewById(R.id.name);
        username.setText(user);

        TextView mula = (TextView) findViewById(R.id.money);
        mula.setText("" + money);

        hpsetup();

        lvlsetup();
    }

    public void lvlsetup() {
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress(experience);

        TextView leveldisplay = (TextView) findViewById(R.id.level);
        leveldisplay.setText("" + level);
    }

    public void hpsetup() {

        TextView hp = (TextView) findViewById(R.id.HP);
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
    }

    public void backpack(View x) {
        Intent bag = new Intent(getApplicationContext(), Bag.class);
        startActivity(bag);

        warriorwalkon();
    }

    public void explore(View view) {

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
        TranslateAnimation walk = new TranslateAnimation(0, -1000, 0, 0);
        walk.setDuration(1000);
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

        citycheck();

        Random random = new Random();
        int events = random.nextInt(4);

        //int events=3;
        switch (events) {
            case 0:
            case 1: {
                Intent item = new Intent(getApplicationContext(), Found.class);
                startActivity(item);
                break;
            }
            case 2: {
                Intent fight = new Intent(getApplicationContext(), Fight.class);
                startActivity(fight);
                break;
            }
            case 3: {
                Intent ditch = new Intent(getApplicationContext(), Ditch.class);
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
        distance += 1;
        if (distance == city) {
            difficulty += 1;

            Intent reach = new Intent(getApplicationContext(), Cityinterface.class);
            startActivity(reach);

            cityset();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
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

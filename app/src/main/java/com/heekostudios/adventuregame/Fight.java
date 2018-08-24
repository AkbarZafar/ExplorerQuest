package com.heekostudios.adventuregame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;

public class Fight extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    int enemyhp, enemymax, HP, attack, defence, maxhp, difficulty, enemy,lifesteal,experience;
    Boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

        unload();
        setup();
    }

    public void unload() {
        HP = sharedPreferences.getInt("HP", 100);
        defence = sharedPreferences.getInt("defence", 1);
        attack = sharedPreferences.getInt("attack", 1);
        maxhp = sharedPreferences.getInt("maxhp", 100);
        difficulty = sharedPreferences.getInt("difficulty", 1);
        lifesteal=sharedPreferences.getInt("lifesteal",0);
        experience=sharedPreferences.getInt("experience",0);
    }

    public void setup() {
        healthsetup();
        gifsetup();

    }

    public void gifsetup() {
        try {
            GifDrawable warrior = new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold = findViewById(R.id.player);
            gifhold.setImageDrawable(warrior);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random rnd=new Random();
        int i=rnd.nextInt(4);

        try {
        switch(i){
            case 0:{
                enemy=1;
                GifDrawable zombie = new GifDrawable(getResources(), R.drawable.zombieidle);
                ImageView gifhold = findViewById(R.id.enemypicture);
                gifhold.setImageDrawable(zombie);
                break;}
            case 1:{
                enemy=2;
                GifDrawable troll = new GifDrawable(getResources(), R.drawable.zombieidle);
                ImageView gifhold = findViewById(R.id.enemypicture);
                gifhold.setImageDrawable(troll);
                break;}
            case 2:{
                enemy=3;
                GifDrawable birod = new GifDrawable(getResources(), R.drawable.zombieidle);
                ImageView gifhold = findViewById(R.id.enemypicture);
                gifhold.setImageDrawable(birod);
                break;}
            case 3:{
                enemy=4;
                GifDrawable monster = new GifDrawable(getResources(), R.drawable.zombieidle);
                ImageView gifhold = findViewById(R.id.enemypicture);
                gifhold.setImageDrawable(monster);
                break;}
        }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void healthsetup() {
        Random rander = new Random();
        enemymax = rander.nextInt(5) + (10 * difficulty);
        enemyhp = enemymax;

        healthupdate();
    }

    public void healthupdate() {

        int enemypercent = (enemyhp * 100) / enemymax;
        int HPpercent = (HP * 100) / maxhp;

        ProgressBar playerhp = (ProgressBar) findViewById(R.id.playerhealth);
        ProgressBar enemy = (ProgressBar) findViewById(R.id.enemyhealth);

        TextView txtplayerhp = (TextView) findViewById(R.id.txtplayerhealth);
        TextView txtenemyhp = (TextView) findViewById(R.id.txtenemyhealth);

        playerhp.setProgress(HPpercent);
        enemy.setProgress(enemypercent);

        txtenemyhp.setText(enemyhp + "/" + enemymax);
        txtplayerhp.setText(HP + "/" + maxhp);

    }

    public void bag(View x) {
        Intent bag = new Intent(getApplicationContext(), Bag.class);
        startActivity(bag);
        backhere();
    }

    public void enemyattack() {
        //animation

        Random rander = new Random();


        double dmg = rander.nextInt(4) + (4 * difficulty);
        if (defence == 5) {
            dmg *= (1 - 0.18);
        } else {
            dmg *= (1 - (defence * 0.03));
        }

        HP -= ((int) Math.round(dmg));
    }

    public void runaway(View x) {
        Random rander = new Random();
        int i = rander.nextInt(6);
        TextView norun = (TextView) findViewById(R.id.falserun);

        if (i == 0) {
            norun.setVisibility(View.GONE);
            success();
        } else {
            enemyattack();
            norun.setText("Unable to run away");
            norun.setVisibility(View.VISIBLE);
            healthupdate();
            updater();
        }
    }

    public void playerhitanimation(){

    }

    public void playerhit() {
        playerhitanimation();
        Random rander = new Random();

        int dmg;
        double lifestealheal;

        if (attack == 5) {
            dmg = rander.nextInt(4) + 35;
        } else {
            dmg = rander.nextInt(4) + (attack * 6);
        }
        enemyhp -= dmg;

        lifestealheal=dmg*(0.05*lifesteal);
        HP+=((int) Math.round(lifestealheal));
    }

    public void hit(View x) {
        TextView norun = (TextView) findViewById(R.id.falserun);
        norun.setVisibility(View.GONE);

        //disabler();
        playerhit();
        enemyattack();
        healthupdate();
        fighttester();
        updater();
    }

    public void enabler(){
        Button attackbtn = (Button) findViewById(R.id.Buttonhit);
        Button runbtn = (Button) findViewById(R.id.buttonrun);
        Button bagbtn = (Button) findViewById(R.id.buttonbag);

        attackbtn.setEnabled(true);
        runbtn.setEnabled(true);
        bagbtn .setEnabled(true);
    }

    public void disabler() {
        Button attackbtn = (Button) findViewById(R.id.Buttonhit);
        Button runbtn = (Button) findViewById(R.id.buttonrun);
        Button bagbtn = (Button) findViewById(R.id.buttonbag);

        attackbtn.setEnabled(false);
        runbtn.setEnabled(false);
        bagbtn .setEnabled(false);
}

    public void fighttester() {
        if (HP <= 0) {
            //dead animation... lose animation...
            Intent lose = new Intent(getApplicationContext(), Death.class);
            startActivity(lose);
            finish();
        } else if (enemyhp <= 0) {
            enemyhp = 0;
            healthupdate();
            //dead animation... win animation...
            win();
        }
    }

    public void updater() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HP", HP);
        editor.putInt("experience",experience);
        editor.apply();
    }

    public void backhere() {
        ImageView character = (ImageView) findViewById(R.id.player);
        TranslateAnimation setpostion = new TranslateAnimation(0, -100, 0, 0);


        setpostion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                unload();
                healthupdate();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        setpostion.setDuration(500);


        character.startAnimation(setpostion);
    }

    public void win() {
        TextView won = (TextView) findViewById(R.id.Won);
        won.setText("You Won!");

        Random rander=new Random();
        experience+=rander.nextInt(12)+5;

        win = true;

        setuptwo();
    }

    public void success() {
        TextView won = (TextView) findViewById(R.id.Won);
        won.setText("You successfuly ran away!");

        Random rander=new Random();
        experience+=rander.nextInt(5)+1;

        win=false;

        setuptwo();
    }

    public void setuptwo() {
        Button hit = (Button) findViewById(R.id.Buttonhit);
        hit.setVisibility(View.GONE);

        Button bag = (Button) findViewById(R.id.buttonbag);
        bag.setVisibility(View.GONE);

        Button run = (Button) findViewById(R.id.buttonrun);
        run.setVisibility(View.GONE);

        Button resume = (Button) findViewById(R.id.Continue);
        resume.setVisibility(View.VISIBLE);

        TextView message = (TextView) findViewById(R.id.Won);
        message.setVisibility(View.VISIBLE);
    }

    public void exit(View x) {
        Intent home = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(home);

        if (win) {
            loot();
        }

        finish();
    }

    @Override
    public void onBackPressed() {

    }

    public void loot() {
        Intent item = new Intent(getApplicationContext(), Found.class);

        Random rander = new Random();
        int i = rander.nextInt(6);

        switch (i) {
            case 0:
            case 1: {
                for (int y = 0; y <= 1; y++) {
                    startActivity(item);
                }
                break;
            }
            case 2:
            case 3:
            case 4: {
                startActivity(item);
                break;
            }
        }
        finish();
    }

}

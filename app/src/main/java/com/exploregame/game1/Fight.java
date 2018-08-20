package com.exploregame.game1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Random;

public class Fight extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    int enemyhp, enemymax, HP, attack, defence, maxhp, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        unload();
        setup();
    }

    public void unload(){
        HP=sharedPreferences.getInt("HP",100);
        defence=sharedPreferences.getInt("defence",1);
        attack=sharedPreferences.getInt("attack",1);
        maxhp=sharedPreferences.getInt("maxhp",100);
        difficulty=sharedPreferences.getInt("difficulty",1);
    }

    public void setup(){
        healthsetup();
        enemypick();

    }

    public void enemypick(){
        Random rander=new Random();
        int i=rander.nextInt(4);

        switch (i){
            case 0:{

            break;}
            case 1:{

                break;}
            case 2:{

                break;}
            case 3:{

                break;}
        }
    }

    public void healthsetup(){
        Random rander=new Random();
        enemymax=rander.nextInt(5)+(10*difficulty);
        enemyhp=enemymax;

        healthupdate();
    }

    public void healthupdate(){

        int enemypercent=(enemyhp*100)/enemymax;
        int HPpercent=(HP*100)/maxhp;

        ProgressBar playerhp=(ProgressBar)findViewById(R.id.playerhealth);
        ProgressBar enemy=(ProgressBar)findViewById(R.id.enemyhealth);

        TextView txtplayerhp=(TextView)findViewById(R.id.txtplayerhealth);
        TextView txtenemyhp=(TextView)findViewById(R.id.txtenemyhealth);

        playerhp.setProgress(HPpercent);
        enemy.setProgress(enemypercent);

        txtenemyhp.setText(enemyhp+"/"+enemymax);
        txtplayerhp.setText(HP+"/"+maxhp);

    }

    public void bag(View x){
        Intent bag=new Intent(getApplicationContext(),Bag.class);
        startActivity(bag);
        backhere();
    }

    public void enemyattack(){
        //animation

        Random rander=new Random();
        double dmg=rander.nextInt(4)+(4*difficulty);

        dmg=(1-(defence*0.03))*dmg;


        HP-=((int) Math.round(dmg));
    }

    public void run(View x){
        Random rander=new Random();
        int i=rander.nextInt(6);
        Intent runaway=new Intent(getApplicationContext(),Gamescreen.class);

        if (i==0){
            success();
        }else{
            enemyattack();
            TextView norun=(TextView)findViewById(R.id.falserun);
            norun.setText("Unable to run away");
            norun.setVisibility(View.VISIBLE);
        }
    }

    public void playerhit(){
        //animation
        Random rander=new Random();
        int dmg=rander.nextInt(4)+(attack*6);

        enemyhp-=dmg;
    }

    public void hit(View x){
        TextView norun=(TextView)findViewById(R.id.falserun);
        norun.setVisibility(View.GONE);

        playerhit();
        //disabler();
        enemyattack();
        healthupdate();
        fighttester();
        updater();
    }

    public void disabler(){
        Button attack=(Button)findViewById(R.id.Buttonhit);
        Button run=(Button)findViewById(R.id.buttonrun);
        Button bag=(Button)findViewById(R.id.buttonbag);

    }

    public void fighttester(){
        if (HP<=0){
            //dead animation... lose animation...
            Intent lose=new Intent(getApplicationContext(),Death.class);
            startActivity(lose);
            finish();
        }else if (enemyhp<=0){
            //dead animation... win animation...
            success();
        }
    }

    public void updater(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("HP",HP);
        editor.apply();
    }

    public void backhere(){
        ImageView character=(ImageView)findViewById(R.id.asd);
        TranslateAnimation setpostion=new TranslateAnimation(0,-100,0,0);


        setpostion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                unload();
                healthupdate();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        setpostion.setDuration(500);


        character.startAnimation(setpostion);
    }

    public void success(){
        updater();

        Intent home=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(home);
        finish();
    }

}

package com.heekostudios.adventuregame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Death extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String user;
    Integer highscore,score,HP, money, level, experience,difficulty, distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        extract();
        setscore();
        reset();
    }

    public void setscore(){
        TextView txthighscore=findViewById(R.id.highscore);
        TextView txtscore=findViewById(R.id.score);

        score=(HP)+((difficulty-1)*150)+((level-1)*100)+(money*2)+(experience)+(distance*3);
        if(score>highscore){
            highscore=score;
            txthighscore.setText(R.string.newhighscore);
        }else{
            txthighscore.setText(getString(R.string.highscoredisplay)+highscore);
        }
        txtscore.setText(getString(R.string.scoredisplay)+score);
    }

    public void reset(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void extract(){
        user=sharedPreferences.getString("user",null);
        highscore=sharedPreferences.getInt("highscore",0);
        HP = sharedPreferences.getInt("HP", 100);
        money = sharedPreferences.getInt("money", 0);
        level = sharedPreferences.getInt("level", 1);
        experience = sharedPreferences.getInt("experience", 0);
        distance = sharedPreferences.getInt("distance", 0);
        difficulty = sharedPreferences.getInt("difficulty", 1);
    }

    @Override
    public void onBackPressed(){}

    public void restart(View X){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        reset();

        editor.putString("user",user);
        editor.apply();

        Intent restart=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(restart);
        finish();
    }

    public void home(View x){
        reset();

        Intent restart=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(restart);
        finish();
    }
}

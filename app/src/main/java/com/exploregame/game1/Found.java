package com.exploregame.game1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Found extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Integer HP,money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        unload();
        event();
        updater();
    }

    public void unload(){
        HP=sharedPreferences.getInt("HP",100);
        money=sharedPreferences.getInt("money",0);
    }

    public void updater(){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("HP",HP);
        editor.putInt("money",money);
        editor.commit();
    }

    public void event(){
        Random random=new Random();
        //int events=random.nextInt(5);

        int events=2;
        switch (events){
            case 0:{
                itemfind();
                break;}
            case 1:{
                //monster
                break;}
            case 2:{
                ditch();
                break;}
            case 3:{
                //hut
                break;}
            default:{

            }
        }

    }

    public void ditch(){
        TextView fell=(TextView)findViewById(R.id.youfound);
        TextView dmglost=(TextView)findViewById(R.id.founditem);

        fell.setText("You fell in a ditch");

        Random random=new Random();

        int fall=random.nextInt(20)+1;
        dmglost.setText("You lost "+fall+" HP");
        HP=HP-fall;
    }

    public void itemfind(){

        TextView founditem=(TextView)findViewById(R.id.founditem);
        ImageView item=(ImageView)findViewById(R.id.item_found);

        TextView foundorlost=(TextView)findViewById(R.id.youfound);
        foundorlost.setText("You Found:");

        Random rander=new Random();
        //int i=rander.nextInt(5);
        int i=2;
        switch (i){
            case 0:{
                //rawmeat
                founditem.setText("Raw meat");
                break;}
            case 1:{
                //apple
                founditem.setText("An apple");
                break;}
            case 2:{
                //moneysmall
                int c=rander.nextInt(25)+2;
                founditem.setText(c+" Gold");
                money=c+money;
                break;}
            case 3:{
                //moneybigrange
                int c=rander.nextInt(45)+2;
                founditem.setText(c+" Gold");
                money=c+money;
                break;}
            case 4:{
                //breadinabag
                founditem.setText("A loaf of bread");
                break;}
        }
    }

    public void OK(View X){
        Intent OK=new Intent(getApplicationContext(),Gamescreen.class);
        OK.putExtra("HP",HP);
        OK.putExtra("money",money);
        startActivity(OK);
        finish();
    }
}

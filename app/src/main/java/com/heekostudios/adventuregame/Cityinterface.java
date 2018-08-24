package com.heekostudios.adventuregame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Cityinterface extends AppCompatActivity {

    TextView desc;
    SharedPreferences sharedPreferences;
    int bread, money,rawmeat,cookedmeat,apple,lifesteal,select;
    TextView actiondesc,cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityinterface);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        actiondesc=(TextView)findViewById(R.id.actiondesc);
        cost=(TextView)findViewById(R.id.cost);

        unload();
    }

    public void unload(){
        bread=sharedPreferences.getInt("bread",0);
        money=sharedPreferences.getInt("money",0);
        rawmeat=sharedPreferences.getInt("rawmeat",0);
        cookedmeat=sharedPreferences.getInt("cookedmeat",0);
        apple=sharedPreferences.getInt("apple",0);
        lifesteal=sharedPreferences.getInt("lifesteal",0);
    }

    @Override
    public void onBackPressed() {

    }

    public void meatcooked(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.cookmeatbuy);
        button.setEnabled(false);

        select=1;
        actiondesc.setText("Eat this and regenerate 12HP");

    }

    public void cookmeat(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.rawtocook);
        button.setEnabled(false);

        select=2;
        actiondesc.setText("Turn raw meat into cooked meat");

    }

    public void meatraw(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.rawmeatbuy);
        button.setEnabled(false);

        select=3;
        actiondesc.setText("Eat this and regenerate 5HP");

    }

    public void applebuy(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.applebuy);
        button.setEnabled(false);

        select=4;
        actiondesc.setText("Eat this and regenerate 7HP");

    }

    public void breadbuy(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.breadbuy);
        button.setEnabled(false);

        select=5;
        actiondesc.setText("Eat this and regenerate 6HP");

    }

    public void lifestealbuy(View x) {
        ImageButton button=(ImageButton)findViewById(R.id.lifestealbuy);
        button.setEnabled(false);

        select=6;
        actiondesc.setText("Recieve health everytime you hit an enemy");
        if (lifesteal==0){
            cost.setText("Cost: 600");
            actiondesc.setText("Recieve health everytime you hit an enemy");
        }else if(lifesteal>0&&lifesteal<5){
            cost.setText("Cost: 100");
            actiondesc.setText("Upgrade the amount of HP points gained from hitting an enemy");
        }

    }

    public void buy(View x) {

        switch (select){
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
            case 6:{

                break;}
        }
        update();
    }

    public void update(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("bread",bread);
        editor.putInt("rawmeat",rawmeat);
        editor.putInt("cookedmeat",cookedmeat);
        editor.putInt("money",money);
        editor.putInt("lifesteal",lifesteal);
        editor.putInt("apple",apple);
        editor.apply();
    }

    public void OK(View x) {
        Intent okay = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(okay);
        finish();
    }
}

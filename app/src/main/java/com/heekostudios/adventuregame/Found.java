package com.heekostudios.adventuregame;

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

    Integer HP, money, rawmeat, bread, apple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        unload();
        itemfind();
        updater();
    }

    @Override
    public void onBackPressed() {

    }

    public void unload() {
        money = sharedPreferences.getInt("money", 0);
        bread = sharedPreferences.getInt("bread", 0);
        apple = sharedPreferences.getInt("apple", 0);
        rawmeat = sharedPreferences.getInt("rawmeat", 0);
    }

    public void updater() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", money);
        editor.putInt("bread", bread);
        editor.putInt("apple", apple);
        editor.putInt("rawmeat", rawmeat);
        editor.commit();
    }


    public void itemfind() {

        TextView founditem = (TextView) findViewById(R.id.founditem);
        ImageView item = (ImageView) findViewById(R.id.item_found);

        TextView foundorlost = (TextView) findViewById(R.id.youfound);
        foundorlost.setText("You Found:");


        Random rander = new Random();

        int i = rander.nextInt(5);

        switch (i) {
            case 0: {
                item.setImageResource(R.drawable.rawmeat);

                rawmeat = rawmeat + 1;
                founditem.setText("Raw meat");
                break;
            }
            case 1: {
                item.setImageResource(R.drawable.appleimage);

                apple = apple + 1;
                founditem.setText("An apple");
                break;
            }
            case 2: {
                item.setImageResource(R.drawable.goldbag);

                int c = rander.nextInt(25) + 10;
                founditem.setText(c + " Gold");
                money = c + money;
                break;
            }
            case 3: {
                item.setImageResource(R.drawable.goldbag);

                int c = rander.nextInt(65) + 10;
                founditem.setText(c + " Gold");
                money = c + money;
                break;
            }
            case 4: {
                item.setImageResource(R.drawable.breadpicture);

                bread = bread + 1;
                founditem.setText("A loaf of bread");
                break;
            }
        }
    }


    public void OK(View X) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}

package com.heekostudios.explorerquest;

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

    Integer money, rawmeat, bread, apple;
    float difficulty;

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
        difficulty=sharedPreferences.getFloat("difficulty",1);
    }

    public void updater() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", money);
        editor.putInt("bread", bread);
        editor.putInt("apple", apple);
        editor.putInt("rawmeat", rawmeat);
        editor.apply();
    }


    public void itemfind() {

        TextView founditem = findViewById(R.id.founditem);
        ImageView item = findViewById(R.id.item_found);

        TextView foundorlost = findViewById(R.id.youfound);
        foundorlost.setText(R.string.you_found);


        Random rander = new Random();

        int i = rander.nextInt(5);

        switch (i) {
            case 0: {
                item.setImageResource(R.drawable.rawmeat);

                rawmeat = rawmeat + 1;
                founditem.setText(R.string.rawmeat);
                break;
            }
            case 1: {
                item.setImageResource(R.drawable.appleimage);

                apple = apple + 1;
                founditem.setText(R.string.apple);
                break;
            }
            case 2: {
                item.setImageResource(R.drawable.goldbag);

                int c = rander.nextInt(25) + Math.round(5*difficulty);
                founditem.setText(c +" "+getString(R.string.gold));
                money = c + money;
                break;
            }
            case 3: {
                item.setImageResource(R.drawable.goldbag);

                int c = rander.nextInt(65) + Math.round(5*difficulty);
                founditem.setText(c +getString(R.string.gold));
                money = c + money;
                break;
            }
            case 4: {
                item.setImageResource(R.drawable.breadpicture);

                bread = bread + 1;
                founditem.setText(R.string.bread);
                break;
            }
        }
    }


    public void OK(View X) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}

package com.heekostudios.explorerquest;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;

public class Bag extends AppCompatActivity {

    String user;
    Integer rawmeat, bread, apple, setter, HP, maxhp, cookedmeat,experience;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        gif();
        unload();
        present();

    }


    public void present() {
        TextView health = findViewById(R.id.HP);
        health.setText(HP + " / " + maxhp*Constants.HP_INCREASE);

        TextView username = findViewById(R.id.Backpack);
        username.setText(String.format("%s%s", user, getString(R.string.player_backpack)));


        ImageButton box1 = findViewById(R.id.box1);
        ImageButton box2 = findViewById(R.id.box2);
        ImageButton box3 = findViewById(R.id.box3);
        ImageButton box4 = findViewById(R.id.box4);

        TextView quantity1 = findViewById(R.id.quantity1);
        TextView quantity2 = findViewById(R.id.quantity2);
        TextView quantity3 = findViewById(R.id.quantity3);
        TextView quantity4 = findViewById(R.id.quantity4);

        if (bread > 0) {
            box1.setVisibility(View.VISIBLE);
            box1.setImageResource(R.drawable.breadpicture);

            quantity1.setVisibility(View.VISIBLE);
            quantity1.setText("" + bread);
        } else {
            box1.setVisibility(View.INVISIBLE);
            quantity1.setVisibility(View.INVISIBLE);
        }

        if (apple > 0) {
            box2.setVisibility(View.VISIBLE);
            box2.setImageResource(R.drawable.appleimage);

            quantity2.setVisibility(View.VISIBLE);
            quantity2.setText("" + apple);
        } else {
            box2.setVisibility(View.INVISIBLE);
            quantity2.setVisibility(View.INVISIBLE);
        }

        if (rawmeat > 0) {
            box3.setVisibility(View.VISIBLE);
            box3.setImageResource(R.drawable.rawmeat);

            quantity3.setVisibility(View.VISIBLE);
            quantity3.setText("" + rawmeat);
        } else {
            box3.setVisibility(View.INVISIBLE);
            quantity3.setVisibility(View.INVISIBLE);
        }

        if (cookedmeat > 0) {
            box4.setVisibility(View.VISIBLE);
            box4.setImageResource(R.drawable.cookedmeat);

            quantity4.setVisibility(View.VISIBLE);
            quantity4.setText("" + cookedmeat);
        } else {
            box4.setVisibility(View.INVISIBLE);
            quantity4.setVisibility(View.INVISIBLE);
        }
    }

    public void box1(View X) {
        ImageView confirm = findViewById(R.id.imageaction);
        Button confirmation = findViewById(R.id.buttonaction);
        TextView hprestore = findViewById(R.id.result);

        hprestore.setText(getString(R.string.restores)+6+getString(R.string.hp));
        hprestore.setVisibility(View.VISIBLE);
        confirmation.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.breadpicture);

        setter = 1;
    }

    public void box2(View X) {
        ImageView confirm = findViewById(R.id.imageaction);
        Button confirmation = findViewById(R.id.buttonaction);
        TextView hprestore = findViewById(R.id.result);

        hprestore.setText(getString(R.string.restores)+7 +getString(R.string.hp));
        hprestore.setVisibility(View.VISIBLE);
        confirmation.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.appleimage);

        setter = 2;
    }

    public void box3(View X) {
        Button confirmation = findViewById(R.id.buttonaction);
        confirmation.setVisibility(View.VISIBLE);
        TextView hprestore = findViewById(R.id.result);

        hprestore.setText(getString(R.string.restores)+5+ getString(R.string.hp));
        hprestore.setVisibility(View.VISIBLE);
        ImageView confirm = findViewById(R.id.imageaction);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.rawmeat);

        setter = 3;
    }

    public void box4(View x) {

        Button confirmation = findViewById(R.id.buttonaction);
        confirmation.setVisibility(View.VISIBLE);
        TextView hprestore = findViewById(R.id.result);

        hprestore.setText(getString(R.string.restores)+ 12+ getString(R.string.hp));
        hprestore.setVisibility(View.VISIBLE);
        ImageView confirm = findViewById(R.id.imageaction);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.cookedmeat);

        setter = 4;
    }

    public void action(View X) {

        Random rander=new Random();

        experience+=rander.nextInt(3)+4;

        if (HP < (maxhp*Constants.HP_INCREASE)) {

            switch (setter) {
                case 1: {
                    HP += 6;
                    bread -= 1;

                    break;
                }
                case 2: {
                    HP += 7;
                    apple -= 1;
                    break;
                }
                case 3: {
                    HP += 5;
                    rawmeat -= 1;
                    break;
                }
                case 4: {
                    HP += 12;
                    cookedmeat -= 1;
                    break;
                }
            }


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Bag.this);

            builder.setCancelable(true);
            builder.setTitle("Max HP");
            builder.setMessage("You can not eat anymore your health is full");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }


        if (HP > (maxhp*Constants.HP_INCREASE)) {
            HP = maxhp*Constants.HP_INCREASE;
        }

        TextView hprestore = findViewById(R.id.result);
        hprestore.setVisibility(View.INVISIBLE);

        Button confirmation = findViewById(R.id.buttonaction);
        confirmation.setVisibility(View.INVISIBLE);

        ImageView confirm = findViewById(R.id.imageaction);
        confirm.setVisibility(View.INVISIBLE);

        update();

        present();
    }

    public void unload() {
        user = sharedPreferences.getString("user", null);
        bread = sharedPreferences.getInt("bread", 0);
        rawmeat = sharedPreferences.getInt("rawmeat", 0);
        apple = sharedPreferences.getInt("apple", 0);
        HP = sharedPreferences.getInt("HP", 50);
        maxhp = sharedPreferences.getInt("maxhp", 1);
        cookedmeat = sharedPreferences.getInt("cookedmeat", 0);
        experience=sharedPreferences.getInt("experience",0);
    }

    @Override
    public void onBackPressed() {
        finish();
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

    public void update() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HP", HP);
        editor.putInt("rawmeat", rawmeat);
        editor.putInt("bread", bread);
        editor.putInt("apple", apple);
        editor.putInt("cookedmeat", cookedmeat);
        editor.putInt("experience",experience);
        editor.apply();
    }

    public void back(View x) {
        finish();
    }
}

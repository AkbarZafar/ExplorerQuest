package com.exploregame.game1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Ditch extends AppCompatActivity {

    Integer HP;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditch);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        unload();
        fall();
        updater();
    }

    public void unload(){
        HP=sharedPreferences.getInt("HP",100);
    }

    public void fall(){
        TextView fell = (TextView) findViewById(R.id.youfell);
        TextView dmglost = (TextView) findViewById(R.id.lost);

        fell.setText("You fell in a ditch");

        Random random = new Random();

        int fall = random.nextInt(20) + 1;
        dmglost.setText("You lost " + fall + " HP");
        HP -= fall;
    }

    public void ok(View x) {

        if (HP <= 0) {
            Intent death = new Intent(getApplicationContext(), Death.class);
            startActivity(death);
        } else {
            Intent home = new Intent(getApplicationContext(), Gamescreen.class);
            startActivity(home);
        }
        finish();
    }

    public void updater() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HP", HP);
        editor.apply();
    }
}

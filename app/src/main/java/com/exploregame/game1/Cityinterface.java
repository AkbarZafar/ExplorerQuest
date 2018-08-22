package com.exploregame.game1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Cityinterface extends AppCompatActivity {

    TextView desc;
    SharedPreferences sharedPreferences;
    int bread, money,rawmeat,cookedmeat,apple;
    boolean lifesteal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityinterface);
    }

    @Override
    public void onBackPressed() {

    }

    public void meatcooked(View x) {
    }

    public void cookmeat(View x) {
    }

    public void meatraw(View x) {
    }

    public void applebuy(View x) {
    }

    public void breadbuy(View x) {
    }

    public void lifestealbuy(View x) {
    }

    public void buy(View x) {
    }

    public void OK(View x) {
        Intent okay = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(okay);
        finish();
    }
}

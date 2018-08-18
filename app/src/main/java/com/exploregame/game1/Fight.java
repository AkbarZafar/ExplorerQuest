package com.exploregame.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Fight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
    }

    public void bag(View x){
        Intent bag=new Intent(getApplicationContext(),Bag.class);
        startActivity(bag);
    }
}

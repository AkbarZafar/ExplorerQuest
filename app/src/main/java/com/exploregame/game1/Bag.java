package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Bag extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        try{
            GifDrawable warrior=new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold=findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        }catch (IOException e){
            e.printStackTrace();
        }

        user=getIntent().getStringExtra("user");

        TextView username=(TextView)findViewById(R.id.Backpack);
        username.setText(user+"'s Backpack");
    }

    public void back(View x){

        finish();
    }
}

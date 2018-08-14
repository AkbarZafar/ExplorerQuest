package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;


public class Gamescreen extends AppCompatActivity {
String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        try{
            GifDrawable warrior=new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold=findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        }catch (IOException e){
            e.printStackTrace();
        }

        user= getIntent().getStringExtra("value");
        Integer money=getIntent().getIntExtra("mula",0);
        Integer HP=getIntent().getIntExtra("HP",0);


        TextView username=(TextView)findViewById(R.id.name);
        username.setText(user);


        TextView mula=(TextView)findViewById(R.id.money);
        mula.setText(""+money);


        TextView hp=(TextView)findViewById(R.id.HP);
        hp.setText(""+HP);

    }

    public void backpack(View x){
        Intent bag=new Intent(getApplicationContext(), Bag.class);
        bag.putExtra("user", user);
        startActivity(bag);
    }
     public void explore(View x){

     }
}

package com.heekostudios.adventuregame;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String user;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        user=sharedPreferences.getString("user",null);

            if (user!=null){
                sendinfo();
            }
        }

    public void begin(View x){
        EditText username= findViewById(R.id.editText);

        user = username.getText().toString();

            if (TextUtils.isEmpty(user)){
                username.setText("");
                Toast.makeText(MainActivity.this,"Invalid Username!",Toast.LENGTH_LONG).show();

            }else{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user",user);
                editor.apply();

                sendinfo();
            }
    }
    public void sendinfo(){
        Intent send = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(send);
        finish();
    }
}

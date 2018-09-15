package com.heekostudios.adventuregame;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

        String username;
        EditText input;
        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getString("username",null);
    }

    public void changename(View x){

        AlertDialog.Builder namer=new AlertDialog.Builder(Settings.this);

        namer.setCancelable(true);
        namer.setTitle("Change Username");
        namer.setMessage("Enter your new username");

        input=new EditText(this);
        namer.setView(input);

        namer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        namer.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                username=input.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("user",username);
                editor.apply();
                dialogInterface.cancel();

                Toast.makeText(Settings.this,"Username succesfully changed.",Toast.LENGTH_SHORT).show();
            }
        });
        namer.show();
    }

    public void reset(View x){
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);

        builder.setCancelable(false);
        builder.setTitle("Reset Confirmation");
        builder.setMessage("Are you sure you want to reset the game? (This will delete all progress)");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){

                Intent reset=new Intent(getApplicationContext(),MainActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(reset);
                finish();
            }
        });
        builder.show();
    }

    public void cross(View y){
        Intent cross=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(cross);
        finish();
    }
}

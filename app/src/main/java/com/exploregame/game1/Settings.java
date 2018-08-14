package com.exploregame.game1;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;







public class Settings extends AppCompatActivity {

    public Button confirm;

        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        confirm = (Button) findViewById(R.id.resetgame);
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
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
        });

    }

    public void cross(View y){
        Intent cross=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(cross);
        finish();
    }
}

package com.exploregame.game1;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;

public class Bag extends AppCompatActivity {

    String user;
    Integer rawmeat, bread, apple, experience, setter, HP,maxhp;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        gif();
        unload();
        present();

    }


    public void present(){
        TextView health=(TextView)findViewById(R.id.HP);
        health.setText(HP+"/"+maxhp);

        TextView username=(TextView)findViewById(R.id.Backpack);
        username.setText(user+"'s Backpack");


        ImageButton box1=(ImageButton)findViewById(R.id.box1);
        ImageButton box2=(ImageButton)findViewById(R.id.box2);
        ImageButton box3=(ImageButton)findViewById(R.id.box3);

        TextView quantity1=(TextView)findViewById(R.id.quantity1);
        TextView quantity2=(TextView)findViewById(R.id.quantity2);
        TextView quantity3=(TextView)findViewById(R.id.quantity3);

        if (bread>0){
            box1.setVisibility(View.VISIBLE);
            box1.setImageResource(R.drawable.breadpicture);

            quantity1.setVisibility(View.VISIBLE);
            quantity1.setText(""+bread);
        }else{
            box1.setVisibility(View.GONE);
            quantity1.setVisibility(View.GONE);
        }

        if (apple>0){
            box2.setVisibility(View.VISIBLE);
            box2.setImageResource(R.drawable.appleimage);

            quantity2.setVisibility(View.VISIBLE);
            quantity2.setText(""+apple);
        }else{
            box2.setVisibility(View.GONE);
            quantity2.setVisibility(View.GONE);
        }

        if(rawmeat>0){
            box3.setVisibility(View.VISIBLE);
            box3.setImageResource(R.drawable.rawmeat);

            quantity3.setVisibility(View.VISIBLE);
            quantity3.setText(""+rawmeat);
        }else{
            box3.setVisibility(View.GONE);
            quantity3.setVisibility(View.GONE);
        }
    }

    public void box1(View X){
        ImageView confirm=(ImageView)findViewById(R.id.imageaction);
        Button confirmation=(Button)findViewById(R.id.buttonaction);
        TextView hprestore=(TextView)findViewById(R.id.result);

        hprestore.setText("Restores 6HP");
        hprestore.setVisibility(View.VISIBLE);
        confirmation.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.breadpicture);

        setter=1;
        }

    public void box2(View X){
        ImageView confirm=(ImageView)findViewById(R.id.imageaction);
        Button confirmation=(Button)findViewById(R.id.buttonaction);
        TextView hprestore=(TextView)findViewById(R.id.result);

        hprestore.setText("Restores 7HP");
        hprestore.setVisibility(View.VISIBLE);
        confirmation.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.appleimage);

        setter=2;
    }

    public void box3(View X){
        Button confirmation=(Button)findViewById(R.id.buttonaction);
        confirmation.setVisibility(View.VISIBLE);
        TextView hprestore=(TextView)findViewById(R.id.result);

        hprestore.setText("Restores 5HP");
        hprestore.setVisibility(View.VISIBLE);
        ImageView confirm=(ImageView)findViewById(R.id.imageaction);
        confirm.setVisibility(View.VISIBLE);
        confirm.setImageResource(R.drawable.rawmeat);

        setter=3;
    }

    public void action(View X){

        Random rander=new Random();
        int expgain=rander.nextInt(7)+5;

        if(HP<maxhp){

        switch (setter){
            case 1:{
                HP=HP+6;
                experience=experience+expgain;
                bread=bread-1;
            break;}
            case 2:{
                HP=HP+7;
                experience=experience+expgain;
                apple=apple-1;
                break;}
            case 3:{
                HP=HP+5;
                experience=experience+expgain;
                rawmeat=rawmeat-1;
                break;}
        }
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Bag.this);

            builder.setCancelable(true);
            builder.setTitle("Max HP");
            builder.setMessage("You can not eat anymore your health is full");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){

                    }
            });
            builder.show();
        }


        if (HP>maxhp){
            HP=maxhp;
        }

        TextView hprestore=(TextView)findViewById(R.id.result);
        hprestore.setVisibility(View.INVISIBLE);

        Button confirmation=(Button)findViewById(R.id.buttonaction);
        confirmation.setVisibility(View.GONE);

        ImageView confirm=(ImageView)findViewById(R.id.imageaction);
        confirm.setVisibility(View.GONE);

        present();
    }

    public void unload(){
        user=sharedPreferences.getString("user",null);
        bread=sharedPreferences.getInt("bread",0);
        rawmeat=sharedPreferences.getInt("rawmeat",0);
        apple=sharedPreferences.getInt("apple",0);
        experience=sharedPreferences.getInt("experience",0);
        HP=sharedPreferences.getInt("HP",100);
        maxhp=sharedPreferences.getInt("maxhp",100);
    }

    public void gif(){
        try{
            GifDrawable warrior=new GifDrawable(getResources(), R.drawable.warrioridle);
            ImageView gifhold=findViewById(R.id.idle);
            gifhold.setImageDrawable(warrior);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HP",HP);
        editor.putInt("rawmeat",rawmeat);
        editor.putInt("bread",bread);
        editor.putInt("apple",apple);
        editor.putInt("experience",experience);
        editor.commit();
    }

    public void back(View x){
        update();

        finish();
    }
}

package com.exploregame.game1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selectperk extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Integer attack, defence, maxhp, setter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectperk);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        unpack();
        setup();
    }

    public void unpack(){
        sharedPreferences.getInt("attack",1);//attack level timesed by 6,, 6(current),12,18,24,32,40
        sharedPreferences.getInt("defence",1);//defence timesed by 1.5%,, 1.5%(current),3%,4.5%,6%,7.5%,10%
        sharedPreferences.getInt("maxhp",1);//hp boosted by 15 every time,, 100(current),115,130,145,160,200
    }

    public void setup(){
        Button confirm=(Button)findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        Button exit=(Button)findViewById(R.id.exit);
        exit.setVisibility(View.GONE);

        Button maxhealth=(Button)findViewById(R.id.perkhp);
        if (maxhp==200){
            maxhealth.setEnabled(false);
            maxhealth.setText("You've reached the max HP limit!");
        }else{
            maxhealth.setText("Upgrade max HP to: "+(maxhp+15));
        }

        Button def=(Button)findViewById(R.id.perkdef);
        if(defence==5){
            def.setText("You've reached max defence!");
            def.setEnabled(false);
        }else {
            def.setText("Upgrade to defence level: " + (defence + 1));
        }

        Button attk=(Button)findViewById(R.id.perkattck);
        if (attack==5){
            attk.setText("You've reached max attack!");
            attk.setEnabled(false);
        }else{
            attk.setText("Upgrade to attack level: "+(attack+1));
            }
    }

    public void atklvl(View x){
        Button confirm=(Button)findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        setter=1;
    }

    public void deflvl(View x){
        Button confirm=(Button)findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        setter=2;
    }

    public void hplvl(View x){
        Button confirm=(Button)findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        setter=3;
    }

    public void confirm(View x){
        switch(setter){
            case 1:{
                attack=attack+1;
            }
            case 2:{
                defence=defence+1;
            }
            case 3:{
                maxhp=maxhp+15;
                if (maxhp==175){
                    maxhp=200;
                }
            }
        }

        upload();

        Button confirm=(Button)findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        Button exit=(Button)findViewById(R.id.exit);
        exit.setVisibility(View.VISIBLE);
    }

    public void upload(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("attack",attack);
        editor.putInt("defence",defence);
        editor.putInt("maxhp",maxhp);
        editor.commit();
    }

    public void exit(View x){
        Intent exit=new Intent(getApplicationContext(),Gamescreen.class);
        startActivity(exit);
        finish();
    }
}

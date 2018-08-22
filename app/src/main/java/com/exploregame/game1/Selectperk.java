package com.exploregame.game1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Selectperk extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Integer attack, defence, maxhp, setter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectperk);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        unpack();
        setup();
    }

    public void unpack() {
        attack = sharedPreferences.getInt("attack", 1);//attack level timesed by 6,, 6(current),12,18,24,35
        defence = sharedPreferences.getInt("defence", 1);//defence timesed by 3%,, 3%(current),6%,9%,12%,18%
        maxhp = sharedPreferences.getInt("maxhp", 100);//hp boosted by 15 every time,, 100(current),115,130,145,180
    }

    public void setup() {
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        Button exit = (Button) findViewById(R.id.exit);
        exit.setVisibility(View.GONE);

        Button maxhealth = (Button) findViewById(R.id.perkhp);
        TextView textmaxhealth = (TextView) findViewById(R.id.currenthp);
        if (maxhp == 180) {
            textmaxhealth.setText("Current Max HP: 180HP");
            maxhealth.setEnabled(false);
            maxhealth.setText("You've reached the max HP limit!");
        }else if (attack==4) {
            textmaxhealth.setText("Current Max HP: " + maxhp);
            maxhealth.setText("Upgrade to Max HP to 180");
        } else {
            textmaxhealth.setText("Current Max HP: " + maxhp);
            maxhealth.setText("Upgrade max HP to: " + (maxhp + 15));
        }

        Button def = (Button) findViewById(R.id.perkdef);
        TextView textdef = (TextView) findViewById(R.id.currentdef);
        if (defence == 5) {
            textdef.setText("Current Defence: 18% resistence");
            def.setText("You've reached max defence!");
            def.setEnabled(false);
        }else if (attack==4) {
                textdef.setText("Current Defence: " + (defence * 3) + "% resistence");
                def.setText("Upgrade to attack to 18% resistence");
            } else {
            textdef.setText("Current Defence: " + (defence * 3) + "% resistence");
            def.setText("Upgrade to defence to " + ((defence + 1) * 3) + "% resistence");

        }

        Button attk = (Button) findViewById(R.id.perkattck);
        TextView textattk = (TextView) findViewById(R.id.currentattk);
        if (attack == 5) {
            textdef.setText("Current Attack: 40 dmg");
            attk.setText("You've reached max attack!");
            attk.setEnabled(false);
        } else if (attack==4) {
            textattk.setText("Current Attack: " + (attack * 6) + " dmg");
            attk.setText("Upgrade to attack to 40");
        } else {
            textattk.setText("Current Attack: " + (attack * 6) + " dmg");
            attk.setText("Upgrade to attack to " + ((attack + 1) * 6));
        }
    }

    public void atklvl(View x) {
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck=(Button)findViewById(R.id.perkattck);
        attck.setEnabled(false);

        Button def=(Button)findViewById(R.id.perkdef);
        def.setEnabled(true);

        Button health=(Button)findViewById(R.id.perkhp);
        health.setEnabled(true);

        setter = 1;
    }

    public void deflvl(View x) {
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck=(Button)findViewById(R.id.perkattck);
        attck.setEnabled(true);

        Button def=(Button)findViewById(R.id.perkdef);
        def.setEnabled(false);

        Button health=(Button)findViewById(R.id.perkhp);
        health.setEnabled(true);

        setter = 2;
    }

    public void hplvl(View x) {
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck=(Button)findViewById(R.id.perkattck);
        attck.setEnabled(true);

        Button def=(Button)findViewById(R.id.perkdef);
        def.setEnabled(true);

        Button health=(Button)findViewById(R.id.perkhp);
        health.setEnabled(false);

        setter = 3;
    }

    @Override
    public void onBackPressed(){

    }

    public void confirm(View x) {
        switch (setter) {
            case 1: {
                attack = attack + 1;
            break;}
            case 2: {
                defence = defence + 1;
            break;}
            case 3: {
                maxhp = maxhp + 15;
                if (maxhp == 145) {
                    maxhp = 180;
                break;}
            }
        }

        upload();
        setup();
        exitsetup();
    }

    public void exitsetup() {
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        Button perkattack = (Button) findViewById(R.id.perkattck);
        perkattack.setEnabled(false);

        Button health = (Button) findViewById(R.id.perkhp);
        health.setEnabled(false);

        Button perkdefence = (Button) findViewById(R.id.perkdef);
        perkdefence.setEnabled(false);

        Button exit = (Button) findViewById(R.id.exit);
        exit.setVisibility(View.VISIBLE);
    }

    public void upload() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("attack", attack);
        editor.putInt("defence", defence);
        editor.putInt("maxhp", maxhp);
        editor.apply();
    }

    public void exit(View x) {
        Intent exit = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(exit);
        finish();
    }
}

package com.heekostudios.explorerquest;

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
        attack = sharedPreferences.getInt("attack", 1);//attack level timesed by 6,, 6(current),12,18,24,30
        defence = sharedPreferences.getInt("defence", 1);//defence timesed by 7%,, 7%(current),14%,21%,28%,35%
        maxhp = sharedPreferences.getInt("maxhp", 1);//hp boosted by 15 every time,, 100(current),115,130,145,160
    }

    public void setup() {
        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.INVISIBLE);

        Button exit = findViewById(R.id.exit);
        exit.setVisibility(View.GONE);

        Button maxhealth = findViewById(R.id.perkhp);
        TextView textmaxhealth = findViewById(R.id.currenthp);
        if (maxhp == 5) {
            textmaxhealth.setText(R.string.current_max_hp +maxhp*Constants.HP_INCREASE);
            maxhealth.setEnabled(false);
            maxhealth.setText(R.string.max_hp_limit);
        } else {
            textmaxhealth.setText(R.string.current_max_hp +maxhp*Constants.HP_INCREASE);
            maxhealth.setText(R.string.upgrade_hp_to + (maxhp + Constants.HP_INCREASE));
        }

        Button def = findViewById(R.id.perkdef);
        TextView textdef = findViewById(R.id.currentdef);
        if (defence == 5) {
            textdef.setText(R.string.current_defence +defence*Constants.DEFENCE_INCREASE+R.string.percent_resistance);
            def.setText(R.string.max_defence_reach);
            def.setEnabled(false);
        } else {
            textdef.setText(R.string.current_defence + (defence * Constants.DEFENCE_INCREASE) + R.string.percent_resistance);
            def.setText(R.string.upgrade_defence_to + ((defence + 1) * Constants.DEFENCE_INCREASE) + R.string.percent_resistance);
        }

        Button attk = findViewById(R.id.perkattck);
        TextView textattk = findViewById(R.id.currentattk);
        if (attack == 5) {
            textdef.setText(R.string.current_attack+attack*Constants.ATTACK_INCREASE+R.string.damage);
            attk.setText(R.string.max_damage_reached);
            attk.setEnabled(false);
        } else {
            textattk.setText(R.string.current_attack + (attack * Constants.ATTACK_INCREASE) + R.string.damage);
            attk.setText(R.string.upgrade_attack_to + ((attack + 1) * Constants.ATTACK_INCREASE));
        }
    }

    public void atklvl(View x) {
        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck= findViewById(R.id.perkattck);
        attck.setEnabled(false);

        Button def= findViewById(R.id.perkdef);
        def.setEnabled(true);

        Button health= findViewById(R.id.perkhp);
        health.setEnabled(true);

        setter = 1;
    }

    public void deflvl(View x) {
        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck= findViewById(R.id.perkattck);
        attck.setEnabled(true);

        Button def= findViewById(R.id.perkdef);
        def.setEnabled(false);

        Button health= findViewById(R.id.perkhp);
        health.setEnabled(true);

        setter = 2;
    }

    public void hplvl(View x) {
        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

        Button attck= findViewById(R.id.perkattck);
        attck.setEnabled(true);

        Button def= findViewById(R.id.perkdef);
        def.setEnabled(true);

        Button health= findViewById(R.id.perkhp);
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
                maxhp = maxhp + 1;
                break;
            }
        }

        upload();
        setup();
        exitsetup();
    }

    public void exitsetup() {
        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        Button perkattack = findViewById(R.id.perkattck);
        perkattack.setEnabled(false);

        Button health = findViewById(R.id.perkhp);
        health.setEnabled(false);

        Button perkdefence = findViewById(R.id.perkdef);
        perkdefence.setEnabled(false);

        Button exit = findViewById(R.id.exit);
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

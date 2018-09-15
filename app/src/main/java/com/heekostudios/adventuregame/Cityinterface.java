package com.heekostudios.adventuregame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Cityinterface extends AppCompatActivity {

    TextView desc;
    SharedPreferences sharedPreferences;
    int bread, money, rawmeat, cookedmeat, apple, lifesteal, select;
    TextView actiondesc, cost;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityinterface);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        actiondesc = findViewById(R.id.actiondesc);
        cost = findViewById(R.id.cost);

        unload();
        setup();
    }

    public void setup() {
        TextView moneycount = findViewById(R.id.moneycount);
        moneycount.setText("" + money);

        buy =findViewById(R.id.btnaction);
        buy.setVisibility(View.INVISIBLE);

        actiondesc.setText("");

        cost.setText("");

        findViewById(R.id.cookmeatbuy).setEnabled(true);
        findViewById(R.id.applebuy).setEnabled(true);
        findViewById(R.id.rawtocook).setEnabled(true);
        findViewById(R.id.rawmeatbuy).setEnabled(true);
        findViewById(R.id.breadbuy).setEnabled(true);
        findViewById(R.id.lifestealbuy).setEnabled(true);
    }

    public void unload() {
        bread = sharedPreferences.getInt("bread", 0);
        money = sharedPreferences.getInt("money", 0);
        rawmeat = sharedPreferences.getInt("rawmeat", 0);
        cookedmeat = sharedPreferences.getInt("cookedmeat", 0);
        apple = sharedPreferences.getInt("apple", 0);
        lifesteal = sharedPreferences.getInt("lifesteal", 0);
    }

    @Override
    public void onBackPressed() {

    }

    public void meatcooked(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(false);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(true);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(true);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(true);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(true);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(true);

        buy.setVisibility(View.VISIBLE);

        select = 1;
        actiondesc.setText("Eat this and regenerate 12HP");
        cost.setText("Cost: 20 gold");
    }

    public void cookmeat(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(true);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(true);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(true);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(true);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(false);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(true);

        buy.setVisibility(View.VISIBLE);

        select = 2;
        actiondesc.setText("Turn raw meat into cooked meat");
        cost.setText("Cost: 1 rawmeat and 8 gold");
    }

    public void meatraw(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(true);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(true);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(true);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(false);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(true);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(true);

        buy.setVisibility(View.VISIBLE);

        select = 3;
        actiondesc.setText("Eat this and regenerate 5HP");
        cost.setText("Cost: 10 gold");
    }

    public void applebuy(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(true);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(false);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(true);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(true);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(true);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(true);

        buy.setVisibility(View.VISIBLE);

        select = 4;
        actiondesc.setText("Eat this and regenerate 7HP");
        cost.setText("Cost: 13 gold");
    }

    public void breadbuy(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(true);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(true);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(false);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(true);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(true);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(true);

        buy.setVisibility(View.VISIBLE);

        select = 5;
        actiondesc.setText("Eat this and regenerate 6HP");
        cost.setText("Cost: 11 gold");

    }

    public void lifestealbuy(View x) {
        ImageButton button = findViewById(R.id.cookmeatbuy);
        button.setEnabled(true);
        ImageButton button1= findViewById(R.id.applebuy);
        button1.setEnabled(true);
        ImageButton button2= findViewById(R.id.breadbuy);
        button2.setEnabled(true);
        ImageButton button3= findViewById(R.id.rawmeatbuy);
        button3.setEnabled(true);
        ImageButton button4= findViewById(R.id.rawtocook);
        button4.setEnabled(true);
        ImageButton button5= findViewById(R.id.lifestealbuy);
        button5.setEnabled(false);


        actiondesc.setText("Recieve health everytime you hit an enemy");
        if (lifesteal == 0) {
            cost.setText("Cost: 220 gold");
            actiondesc.setText("Recieve health everytime you hit an enemy");
            select = 6;
            buy.setVisibility(View.VISIBLE);
            button.setEnabled(false);
        } else if (lifesteal > 0 && lifesteal < 5) {
            cost.setText("Cost: 75 gold");
            actiondesc.setText("Upgrade the amount of HP points gained from hitting an enemy");
            select = 6;
            button.setEnabled(false);
            buy.setVisibility(View.VISIBLE);
        }else if (lifesteal==5){

            AlertDialog.Builder builder = new AlertDialog.Builder(Cityinterface.this);

            builder.setCancelable(true);
            builder.setTitle("Maxed Out Item!");
            builder.setMessage("You can not upgrade this item any higher! It is at max level.");

            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }

    }

    public void buy(View x) {

        switch (select) {
            case 1: {
                if (money >= 20) {
                    cookedmeat += 1;
                    money -= 20;
                } else {
                    notenoughmoney();
                }
                break;
            }
            case 2: {
                if (money >= 8&&rawmeat>=1) {
                    cookedmeat += 1;
                    rawmeat-=1;
                    money -= 8;
                } else if (money<8){
                    notenoughmoney();
                }else if(rawmeat<1){
                    notenoughrawmeat();
                }
                break;
            }
            case 3: {
                if (money >= 10) {
                    rawmeat += 1;
                    money -= 10;
                } else {
                    notenoughmoney();
                }
                break;
            }
            case 4: {
                if (money >= 13) {
                    apple += 1;
                    money -= 13;
                } else {
                    notenoughmoney();
                }
                break;
            }
            case 5: {
                if (money >= 11) {
                    bread += 1;
                    money -= 11;
                } else {
                    notenoughmoney();
                }
                break;
            }
            case 6: {
                if (lifesteal==0){
                    if (money >= 220) {
                        lifesteal += 1;
                        money -= 220;
                    } else {
                        notenoughmoney();
                    }
                }else{
                    if (money >= 75) {
                        lifesteal += 1;
                        money -= 75;
                    } else {
                        notenoughmoney();
                    }
                }
                break;
            }
        }
        update();
        setup();
    }

    public void notenoughmoney() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Cityinterface.this);

        builder.setCancelable(true);
        builder.setTitle("Not Enough Money!");
        builder.setMessage("You do not have enough money for this item");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void notenoughrawmeat(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Cityinterface.this);

        builder.setCancelable(true);
        builder.setTitle("Out of Raw Meat!");
        builder.setMessage("You do not have any raw meat to cook");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void update() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("bread", bread);
        editor.putInt("rawmeat", rawmeat);
        editor.putInt("cookedmeat", cookedmeat);
        editor.putInt("money", money);
        editor.putInt("lifesteal", lifesteal);
        editor.putInt("apple", apple);
        editor.apply();
    }

    public void OK(View x) {
        Intent okay = new Intent(getApplicationContext(), Gamescreen.class);
        startActivity(okay);
        finish();
    }

    public void bag(View x){
        Intent bag = new Intent(getApplicationContext(), Bag.class);
        startActivity(bag);
        backhere();
    }

    public void backhere(){
        ImageView character = findViewById(R.id.coin);
        TranslateAnimation setpostion = new TranslateAnimation(100, 0, 0, 0);


        setpostion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                unload();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        setpostion.setDuration(50);


        character.startAnimation(setpostion);
    }
}

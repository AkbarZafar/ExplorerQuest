package com.exploregame.game1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String user;
    int HP, money,level,experience;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        user=sharedPreferences.getString("user",null);

        HP=sharedPreferences.getInt("HP",100);
        money=sharedPreferences.getInt("money",0);
        level=sharedPreferences.getInt("level",1);
        experience=sharedPreferences.getInt("experience",0);
        if (user!=null){
            sendinfo(money,HP,level,experience);
        }
        }

    public void begin(View x){
            TextView incorrect=(TextView)findViewById(R.id.textView);
        EditText username=(EditText)findViewById(R.id.editText);
            user = username.getText().toString();
            if (TextUtils.isEmpty(user)){
                username.setText("");
                incorrect.setText("Invalid username.");

            }else{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user",user);
                editor.commit();

                sendinfo(money,HP,level,experience);
            }
    }
    public void sendinfo(int money,int HP,int level, int experience){
        Intent send = new Intent(getApplicationContext(), Gamescreen.class);
        send.putExtra("user",user);
        send.putExtra("HP",HP);
        send.putExtra("money",money);
        send.putExtra("level",level);
        send.putExtra("experience",experience);
        startActivity(send);
        finish();
    }
}

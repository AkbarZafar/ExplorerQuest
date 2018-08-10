package com.exploregame.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


public class gamescreen extends AppCompatActivity {
String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

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
        Intent bag=new Intent(getApplicationContext(), bag.class);
        bag.putExtra("user", user);
        startActivity(bag);
    }
}

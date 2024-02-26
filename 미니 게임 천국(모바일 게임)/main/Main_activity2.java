package com.example.ty_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main_activity2 extends AppCompatActivity {

    Button wordPractice, findSame, block, roulette, rockScissorPaper;
    TextView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockScissorPaper = findViewById(R.id.rockScissorPaper);
        wordPractice = findViewById(R.id.wordPractice);
        findSame = findViewById(R.id.findSame);
        block = findViewById(R.id.block);
        roulette = findViewById(R.id.roulette);

        Intent intent = getIntent(); //전달할 데이터를 받을 Intent
        String userId = intent.getStringExtra("userId");
        String userCharacter = intent.getStringExtra("userCharacter");

        TextView player = findViewById(R.id.player);
        player.setText(userId + " | " + userCharacter);

        findSame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Main_activity2.this, FindSame_activity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userCharacter", userCharacter);
                startActivity(intent);
            }
        });

//        rockScissorPaper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent _intent = new Intent(Main_activity2.this, RockScissorPaper_activity.class);
//                startActivity(_intent);
//            }
//        });


//        wordPractice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent _intent = new Intent(Main_activity.this, Eng_activity.class);
//                startActivity(_intent);
//            }
//        });

        roulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent(Main_activity2.this, Character_viewpager.class);
                startActivity(_intent);
            }
        });

    }

}
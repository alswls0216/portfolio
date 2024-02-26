package com.example.ty_project;
//FindSame_activity 전체

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;


public class FindSame_activity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton[] buttons = new ImageButton[8];
    private ArrayList<Integer> imageList;
    private ArrayList<Character_roulette_model> cards;
    private TextView resultText, heart1,  heart2, heart3, heart4, heart5, timer, resetBtn;
    private TextView score;
    private Button volume, back, exit, reset;
    private String  result_time;
    private int preCardPosition = -1;  private int sound1, sound2, sound3;
    private long running = -1; private long heart_status = 1;
    private Thread timeThread = null;

    private SoundPool soundPool;
    public static MediaPlayer findMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findsame);

        findMediaPlayer= MediaPlayer.create(this,R.raw.sound_background2);
        findMediaPlayer.start();



        resultText = findViewById(R.id.result_text);
        volume = findViewById(R.id.volume);
        back = findViewById(R.id.back);
        timer = findViewById(R.id.timer);

        //하트..
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        heart4 = findViewById(R.id.heart4);
        heart5 = findViewById(R.id.heart5);

        //사용자 데이터
//        Intent intent = getIntent();
//        userId = intent.getStringExtra("userId");
//        userCharacter = intent.getStringExtra("userCharacter");

        init();  //게임 실행

        //리셋 버튼
        Button resetBtn = findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( FindSame_activity.this, MainActivity.class);
                startActivity(_intent);
            }
        });

        /* 볼륨
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( FindSame_activity.this, Main_activity.class);
                startActivity(_intent);
            }
        });
        */

        // 카드 효과음
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(8)
                .setAudioAttributes(audioAttributes)
                .build();

        sound1 = soundPool.load(this, R.raw.sound_findsame, 1);
        sound2 = soundPool.load(this, R.raw.sound_findsame_fail, 1);
        sound3 = soundPool.load(this, R.raw.sound_findsame_result, 1);
    }

    public void init(){
        //게임 설정 초기화
        if(running != -1){
            running = -1;
            heart_status = 1;

            heart1.setVisibility(View.VISIBLE);
            heart2.setVisibility(View.VISIBLE);
            heart3.setVisibility(View.VISIBLE);
            heart4.setVisibility(View.VISIBLE);
            heart5.setVisibility(View.VISIBLE);
        }

        //카드 리스트 추가
        imageList = new ArrayList<>();
        imageList.add(R.drawable.r_lion1);
        imageList.add(R.drawable.r_pig1);
        imageList.add(R.drawable.r_zebra1);
        imageList.add(R.drawable.r_croc1);
        imageList.add(R.drawable.r_lion1);
        imageList.add(R.drawable.r_pig1);
        imageList.add(R.drawable.r_zebra1);
        imageList.add(R.drawable.r_croc1);

        //순서 섞어서 새로운 카드 리스트에 추가
        Collections.shuffle(imageList);
        cards = new ArrayList<>();

        //버튼 초기화
        for(int i=0; i<buttons.length; i++){
            String buttonID = "imageBtn" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);

            buttons[i].setOnClickListener(this);
            cards.add(new Character_roulette_model(imageList.get(i), false, false));
            buttons[i].setImageResource(R.drawable.question);
            buttons[i].setAlpha(1.0f);
        }
    }

    public void onClick(View view){

        if(running == -1){
            running = 1;
            timeThread = new Thread(new timeThread());
            timeThread.start();
        }

        playSound(); //효과음
        int id = view.getId();
        int position = 0;

        if(id == R.id.imageBtn0){
            position = 0;
        } else if (id == R.id.imageBtn1) {
            position = 1;
        } else if (id == R.id.imageBtn2) {
            position = 2;
        } else if (id == R.id.imageBtn3) {
            position = 3;
        } else if (id == R.id.imageBtn4) {
            position = 4;
        } else if (id == R.id.imageBtn5) {
            position = 5;
        } else if (id == R.id.imageBtn6) {
            position = 6;
        } else if (id == R.id.imageBtn7) {
            position = 7;
        }

        updateModel(position);
        updateView(position);
    }

    private void updateView(int position) {
        Character_roulette_model card = cards.get(position);

        if(card.isFaceUp()) {
            buttons[position].setImageResource(card.getImageId());
        } else {
            buttons[position].setImageResource(R.drawable.question);
        }
    }

    private void updateModel(int position) {
        Character_roulette_model card = cards.get(position);

        if(preCardPosition == -1){
            restoreCard();
            preCardPosition = position;
        } else {
            checkForMatch(preCardPosition, position);
            preCardPosition = -1;
        }
        cards.get(position).setFaceUp(!card.isFaceUp());
    }

    private void checkForMatch(int prePosition, int position) {

        if(cards.get(prePosition).getImageId() == cards.get(position).getImageId()){

            cards.get(prePosition).setMatched(true);
            cards.get(position).setMatched(true);

            buttons[prePosition].setAlpha(0.1f);
            buttons[position].setAlpha(0.1f);

            checkCompletion();
        }
        else if(cards.get(prePosition).getImageId() != cards.get(position).getImageId()) {
            if(heart_status == 1) {
                heart1.setVisibility(View.GONE);
                heart_status = 2;
            }
            else if(heart_status == 2) {
                heart2.setVisibility(View.GONE);
                heart_status = 3;
            }
            else if(heart_status == 3) {
                heart3.setVisibility(View.GONE);
                heart_status = 4;
            }
            else if(heart_status == 4) {
                heart4.setVisibility(View.GONE);
                heart_status = 5;
            }
            else if(heart_status == 5) {
                heart5.setVisibility(View.GONE);
                heart_status = 0;
                checkCompletion();
            }
        }
    }

    private void checkCompletion() {

        int count = 0;
        for(int i=0; i<cards.size(); i++){
            if(cards.get(i).isMatched()){
                count++;
            }
        }

        if(count == cards.size()) {
            timeThread.interrupt();
            showAlert_success();
        }
        else {
            if(heart_status == 0){
                timeThread.interrupt();  // 타이머 종료
                showAlert_fail();
            }
        }
    }

    private void restoreCard() {
        for(int i=0; i<cards.size(); i++){
            if(!cards.get(i).isMatched()){
                buttons[i].setImageResource(R.drawable.question);
                cards.get(i).setFaceUp(false);
            }
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d", min, sec, mSec);
            result_time = result;
            timer.setText(result);
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                while (running == 1) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }

    //성공하는 경우 alert
    public void showAlert_success() {
        playSound_result();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_findsame, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .show();

        score = view.findViewById(R.id.score);
        score.setText(result_time);

        exit = view.findViewById(R.id.exit);
        reset = view.findViewById(R.id.reset);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(FindSame_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                alertDialog.dismiss();
                init();
            }
        });
    }

    //실패하는 경우 alert
    public void showAlert_fail() {
        playSound_fail();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_findsame, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .show();

        score = view.findViewById(R.id.score);
        score.setText("Fail");

        exit = view.findViewById(R.id.exit);
        reset = view.findViewById(R.id.reset);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(FindSame_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                alertDialog.dismiss();
                init();
            }
        });
    }

    public void playSound(){
        soundPool.play(sound1, 1, 1, 0, 0, 1);
    }
    public void playSound_fail(){
        soundPool.play(sound2, 1, 1, 0, 0, 1);
    }
    public void playSound_result(){
        soundPool.play(sound3, 1, 1, 0, 0, 1);
    }
}

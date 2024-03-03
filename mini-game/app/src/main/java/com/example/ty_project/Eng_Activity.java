package com.example.ty_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import android.os.Message;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Button;

import java.util.HashMap;

import android.view.View;
import android.util.Log;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Handler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Eng_Activity extends AppCompatActivity {
    TextView questionTextView;
    Button example1Button;
    Button example2Button;
    Button example3Button;
    Button example4Button,back;
    //
    String TAG = "Eng_Activity";


    // timer 변수 선언
    private Thread timeThread = null;

    // result_time : 플레이 시간
    String result_time;
    private TextView resultText;
    private long running = -1;

    // 여기서 문제 개수 설정
    HashMap[] problems = new HashMap[13];
    int problemNumber = 1;
    String question = "";
    String answer = "";
    String example1 = "";
    String example2 = "";
    String example3 = "";
    String example4 = "";

    // totalCorrect : 맞춘 점수
    int totalCorrect = 0;
    TextView totalCorrectTextView;
    TextView correctIncorrectTextView;

   private SoundPool soundPool;
   private int sound,soundCorrect,soundIncorrect;

    public static MediaPlayer engMediaPlayer;

    TextView score;
    Button exit, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng);


        // 카드 효과음
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(8)
                .setAudioAttributes(audioAttributes)
                .build();

        sound = soundPool.load(this, R.raw.tab_click, 1);

        soundCorrect = soundPool.load(this, R.raw.sound_findsame_result, 1);

        soundIncorrect = soundPool.load(this, R.raw.sound_findsame_fail, 1);





        engMediaPlayer= MediaPlayer.create(this,R.raw.sound_eng);
        engMediaPlayer.start();

        back = findViewById(R.id.eng_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( Eng_Activity.this, MainActivity.class);
                startActivity(_intent);
            }
        });


        // timer
        resultText = findViewById(R.id.result_text_eng);

        questionTextView = findViewById(R.id.questionTextView);
        example1Button = findViewById(R.id.example1Button);
        example2Button = findViewById(R.id.example2Button);
        example3Button = findViewById(R.id.example3Button);
        example4Button = findViewById(R.id.example4Button);
        //


        totalCorrectTextView = findViewById(R.id.totalCorrectTextView);
        correctIncorrectTextView = findViewById(R.id.correctIncorrectTextView);

        try {
            InputStream inputStream = getAssets().open("001_001.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            String str = sb.toString();

            JSONObject jo = new JSONObject(str);
            JSONArray ja = jo.getJSONArray("problems");
            // 문제 랜덤으로 내기
            int a[] = new int[ja.length()];

            Random r = new Random();
            for (int i = 0; i < ja.length(); i++) {


                a[i] = r.nextInt(ja.length());
                for(int j = 0; j < i; j++){
                    if(a[i] == a[j]){
                        i --;
                    }

                }

                // 정답 버튼 랜덤
                int b[] = new int[4];
                for(int x = 0; x < 4; x++){
                    b[x] = r.nextInt(4)+1;
                    for(int y = 0; y < x; y++){
                        if(b[x] == b[y]){
                            x--;
                        }
                    }

                }
                // 여기서 문제 랜덤으로 넣기
                JSONObject pjo = ja.getJSONObject(a[i]);

                HashMap problem = new HashMap();
                problem.put("question", pjo.getString("question"));
                problem.put("answer", pjo.getString("answer"));
//                problem.put("example1", pjo.getString("example1"));
//                problem.put("example2", pjo.getString("example2"));
//                problem.put("example3", pjo.getString("example3"));
//                problem.put("example4", pjo.getString("example4"));
                // 답 선택하는 곳도 랜덤
                problem.put("example1", pjo.getString("example"+b[0]));
                problem.put("example2", pjo.getString("example"+b[1]));
                problem.put("example3", pjo.getString("example"+b[2]));
                problem.put("example4", pjo.getString("example"+b[3]));
                // 배열 순서대로 문제가 들어가는데 랜덤으로 넣어야한다.
                problems[i] = problem;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

       // showProblem();
        //
        totalCorrectTextView.setText("Total Correct: 0");
        correctIncorrectTextView.setText("Correct/Incorrect");
    }
    // 시간 추가





    //핸들러 추가
//핸들러 추가
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d", min, sec, mSec);
            result_time = result;
            resultText.setText("경과시간 : "+result);
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (running == 1) { //일시정지를 누르면 멈춤
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


    // =============================
    void showProblem() {

        question = (String)problems[problemNumber - 1].get("question");
        answer = (String)problems[problemNumber - 1].get("answer");
        example1 = (String)problems[problemNumber - 1].get("example1");
        example2 = (String)problems[problemNumber - 1].get("example2");
        example3 = (String)problems[problemNumber - 1].get("example3");
        example4 = (String)problems[problemNumber - 1].get("example4");

        questionTextView.setText(question);
        example1Button.setText(example1);
        example2Button.setText(example2);
        example3Button.setText(example3);
        example4Button.setText(example4);

    }

    public void example1ButtonClicked(View v) {
        Log.d(TAG, "example1ButtonClicked");
        soundPool.play(sound, 1, 1, 0, 0, 1);

        selectExample(example1);

    }

    void selectExample(String example) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        // timer 시작
        if(running == -1){
            running = 1;
            timeThread = new Thread(new timeThread());
            timeThread.start();
        }
        // ==========================
        Log.d(TAG, example);
        if (answer.equals(example)) {
            totalCorrect += 1;
            totalCorrectTextView.setText("정답 개수 : "+Integer.toString(totalCorrect )+ " / 13");
            correctIncorrectTextView.setText("정답 ^^");
            soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
        }
        else {
            correctIncorrectTextView.setText("오답 ㅠㅠ");
            soundPool.play(soundIncorrect, 1, 1, 0, 0, 1);
        }

        example1Button.setEnabled(false);
        example2Button.setEnabled(false);
        example3Button.setEnabled(false);
        example4Button.setEnabled(false);
        Handler h = new Handler() ;
        h.postDelayed(new Runnable() {
            public void run() {
                example1Button.setEnabled(true);
                example2Button.setEnabled(true);
                example3Button.setEnabled(true);
                example4Button.setEnabled(true);

                if (problemNumber < problems.length) {
                    problemNumber += 1;
                    showProblem();
                }
                else {
                    Log.d(TAG, "showGameOverBox");
                    showGameOverBox();
                }
            }}, 1000);
    }

    void showGameOverBox() {

        // timer 종료
        timeThread.interrupt();
        resultText.setText(" 플레이 시간 : " + result_time); // 게임 플레이 시간 표시

        // totalCorrect : 맞춘 점수
        // result_time : 플레이 시간
        System.out.println("total score : "+totalCorrect);
        System.out.println("total time : "+result_time);

        // 커스텀 alert 창
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_eng, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .show();

        score = view.findViewById(R.id.score);
        score.setText("total score : "+totalCorrect + "\ntotal time : " + result_time);

        exit = view.findViewById(R.id.exit);
        reset = view.findViewById(R.id.reset);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Eng_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                alertDialog.dismiss();
                if(running != -1){
                    running = -1;
                }
                replay();
            }
        });

        /*  ===기존 현성님 코드===
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("게임 종료")
                .setMessage("게임을 다시 하시겠습니까?")
                .setNegativeButton("뒤로가기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent _intent = new Intent( Eng_Activity.this, MainActivity.class);
                        startActivity(_intent);
                    }
                })
                .setPositiveButton("다시 하기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(running != -1){
                            running = -1;
                        }
                        replay();
                    }

                })
                .setCancelable(false) //true by default
                .create();
        alertDialog.show();
         */
    }

    void exitApp() {
        finishAffinity();
    }

    void replay() {
        problemNumber = 1;
        totalCorrect = 0;
        //
        showProblem();
        //
        totalCorrectTextView.setText("Total Correct: 0");
        correctIncorrectTextView.setText("Correct/Incorrect");
        System.out.println(totalCorrect);
    }

    public void example2ButtonClicked(View v) {
        Log.d(TAG, "example2ButtonClicked");
        selectExample(example2);
    }

    public void example3ButtonClicked(View v) {
        Log.d(TAG, "example3ButtonClicked");
        selectExample(example3);
    }

    public void example4ButtonClicked(View v) {
        Log.d(TAG, "example4ButtonClicked");
        selectExample(example4);
    }


}
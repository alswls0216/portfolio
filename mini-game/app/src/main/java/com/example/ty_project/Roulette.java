package com.example.ty_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette extends AppCompatActivity {

    private LuckyWheel luckyWheel;
    List<WheelItem> wheelItems ;
    String point;
    private Button volume, back, user_Id_btn;
    private EditText user_Id;
    private String userId, userCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_roulette);

        //volume = findViewById(R.id.volume);
        back = findViewById(R.id.back);
        luckyWheel = findViewById(R.id.roulette);  //변수에 담기
        user_Id = findViewById(R.id.user_Id);
        //user_Id_btn = findViewById(R.id.user_Id_btn);

        generateWheelItems(); //점수판 데이터 생성

        //점수판 타겟 정해지면 이벤트 발생
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                //아이템 변수에 담기
                WheelItem wheelItem = wheelItems.get(Integer.parseInt(point)-1);
                userCharacter = wheelItem.text; //아이템 텍스트 변수에 담기 > 최종 선택되는 아이템 *** == 캐릭터
                Toast.makeText(Roulette.this, "캐릭터 : " + userCharacter, Toast.LENGTH_SHORT).show(); //메시지 출력
            }
        });

        //시작버튼
        Button start = findViewById(R.id.spinButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                point = String.valueOf(random.nextInt(6)+1); // 1 ~ 6
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
            }
        });

        //뒤로가기
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( Roulette.this, Main_activity2.class);
                startActivity(_intent);
            }
        });
        //음향버튼
//        volume.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent _intent = new Intent( Roulette.this, Main_activity2.class);
//                startActivity(_intent);
//            }
//        });
        //사용자 이름 저장
        user_Id_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userId = user_Id.getText().toString();
                //Toast.makeText(Roulette.this, "이름 : " + userId, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateWheelItems() {

        wheelItems = new ArrayList<>();

        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), BitmapFactory.decodeResource(getResources(),
                R.drawable.mandarin), "귤"));

        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), BitmapFactory.decodeResource(getResources(),
                R.drawable.grape), "포 도"));

        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), BitmapFactory.decodeResource(getResources(),
                R.drawable.melon), "멜 론"));

        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.strawberry), "딸 기"));

        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.peach), "복 숭 아"));

        wheelItems.add(new WheelItem(Color.parseColor("#009688"), BitmapFactory.decodeResource(getResources(),
                R.drawable.green_apple), "풋 사 과"));

        //점수판에 데이터 넣기
        luckyWheel.addWheelItems(wheelItems);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}


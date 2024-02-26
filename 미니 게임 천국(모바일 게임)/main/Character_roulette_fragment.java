package com.example.ty_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Character_roulette_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Character_roulette_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private LuckyWheel luckyWheel;
    private List<WheelItem> wheelItems ;
    private TextView player;
    ImageView character;
    private EditText user_Id;
    private Button back, exit, reset;
    private String point, userId, userCharacter;

    public Character_roulette_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_character_roulette, container, false);

        back = view.findViewById(R.id.back);
        luckyWheel = view.findViewById(R.id.roulette);  //변수에 담기
        user_Id = view.findViewById(R.id.user_Id);

        generateWheelItems(); //점수판 데이터 생성

        //점수판 타겟 정해지면 이벤트 발생
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                //아이템 변수에 담기
                WheelItem wheelItem = wheelItems.get(Integer.parseInt(point) - 1);

                userCharacter = wheelItem.text; //선택된 캐릭터 변수에 담기
                userId = user_Id.getText().toString(); //플레이어 ID 저장

                showAlert_roulette();
                //Toast.makeText(getActivity(), "캐릭터 : " + userCharacter + "  |  플레이어 : " + userId, Toast.LENGTH_SHORT).show(); //확인용 메시지 출력

            }

        });

        //시작버튼
        Button start = view.findViewById(R.id.spinButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                point = String.valueOf(random.nextInt(6) + 1); // 1 ~ 6
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
            }
        });

        //뒤로가기
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( getActivity(), Main_activity2.class);
                startActivity(_intent);
            }
        });
        return view;
    }

    private void generateWheelItems() {

        wheelItems = new ArrayList<>();

        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), BitmapFactory.decodeResource(getResources(),
                R.drawable.mandarin), "악어"));

        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), BitmapFactory.decodeResource(getResources(),
                R.drawable.grape), "사자"));

        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), BitmapFactory.decodeResource(getResources(),
                R.drawable.melon), "코끼리"));

        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.strawberry), "양"));

        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.peach), "얼룩말"));

        wheelItems.add(new WheelItem(Color.parseColor("#009688"), BitmapFactory.decodeResource(getResources(),
                R.drawable.green_apple), "돼지"));

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

    // 룰렛 결과 alert
    public void showAlert_roulette() {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_roulette, null);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .show();

        player = view.findViewById(R.id.player);
        player.setText(userId);

        //선택된 캐릭터 표시
        character = view.findViewById(R.id.character);
        if(userCharacter == "악어")
            character.setImageResource(R.drawable.f_croc);
        else if(userCharacter == "코끼리")
            character.setImageResource(R.drawable.f_elephant);
        else if(userCharacter == "사자")
            character.setImageResource(R.drawable.f_lion);
        else if(userCharacter == "돼지")
            character.setImageResource(R.drawable.f_pig);
        else if(userCharacter == "양")
            character.setImageResource(R.drawable.f_sheep);
        else if(userCharacter == "얼룩말")
            character.setImageResource(R.drawable.f_zebra);

        exit = view.findViewById(R.id.exit);
        reset = view.findViewById(R.id.reset);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                alertDialog.dismiss();
            }
        });
    }
}
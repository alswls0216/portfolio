/*
 * Copyright (c) 2021. rogergcc
 */

package com.example.ty_project.ui.menucourses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;
import com.example.ty_project.Character_roulette_model;
import com.example.ty_project.MainActivity;
import com.example.ty_project.R;
import com.example.ty_project.databinding.FragmentCoursesStaggedBinding;
import com.example.ty_project.ui.listeners.CoursesItemClickListener;
import com.example.ty_project.ui.menuhome.HomeCoursesFragment;
import com.example.ty_project.ui.model.CourseCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//



public class CoursesStaggedFragment extends Fragment
        implements CoursesItemClickListener {

    FragmentCoursesStaggedBinding binding;
    private Context mcontext;

    private LuckyWheel luckyWheel;
    private List<WheelItem> wheelItems ;
    private TextView player;
    ImageView character;
    private EditText user_Id;
    private Button back, exit, reset;
    private String point;

    public static String userId, userCharacter;

    SoundPool soundPool;
    private int sound;
    private int spinSound;


    private SharedPreferences preferences;

    MediaPlayer  alertMediaPlayer;

    public CoursesStaggedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        alertMediaPlayer = MediaPlayer.create(this.getContext(),R.raw.r_alert);


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_courses_stagged, container, false);

        View view = inflater.inflate(R.layout.activity_character_roulette, container, false);

        //volume = view.findViewById(R.id.volume);
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
                Log.d("asd###",userCharacter);
                userId = user_Id.getText().toString(); //플레이어 ID 저장

                Log.d("asd###",userId);



                showAlert_roulette();


               // Bundle bundle = new Bundle();
              //  bundle.putString("userId", userId);
                // homeCoursesFragment.setArguments(bundle);

//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                HomeCoursesFragment homeCoursesFragment = new HomeCoursesFragment();
//
//
//                transaction.replace(R.id.nav_host_fragment,homeCoursesFragment).commit();



//                Intent intent = new Intent( getActivity(), MainActivity.class);
//
//                startActivity(intent);




            }

        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(this.getContext(), R.raw.tab_click, 1);
        spinSound = soundPool.load(this.getContext(), R.raw.roulette_spin, 1);

        //시작버튼
        Button start = view.findViewById(R.id.spinButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Random random = new Random();
                point = String.valueOf(random.nextInt(6) + 1); // 1 ~ 6
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
                //soundPool.play(sound,1f,1f,0,0,1f);

              soundPool.play(spinSound,1f,1f,0,2,1f);


            }
        });

        //뒤로가기
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                soundPool.play(sound,1f,1f,0,0,1f);
                Intent _intent = new Intent( getActivity(), MainActivity.class);
                MainActivity.mediaPlayer.stop();
                startActivity(_intent);
            }
        });

        //음향버튼
        /*volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( getActivity(), Main_activity.class);
                startActivity(_intent);
            }
        });*/

        return view;
    }

//    private void performSearch() {
//        binding.edtSearch.clearFocus();
//        InputMethodManager in = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        in.hideSoftInputFromWindow(binding.edtSearch.getWindowToken(), 0);
//        //...perform search
//    }

    private void generateWheelItems() {

        wheelItems = new ArrayList<>();

        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), BitmapFactory.decodeResource(getResources(),
                R.drawable.r_zebra1), " ."));

        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), BitmapFactory.decodeResource(getResources(),
                R.drawable.r_elephant1), "  ."));

        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), BitmapFactory.decodeResource(getResources(),
                R.drawable.r_lion1), "   ."));

        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.r_croc1), "    ."));

        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), BitmapFactory.decodeResource(getResources(),
                R.drawable.r_pig1), "     ."));

        wheelItems.add(new WheelItem(Color.parseColor("#009688"), BitmapFactory.decodeResource(getResources(),
                    R.drawable.r_sheep1), "      ."));

        //점수판에 데이터 넣기
        luckyWheel.addWheelItems(wheelItems);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        //Bitmap bitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    @Override
    public void onDashboardCourseClick(CourseCard courseCard, ImageView imageView) {
        Toast.makeText(mcontext, courseCard.getCourseTitle(), Toast.LENGTH_LONG).show();
    }

    // 룰렛 결과 alert
    public void showAlert_roulette() {

        alertMediaPlayer.start();

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_roulette, null);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .show();

        player = view.findViewById(R.id.player);
        player.setText(userId);

        //선택된 캐릭터 표시
        character = view.findViewById(R.id.character);
        if(userCharacter == "    .")
            character.setImageResource(R.drawable.f_croc);
        else if(userCharacter == "  .")
            character.setImageResource(R.drawable.f_elephant);
        else if(userCharacter == "   .")
            character.setImageResource(R.drawable.f_lion);
        else if(userCharacter == "     .")
            character.setImageResource(R.drawable.f_pig);
        else if(userCharacter == "      .")
            character.setImageResource(R.drawable.f_sheep);
        else if(userCharacter == " .")
            character.setImageResource(R.drawable.f_zebra);

        exit = view.findViewById(R.id.exit);
        reset = view.findViewById(R.id.reset);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                soundPool.play(sound,1f,1f,0,0,1f);
                MainActivity.mediaPlayer.stop();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                soundPool.play(sound,1f,1f,0,0,1f);
                alertDialog.dismiss();
            }
        });
    }
}
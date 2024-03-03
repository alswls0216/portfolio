/*
 * Copyright (c) 2021. rogergcc
 */

package com.example.ty_project.ui.menuhome;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.example.ty_project.Eng_Activity;
import com.example.ty_project.FindSame_activity;
import com.example.ty_project.MainActivity;
import com.example.ty_project.R;
import com.example.ty_project.creativeuiux.loginuikit.LoginActivity3;
import com.example.ty_project.databinding.CardPopularCoursesBinding;
import com.example.ty_project.databinding.FragmentHomeCoursesBinding;
import com.example.ty_project.flyGame.MainFlyActivity;
import com.example.ty_project.ui.menucourses.CoursesStaggedFragment;

import org.w3c.dom.Text;

public class HomeCoursesFragment extends Fragment {

    FragmentHomeCoursesBinding binding;
    CardPopularCoursesBinding binding1;
    Button hello_click;


    String user_Id;



    private CardView game,game2,game3;
    SoundPool soundPool;
    private int sound;



    public HomeCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    private void setUpUI() {
//        String percentage = getResources().getString(R.string.percentage_course, 75);
//        binding.tvPercentage.setText(percentage);
//
//
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home_courses, container, false);
        binding = FragmentHomeCoursesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ImageView imageView =view.findViewById(R.id.home_bg);


        Glide.with(this).load(R.raw.home_bg).into(imageView);



        if(CoursesStaggedFragment.userId == null){
            user_Id = "캐릭터를 선택해주세요!";
        }else{
            user_Id= CoursesStaggedFragment.userId;
        }

        ImageView playerCharacter = view.findViewById(R.id.player_character);
        if (CoursesStaggedFragment.userCharacter==null){
            playerCharacter.setImageResource(R.drawable.refer_icon);
        } else if (CoursesStaggedFragment.userCharacter.equals(" .")){
            playerCharacter.setImageResource(R.drawable.r_zebra1);
        } else if  (CoursesStaggedFragment.userCharacter.equals("  .")){
            playerCharacter.setImageResource(R.drawable.r_elephant1);
        }  else if  (CoursesStaggedFragment.userCharacter.equals("   .")){
            playerCharacter.setImageResource(R.drawable.r_lion1);
        } else if  (CoursesStaggedFragment.userCharacter.equals("    .")){
            playerCharacter.setImageResource(R.drawable.r_croc1);
        } else if  (CoursesStaggedFragment.userCharacter.equals("     .")){
            playerCharacter.setImageResource(R.drawable.r_pig1);
        }else {
            playerCharacter.setImageResource(R.drawable.r_sheep1);
        }


        TextView playerId = view.findViewById(R.id.player_id);
        playerId.setText(user_Id);

            //###로그인폼 이동
//        hello_click= view.findViewById(R.id.hello_click);
//        hello_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getActivity(), LoginActivity3.class);
//                startActivity(intent);
//
//
//            }
//        });


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


        game=view.findViewById(R.id.home_game);
        game2=view.findViewById(R.id.home_game2);
        game3=view.findViewById(R.id.home_game3);


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundPool.play(sound,1f,1f,0,0,1f);
                MainActivity.mediaPlayer.stop();
                Intent intent = new Intent(getActivity(), MainFlyActivity.class);
                startActivity(intent);

            }
        });

                game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound,1f,1f,0,0,1f);
                MainActivity.mediaPlayer.stop();
                Intent intent = new Intent(getContext(), FindSame_activity.class);
                startActivity(intent);

            }
        });

        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound,1f,1f,0,0,1f);
                MainActivity.mediaPlayer.stop();
                Intent intent = new Intent(getActivity(), Eng_Activity.class);
                startActivity(intent);

            }
        });



       // setUpUI();
        return view;
    }





}
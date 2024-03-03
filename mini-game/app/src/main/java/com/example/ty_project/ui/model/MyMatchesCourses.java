/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.ty_project.ui.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ty_project.App;
import com.example.ty_project.R;
import com.example.ty_project.App;

import java.util.Arrays;
import java.util.List;

//###################랭킹페이지 model
public class MyMatchesCourses {

    private static final String STORAGE = "shop";
    private SharedPreferences storage;

    private MyMatchesCourses() {
        storage = App.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public static MyMatchesCourses get() {
        return new MyMatchesCourses();
    }

    public List<MatchCourse> getData() {
        return Arrays.asList(
//                new MatchCourse(1, "Everyday Candle", "$12.00 USD", R.drawable.shop1),
//                new MatchCourse(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.shop2),
//                new MatchCourse(3, "Favourite Board", "$265.00 USD", R.drawable.shop3),
//                new MatchCourse(4, "Earthenware Bowl", "$18.00 USD", R.drawable.shop4),
//                new MatchCourse(5, "Porcelain Dessert Plate", "$36.00 USD", R.drawable.shop5),
//                new MatchCourse(6, "Detailed Rolling Pin", "$145.00 USD", R.drawable.shop6)
                new MatchCourse(1, "비행기 게임", "", R.drawable.fly_game),
                new MatchCourse(2, "같은그림 찾기", "", R.drawable.r_all),
                new MatchCourse(3, "영단어 맞추기", "", R.drawable.home_eng)


        );
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}

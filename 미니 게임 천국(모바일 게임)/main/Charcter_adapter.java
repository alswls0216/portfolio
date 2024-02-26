package com.example.ty_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Charcter_adapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData;
    public Charcter_adapter(@NonNull FragmentManager fm) {
        super(fm);
        mData = new ArrayList<>();
        mData.add(new Character_roulette_fragment());
        mData.add(new Character_choose_fragment());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return (position+1)+"번째";
    }

    @Nullable
    @Override
    public Fragment getItem(int position){
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}

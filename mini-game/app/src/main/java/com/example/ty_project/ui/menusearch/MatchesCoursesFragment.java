/*
 * Copyright (c) 2021. rogergcc
 */

package com.example.ty_project.ui.menusearch;

import static java.lang.Math.abs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ty_project.R;
import com.example.ty_project.databinding.FragmentMatchesCoursesBinding;
import com.example.ty_project.ui.helpers.HorizontalMarginItemDecoration;
import com.example.ty_project.ui.listeners.MatchCourseClickListener;
import com.example.ty_project.ui.model.MatchCourse;
import com.example.ty_project.ui.model.MyMatchesCourses;
import com.example.ty_project.ui.utils.MyUtilsApp;

import java.util.List;
import java.util.Locale;



//####랭킹페이지 fragment
public class MatchesCoursesFragment extends Fragment
        implements
//        DiscreteScrollView.OnItemChangedListener<MatchesCoursesAdapter.ViewHolder>
        MatchCourseClickListener {

    private static final String TAG = "랭킹페이지";
    FragmentMatchesCoursesBinding binding;
    Context mcontext;

    public MatchesCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_matches_courses, container, false);
        binding = FragmentMatchesCoursesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        mcontext = this.getContext();

        MyMatchesCourses myMatchesCourses = MyMatchesCourses.get();
        List<MatchCourse> data = myMatchesCourses.getData();


//        int currentItem = getCurrentItem();
        int currentItem = 1;
        setupViewpager(currentItem, data);

        return view;
    }


    private void setupViewpager(int currentItem, List<MatchCourse> matchCourseList) {
        CourseTopicsViewPager courseTopicsViewPager = new CourseTopicsViewPager(matchCourseList, mcontext, this);
        binding.viewPager.setAdapter(courseTopicsViewPager);
        // set selected item
        binding.viewPager.setCurrentItem(currentItem);
        // You need to retain one page on each side so that the next and previous items are visible
        binding.viewPager.setOffscreenPageLimit(1);
        // Add a PageTransformer that translates the next and previous items horizontally
        // towards the center of the screen, which makes them visible
        int nextItemVisiblePx = (int) getResources().getDimension(R.dimen.viewpager_next_item_visible);
        int currentItemHorizontalMarginPx = (int) getResources().getDimension(R.dimen.viewpager_current_item_horizontal_margin);
        int pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx;
        ViewPager2.PageTransformer pageTransformer = (page, position) -> {
            page.setTranslationX(-pageTranslationX * position);
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.setScaleY(1 - (0.15f * abs(position)));
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        };
        binding.viewPager.setPageTransformer(pageTransformer);
        binding.viewPager.addItemDecoration(new HorizontalMarginItemDecoration(
                mcontext, R.dimen.viewpager_current_item_horizontal_margin_testing,
                R.dimen.viewpager_next_item_visible_testing)
        );
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                countTxtView.setText(String.format(Locale.ENGLISH,"%d/%d", position+1, matchCourseList.size()));
                MyUtilsApp.showLog(TAG, String.format(Locale.ENGLISH, "%d/%d", position + 1, matchCourseList.size()));
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                MyUtilsApp.showToast(mcontext, matchCourseList.get(position).getName());
//                Random rnd = new Random();
//                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                int color= ((int)(Math.random()*16777215)) | (0xFF << 24);

                binding.containerConstraint.setBackgroundColor(color);
            }
        });



    }

    @Override
    public void onScrollPagerItemClick(MatchCourse courseCard, ImageView imageView) {
        MyUtilsApp.showLog(TAG, "LogD onScrollPagerItemClick : " + courseCard.toString());

        MyUtilsApp.showToast(mcontext, courseCard.getName());
        //Now, this has dynamic data from myMatchesCourses.getData();.
        //Could use the Id as unique value for go to new activity
//        Intent intentGetStarted;
//        intentGetStarted = new Intent(mcontext, YourActivity.class);
//        startActivity(intentGetStarted);
    }
}
/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.ty_project.ui.listeners;

import android.widget.ImageView;

import com.example.ty_project.ui.model.MatchCourse;

public interface MatchCourseClickListener {

    void onScrollPagerItemClick(MatchCourse courseCard, ImageView imageView); // Shoud use imageview to make the shared animation between the two activity

}

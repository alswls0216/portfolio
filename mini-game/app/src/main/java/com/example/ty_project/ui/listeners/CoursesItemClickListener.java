/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.ty_project.ui.listeners;

import android.widget.ImageView;

import com.example.ty_project.ui.model.CourseCard;

public interface CoursesItemClickListener {

    void onDashboardCourseClick(CourseCard courseCard, ImageView imageView); // Shoud use imageview to make the shared animation between the two activity

}

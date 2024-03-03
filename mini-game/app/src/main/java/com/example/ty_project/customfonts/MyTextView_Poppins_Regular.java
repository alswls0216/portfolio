package com.example.ty_project.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;


public class MyTextView_Poppins_Regular extends AppCompatTextView {

    public MyTextView_Poppins_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView_Poppins_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView_Poppins_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Poppins-Regular.ttf");
            setTypeface(tf);
        }
    }

}
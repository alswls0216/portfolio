package com.example.ty_project;

import android.app.Application;
import android.content.Intent;
import android.view.View;

import com.example.ty_project.creativeuiux.loginuikit.LoginActivity3;


public class App extends Application {

    private static App instance;


    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;




    }
}

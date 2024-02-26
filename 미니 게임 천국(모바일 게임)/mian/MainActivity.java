package com.example.ty_project;


import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.ty_project.creativeuiux.loginuikit.LoginActivity3;
import com.example.ty_project.databinding.ActivityMainBinding;
import com.example.ty_project.databinding.FragmentHomeCoursesBinding;
import com.example.ty_project.flyGame.MainFlyActivity;
import com.example.ty_project.ui.helpers.BottomNavigationBehavior;
import com.example.ty_project.ui.helpers.DarkModePrefManager;
import com.example.ty_project.ui.menucourses.CoursesStaggedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DarkModePrefManager darkModePrefManager;
    ActivityMainBinding binding;
    NavHostFragment navHostFragment;
    private BottomNavigationView bottomNavigationView;
    String user_Id;
    FragmentHomeCoursesBinding binding2;

    SoundPool soundPool;
    private int sound;

    public static MediaPlayer mediaPlayer;



    public static boolean flag = false;;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer=MediaPlayer.create(this,R.raw.sound_background4);


        if(FindSame_activity.findMediaPlayer!=null){
            FindSame_activity.findMediaPlayer.stop();
        }

        if(Eng_Activity.engMediaPlayer!=null){
            Eng_Activity.engMediaPlayer.stop();
        }



        mediaPlayer.start();
        mediaPlayer.setLooping(true);




        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        setAppTheme();
        setSupportActionBar(binding.appBarMain.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        setupNavigation();

    }


    //=======================================================

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .setUsage(AudioAttributes.USAGE_GAME)
//                    .build();
//
//            soundPool = new SoundPool.Builder()
//                    .setAudioAttributes(audioAttributes)
//                    .build();
//
//        } else
//            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
//
//        sound = soundPool.load(this, R.raw.tab_click, 1);
//
//
//        int id = item.getItemId();
//
//        if (id == R.id.matchesCoursesFragment) {
//            soundPool.play(sound,1f,1f,0,0,1f);
//
//        } else  {
//            soundPool.play(sound,1f,1f,0,0,1f);
//        }
//
//
//        soundPool.play(sound,1f,1f,0,0,1f);;
//
//
//
//        return super.onOptionsItemSelected(item);
//    }

//=======================================================



    private void setAppTheme() {
        darkModePrefManager = new DarkModePrefManager(this);
        boolean isDarkModeEnabled = darkModePrefManager.isNightMode();
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

    }

    private void setupNavigation() {





        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.appBarMain.bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
        }
    }


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_dark_mode) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click

//            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//            finish();
//            overridePendingTransition(0, 0);

            toggleDarkMode();
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Método para cambiar el estado del modo oscuro
    private void toggleDarkMode() {
        boolean isDarkModeEnabled = darkModePrefManager.isNightMode();
        darkModePrefManager.setDarkMode(!isDarkModeEnabled);
//        recreate();

        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(0, 0);

    }









}//

package com.example.smarttravel_finalproject.splash;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.smarttravel_finalproject.AdminUI.AdminsMainActivity;
import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.UserUI.UserMainActivity;
import com.example.smarttravel_finalproject.auth.Welcome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash extends AppCompatActivity {
    ActionBar actionBar;
    Animation top_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user == null){
                    Intent i = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(i);
                    finish();
                }else{
                    //Go to home
                    Intent i = new Intent(getApplicationContext(), AdminsMainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        }, 2500);
    }
    protected void onStart() {
        super.onStart();
        top_anim = AnimationUtils.loadAnimation(this ,R.anim.top_animation );
        ImageView logoImg=findViewById(R.id.img_logo);
        logoImg.setAnimation(top_anim);
    }
}
package com.example.cicerone.Common;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cicerone.R;

public class SplashScreen extends AppCompatActivity {

    ImageView background_image;
    TextView app_name,powered_by_line;

    Animation top_anim,bottom_anim,side_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        background_image = findViewById(R.id.background_image);
        app_name = findViewById(R.id.app_name);
        powered_by_line = findViewById(R.id.powered_by_line);

        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        side_anim = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        background_image.setAnimation(top_anim);
        app_name.setAnimation(side_anim);
        powered_by_line.setAnimation(bottom_anim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this,OnBoarding.class);
            startActivity(intent);
            finish();
        },2000);
    }
}
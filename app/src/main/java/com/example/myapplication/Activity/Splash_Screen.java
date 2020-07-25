package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView tvSplash1 = (TextView)findViewById(R.id.tvSplash1);
        //Typeface typeface = Typeface.createFromAsset(getAssets(),"res/font/Bangers-Regular.ttf");
        //tvSplash1.setTypeface(typeface);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen);

        // Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imgLogo);
        logo = findViewById(R.id.tvSplash1);
        slogan=findViewById(R.id.tvSplash2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         },SPLASH_SCREEN);

    }
}
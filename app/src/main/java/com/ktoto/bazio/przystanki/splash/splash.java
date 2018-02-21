package com.ktoto.bazio.przystanki.splash;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ktoto.bazio.przystanki.MainActivity;
import com.ktoto.bazio.przystanki.R;

import java.util.Timer;


public class splash extends Activity {

    private ImageView obrazek;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        obrazek = (ImageView) findViewById(R.id.imageView);
obrazek.setVisibility(View.VISIBLE);

        Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        obrazek.startAnimation(slide);


        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
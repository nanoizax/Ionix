package com.izax.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent intent = new Intent(Splash.this , MainActivity.class);
                startActivity(intent);
            }
        }.start();

    }
}
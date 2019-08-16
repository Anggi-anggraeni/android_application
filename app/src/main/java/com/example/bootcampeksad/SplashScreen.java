package com.example.bootcampeksad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.bootcampeksad.Utility.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen<SplashScreenActivity> extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {

                if (SessionManager.cekLogin(SplashScreen.this))
                {
                    pindahKeHomeScreen();
                }else{
                    pindahKeLoginScreen();
                };
            }
        };

        Timer timer = new Timer();
        timer.schedule(timertask, 3000);

    };

    private void pindahKeLoginScreen()
    {
        Intent intent = new Intent(SplashScreen.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void pindahKeHomeScreen()
    {
        Intent intent = new Intent(SplashScreen.this,
                HomeActivity.class);
        startActivity(intent);
        finish();
    }

}


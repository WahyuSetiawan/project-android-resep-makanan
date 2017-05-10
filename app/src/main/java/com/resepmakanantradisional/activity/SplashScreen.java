package com.resepmakanantradisional.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.resepmakanantradisional.R;
import com.resepmakanantradisional.database.Database;

public class SplashScreen extends AppCompatActivity {

    private long SPLASH_TIME_OUT = 3000;
    private boolean handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Database database = new Database(SplashScreen.this);
                startMenuUtama();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startMenuUtama() {
        Intent i = new Intent(SplashScreen.this, MenuUtama.class);
        startActivity(i);

        finish();
    }
}

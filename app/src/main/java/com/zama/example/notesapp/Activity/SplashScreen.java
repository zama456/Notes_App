package com.zama.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.zama.example.notesapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
          getSupportActionBar().hide();
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                  finish();
                }
            }


        };thread.start();
    }

}
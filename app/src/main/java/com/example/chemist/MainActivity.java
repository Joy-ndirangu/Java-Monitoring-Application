package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the signup activity
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent the user from going back to it
            }
        }, SPLASH_SCREEN);
    }
}
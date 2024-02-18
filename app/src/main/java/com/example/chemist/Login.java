package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button login_btn, empbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = (Button) findViewById(R.id.noaccount);
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);

            }
        });

        Button empbtn = (Button) findViewById(R.id.loginbtn);
        empbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this, manager_home.class);
                startActivity(intent);

            }
        });
    }
}
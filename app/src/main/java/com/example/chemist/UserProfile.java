package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    //variables
    TextInputLayout profile_name, profile_username, profile_email, profile_phone, profile_password;
    TextView fullNameLabel, usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//hooks
        profile_name = findViewById(R.id.profile_fullname);
        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
        profile_password = findViewById(R.id.profile_password);
        fullNameLabel = findViewById(R.id.fullname_label);
        usernameLabel = findViewById(R.id.username_label);

        //function to show all data
        showAllUserData();


    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phone");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        profile_name.getEditText().setText(user_name);
        profile_email.getEditText().setText(user_email);
        profile_phone.getEditText().setText(user_phoneNo);
        profile_password.getEditText().setText(user_password);
    }
}
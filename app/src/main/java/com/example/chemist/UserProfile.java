package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    //variables
    TextInputLayout profile_name, profile_username, profile_email, profile_phone, profile_password;
    TextView fullNameLabel, usernameLabel;
    FirebaseAuth auth;
    FirebaseUser user;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//hooks
        auth = FirebaseAuth.getInstance();
        profile_name = findViewById(R.id.profile_fullname);
        profile_username = findViewById(R.id.profile_username);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
        profile_password = findViewById(R.id.profile_password);
        fullNameLabel = findViewById(R.id.fullname_label);
        usernameLabel = findViewById(R.id.username_label);
        updatebtn = findViewById(R.id.updatebtn);

        //getting details of current logged in user
        user = auth.getCurrentUser();
//if user is not logged in return to registration
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        //setting the email
        else {
            profile_email.getEditText().setText(user.getEmail());

        }
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });




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
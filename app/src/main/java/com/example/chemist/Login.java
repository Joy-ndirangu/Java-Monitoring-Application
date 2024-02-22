package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //variables
    TextInputLayout username, password;

    Button noaccount_btn, login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernamelogin);
        password = findViewById(R.id.passwordlogin);
        noaccount_btn = findViewById(R.id.noaccount);
        login_btn = findViewById(R.id.loginbtn);

        noaccount_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Validate Login Info
                if (!validateUsername() | !validatePassword()) {
                    username.setError("User not authenticated");
                } else {
                    //to check if there is data related to the username and password in firebase
                    isUser();
                }

            }
        });
    }


    //validation of username and password

    //For username
    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    //For password
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


    //get the user entered values Username and Password in String using the isUser()method
    private void isUser() {
//        progressBar.setVisibility(View.VISIBLE);


        //getting the user entered details/values inside a string
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        //to start using the data stored in the firebase realtime database we need a database reference variable
        //instance is the parent node of the data tree in our firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        //query looks inside the username for all the users and match it with the value entered by the user.
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //checking if there is data inside the datasnapshot
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    //if the data exists, fetch the password of that user and get it inside this string
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    //checking if the password in database is similar to that entered(authentication)
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(Login.this, UserProfile.class);
                        //initializing data inside the intent
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phone", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
//                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
//                    progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            //empty implementation for the onCancelled() method since i don't need to handle any specific logic when a database operation is canceled.
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //variables
    TextInputLayout regName, regUsername, regEmail, regPassword, regPhone;
    Button reg_btn, tologin;
    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.fullname);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regPhone = findViewById(R.id.phone);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        tologin = (Button) findViewById(R.id.haveaccount_btn);

        //firebase


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for users to store their data in firebase once they click register button
                //getinstance will call to the rootnode which has al the database from firebase of the project
                rootnode = FirebaseDatabase.getInstance();

                reference = rootnode.getReference("users");

                //conditional statement to check for validation using the varous functions for validation
                if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUserName())
                {
                    return;
                }

                //get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();


                //calling the userhelper class
                UserHelperClass helperClass = new UserHelperClass(name, username, email, password, phone);

                // to add more users so that each have an id use child and pass what you'd want to use as id
                reference.child(phone).setValue(helperClass);//value that will be passed is stored in the database

                //Click event for the Register button to redirect to Login page
                Intent intent;
                intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);


            }
        });

        //click event for the have an account link to redirect registered users to login
        tologin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);

            }
        });
    }


        //validation functions for each textfield
    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("field cannot be empty");
            return false;
        } else if (val.length() > 15) {
            regUsername.setError("username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhone.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhone.setError("Field cannot be empty");
            return false;
        } else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }



}
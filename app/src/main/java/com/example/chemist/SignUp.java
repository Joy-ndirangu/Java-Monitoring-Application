package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //variables
    TextInputLayout regName, defRoles, regEmail, regPassword, regPhone;
    Button reg_btn, tologin;
    ProgressBar progressBar;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
// ...



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.fullname);
        regEmail = findViewById(R.id.email);
        defRoles = findViewById(R.id.defRoles);
        regPassword = findViewById(R.id.password);
        regPhone = findViewById(R.id.phone);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        tologin = (Button) findViewById(R.id.haveaccount_btn);
        progressBar = findViewById(R.id.progressbar);

        //firebase


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting visibilit for the progressbar
                progressBar.setVisibility(View.VISIBLE);

                //for users to store their data in firebase once they click register button
                //getinstance will call to the rootnode which has al the database from firebase of the project
                rootnode = FirebaseDatabase.getInstance();

                reference = rootnode.getReference("users");

                //conditional statement to check for validation using the varous functions for validation
                if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateRoles())
                {
                    return;
                }

                //get all the values
                String name = regName.getEditText().getText().toString();
                String roles = defRoles.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();


//for authentication using email and password
                // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                Log.d("user data",String.valueOf(mAuth));

                                if (task.isSuccessful()) {

                                    Toast.makeText(SignUp.this, "Registration successful.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Log.d("User Data", "create user failure", task.getException());

                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



                //calling the userhelper class
                UserHelperClass helperClass = new UserHelperClass(name, email,  password, phone,roles );
//                User helperClass = new User(name, email,  roles, phone,  password);
//
//                // to add more users so that each have an id use child and pass what you'd want to use as id
                reference.child(phone).setValue(helperClass);//value that will be passed is stored in the database

                //Click event for the Register button to redirect to Login page
//                Intent intent;
//                intent = new Intent(SignUp.this, Login.class);
//                startActivity(intent);


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
            defRoles.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateRoles() {
        String val = defRoles.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            defRoles.setError("field cannot be empty");
            return false;
        } else if (val.length() > 15) {
            defRoles.setError("username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            defRoles.setError("White Spaces are not allowed");
            return false;
        } else {
            defRoles.setError(null);
            defRoles.setErrorEnabled(false);
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
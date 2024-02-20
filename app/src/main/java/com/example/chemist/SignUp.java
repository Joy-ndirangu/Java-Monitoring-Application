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
        final FirebaseDatabase[] rootnode = new FirebaseDatabase[1];
        final DatabaseReference[] reference = new DatabaseReference[1];


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for users to store their data in firebase once they click register button
                //getinstance will call to the rootnode which has al the database from firebase of the project

                rootnode[0] = FirebaseDatabase.getInstance();
                reference[0] = rootnode[0].getReference("users");

                //get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                String phone = regPhone.getEditText().getText().toString();


                //calling the userhelper class
                UserHelperClass helperClass = new UserHelperClass(name, username, email, password, phone);

                // to add more users so that each have an id use child and pass what you'd want to use as id
                reference[0].child(phone).setValue(helperClass);//value that will be passed is stored in the database

                //Click event for the Register button to redirect to Login page
                Intent intent;
                intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);


            }
        });

        //click event for the have an account link to redirect registered users to login
        tologin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);

            }
        });
    }
}
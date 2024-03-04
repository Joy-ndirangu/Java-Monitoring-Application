package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //variables
    TextInputLayout loginemail, loginpassword;
    ProgressBar progressBar;

    Button noaccount_btn, login_btn;

    //for authentication
    private FirebaseAuth mAuth;
// ...

// checking if user is signed in

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //if already signed in open next activity
            Intent intent = new Intent(getApplicationContext(), manager_home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.emaillogin);
        loginpassword = findViewById(R.id.passwordlogin);
        noaccount_btn = findViewById(R.id.noaccount);
        login_btn = findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progressbar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        noaccount_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Validate Login Info
                if (!validateEmail() | !validatePassword()) {
                    loginemail.setError("User not authenticated");
                }


                String email = loginemail.getEditText().getText().toString();
                String password = loginpassword.getEditText().getText().toString();

//                signing a user using email and password

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View. GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Authentication successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), manager_home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }


    //validation of username and password

    //For username
    private Boolean validateEmail() {
        String val = loginemail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            loginemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            loginemail.setError("Invalid email address");
            return false;
        } else {
            loginemail.setError(null);
            loginemail.setErrorEnabled(false);
            return true;
        }
    }

    //For password
    private Boolean validatePassword() {
        String val = loginpassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            loginpassword.setError("Field cannot be empty");
            return false;
        } else {
            loginpassword.setError(null);
            loginpassword.setErrorEnabled(false);
            return true;
        }
    }


    //get the user entered values Username and Password in String using the isUser()method
}
package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    //variables
    TextInputLayout profile_name, profile_role, profile_email, profile_phone;

    FirebaseAuth auth;
    FirebaseUser user;
    Button updatebtn;
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //hooks
        auth = FirebaseAuth.getInstance();
        profile_name = findViewById(R.id.profile_fullname);
        profile_role = findViewById(R.id.profile_role);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
        updatebtn = findViewById(R.id.updatebtn);
        reference = FirebaseDatabase.getInstance().getReference("users");

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
//            profile_name.getEditText().setText((CharSequence) reference.child("name"));
////            profile_role.getEditText().setText(user.getR());
////            profile_phone.getEditText().setText(user.get());

            showAllUserData();

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
        String userID = user.getUid();
        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String fullName = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String roles = snapshot.child("roles").getValue(String.class);
                    String phone = snapshot.child("phone").getValue(String.class);


                    profile_name.getEditText().setText(fullName);
                    profile_email.getEditText().setText(email);
                    profile_role.getEditText().setText(roles);
                    profile_phone.getEditText().setText(phone);


                }
                else{
                    Toast.makeText(UserProfile.this, "User has no access to account",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class manager_home extends AppCompatActivity {


    CardView cardEmployees;
    CardView cardSchedules;
    CardView cardTasks;
    CardView cardInventory;
    CardView cardSettings;
    CardView cardLogout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

//

//        for the grid layout

        cardEmployees = findViewById(R.id.cardemployees);
        cardSchedules = findViewById(R.id.cardschedule);
        cardTasks = findViewById(R.id.cardtasks);
        cardInventory = findViewById(R.id.cardinventory);
        cardSettings = findViewById(R.id.cardSettings);
        cardLogout = findViewById(R.id.cardLogout);

        cardEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(manager_home.this, Employees_Recycler.class);
                startActivity(intent);

            }        });
        cardSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Chat Clicked");
            }
        });
        cardTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(manager_home.this, newEmployee.class);
                startActivity(intent);

            }
        });
        cardInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(manager_home.this, UserProfile.class);
                startActivity(intent);
            }
        });
        cardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Settings Clicked");
            }
        });
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Logout Clicked");
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    }

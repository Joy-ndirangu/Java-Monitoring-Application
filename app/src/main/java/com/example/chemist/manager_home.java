package com.example.chemist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class manager_home extends AppCompatActivity {

    Toolbar topAppBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);


        topAppBar = findViewById(R.id.topAppBar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon press
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profilepic:
                        // Handle favorite icon press
                        return true;
                    case R.id.search:
                        // Handle search icon press
                        return true;
                    case R.id.more:
                        // Handle more item (inside overflow menu) press
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}
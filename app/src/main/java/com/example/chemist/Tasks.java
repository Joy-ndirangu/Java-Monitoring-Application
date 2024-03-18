package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    RecyclerView taskRecycler;
    DatabaseReference databaseReference;
    TaskAdapter taskAdapter;
    ArrayList<TaskModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        FloatingActionButton fab = findViewById(R.id.fab);


        taskRecycler = findViewById(R.id.tasks_recycler);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tasks");
        taskRecycler.setHasFixedSize(true);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, arrayList);
        taskRecycler.setAdapter(taskAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TaskModel task = dataSnapshot.getValue(TaskModel.class);
                    arrayList.add(task);
                }
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Tasks.this, New_Task.class);
                startActivity(intent);
            }
        });
    }
}
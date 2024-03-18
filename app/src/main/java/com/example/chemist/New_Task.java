package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class New_Task extends AppCompatActivity {

    Button cancel, addbtn;

    TextInputLayout task_name, start_date, due_date, schedule_id, assignee;

    Spinner spinner_employees;

    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        cancel = findViewById(R.id.cancel_task);
        addbtn = findViewById(R.id.add_tasks);
        task_name = findViewById(R.id.taskName);
        start_date = findViewById(R.id.startDate);
        due_date = findViewById(R.id.dueDate);
        schedule_id = findViewById(R.id.schedule_id);
        spinner_employees =findViewById(R.id.spinner);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Employees");
//        user = auth.getCurrentUser();
//
//        String userID = user.getUid();
        databaseReference.child("employee_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<RecyclerModel> employees = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                    RecyclerModel employee = userSnapshot.getValue(RecyclerModel.class);
                    if ( employee != null){
                        Log.d("Employees", String.valueOf(employee));
                        employees.add(employee);
                    }
                }
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(New_Task.this, R.array.assignees, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spinner_employees.setAdapter(adapter);

//                updateArrayAdapter(employees);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
                Intent intent = new Intent(getApplicationContext(), Tasks.class);
                startActivity(intent);
                finish();


            }
        });

//        adapter = ArrayAdapter.createFromResource(this, R.array.assignees, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//
//        spinner_employees.setAdapter(adapter);


    }

    //saving employee dta into database
    private  void addTask() {
        Map<String, Object> map = new HashMap<>();
        map.put("Task_Name", task_name.getEditText().getText().toString());
        map.put("Start_Date", start_date.getEditText().getText().toString());
        map.put("Due_Date", due_date.getEditText().getText().toString());
        map.put("Schedule_ID", schedule_id.getEditText().getText().toString());
        map.put("Assignee", assignee.getEditText().getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Tasks").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(New_Task.this, "Task Data added successfully", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(New_Task.this, "Error while adding task data", Toast.LENGTH_SHORT).show();

                    }
                });
    }

//    private void getAssignees(){
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Employees");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                List<RecyclerModel> employees = new ArrayList<RecyclerModel>();
//                for (DataSnapshot userSnapshot : snapshot.getChildren()){
//                    RecyclerModel employee = userSnapshot.getValue(RecyclerModel.class);
//                    if ( employee != null){
//                        employees.add(employee);
//                    }
//                }
//
//                updateArrayAdapter(employees);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    private void updateArrayAdapter(List<RecyclerModel> employees){
//        List<String> employee_names = new ArrayList<>();
//        for (RecyclerModel employee : employees){
//            employee_names.add(employee.getEmployee_name());
//        }
//        adapter.addAll(employee_names);
//        adapter.notifyDataSetChanged();
//    }
}
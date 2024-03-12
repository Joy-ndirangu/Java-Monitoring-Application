package com.example.chemist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class newEmployee extends AppCompatActivity {

    Button savebtn, cancelbtn;

    TextInputLayout empid, empname, empemail, emp_phone, empbranch, empurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        empid = findViewById(R.id.empid);
        empname = findViewById(R.id.empname);
        empemail = findViewById(R.id.empemail);
        empbranch = findViewById(R.id.branch);
        emp_phone = findViewById(R.id.emp_phone);
        empurl = findViewById(R.id.imageurl);
        savebtn = findViewById(R.id.save_emp);
        cancelbtn = findViewById(R.id.cancel_emp);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                Intent intent = new Intent(getApplicationContext(), Employees_Recycler.class);
                startActivity(intent);
                finish();


            }
        });

    }

    //saving employee dta into database
    private  void insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("employee_id", empid.getEditText().getText().toString());
        map.put("employee_name", empname.getEditText().getText().toString());
        map.put("employee_email", empemail.getEditText().getText().toString());
        map.put("employee_branch", empbranch.getEditText().getText().toString());
        map.put("employee_phone", emp_phone.getEditText().getText().toString());
        map.put("employee_url", empurl.getEditText().getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Employees").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                Toast.makeText(newEmployee.this, "Employee Data added successfully", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(newEmployee.this, "Error while adding employee data", Toast.LENGTH_SHORT).show();

                    }
                });


    }
}
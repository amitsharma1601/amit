package com.example.assignment3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment3.R;

import Models.StudentDetails;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewModifyActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_adding);

        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", 0);
        StudentDetails detail = intent.getParcelableExtra("detail");
        switch (flag) {
            case 0:
                openViewMode(detail);
                break;
            case 1:
                openModifyMode(detail);
                break;
        }
    }

    private void openViewMode(StudentDetails detail) {
        getSupportActionBar().setTitle("VIEW DETAILS");
        EditText student_name = findViewById(R.id.et_studentadding_student_name);
        EditText student_class = findViewById(R.id.et_studentadding_student_class);
        EditText student_rollno = findViewById(R.id.et_studentadding_student_rollno);
        student_name.setText(detail.getStudentName());
        student_name.setFocusable(false);
        student_class.setText(detail.getStudentClass().toString());
        student_class.setFocusable(false);
        student_rollno.setText(detail.getRollNumber());
        student_rollno.setFocusable(false);
        Button submit = findViewById(R.id.btn_studentadding_submit);
        submit.setVisibility(View.INVISIBLE);
    }

    private void openModifyMode(StudentDetails detail) {
        getSupportActionBar().setTitle("UPDATE DETAILS");
        final EditText etStudentName = findViewById(R.id.et_studentadding_student_name);
        final EditText etStudentClass = findViewById(R.id.et_studentadding_student_class);
        final EditText etStudentRollNo = findViewById(R.id.et_studentadding_student_rollno);
        etStudentName.setText(detail.getStudentName());
        etStudentClass.setText(detail.getStudentClass().toString());
        etStudentRollNo.setText(detail.getRollNumber());

        Button btnSubmit = findViewById(R.id.btn_studentadding_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getIntent().getIntExtra("position", 0);
                StudentDetails studentDetails = new StudentDetails(etStudentName.getText().toString(),
                        new Integer(etStudentClass.getText().toString()),
                        etStudentRollNo.getText().toString());
                Intent intent = new Intent(getApplicationContext(), StudentListingActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("detail", studentDetails);
                startActivity(intent);
                finish();
            }
        });


    }
}

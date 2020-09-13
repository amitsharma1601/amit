package com.example.assignment3.Activities;

import Adapters.StudentDetailsAdapter;
import Fragments.RecyclerViewFragment;
import Fragments.TextViewFragment;
import Models.StudentDetails;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentListingActivity extends AppCompatActivity {

    private static  List<StudentDetails> studentsDetailsList;
    private static final int STUDENT_ADD = 0;
    private static StudentDetailsAdapter mAdapter;
    private boolean gridview_icon_clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_listing);



        if(studentsDetailsList == null) {
            studentsDetailsList = new ArrayList<>();
            mAdapter = new StudentDetailsAdapter(studentsDetailsList , true);
        }

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        StudentDetails detail = intent.getParcelableExtra("detail");
        if(detail != null){
            studentsDetailsList.set(position, detail);
            mAdapter.notifyDataSetChanged();
        }
        Fragment textViewFragment = new TextViewFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(studentsDetailsList.isEmpty()) {
            ft.add(R.id.student_listing_place_holder, textViewFragment);
        }
        else{
            Fragment recyclerViewFragment = new RecyclerViewFragment(mAdapter, true);
            ft.replace(R.id.student_listing_place_holder, recyclerViewFragment);
        }
        ft.commit();
        Button buttonAdd = findViewById(R.id.btn_student_listing_add);
        handleAddButtonClick();
    }



    private void handleAddButtonClick(){
        Button button = findViewById(R.id.btn_student_listing_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentAddingActivity.class);
                startActivityForResult(intent, STUDENT_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == STUDENT_ADD && resultCode == RESULT_OK) {
            StudentDetails studentDetail = data.getParcelableExtra("studentdetail");
            studentsDetailsList.add(studentDetail);
            if(studentsDetailsList.size() == 1){
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment recyclerViewFragment = new RecyclerViewFragment(mAdapter, true);
                fragmentTransaction.replace(R.id.student_listing_place_holder, recyclerViewFragment);
                fragmentTransaction.commit();
            }
            mAdapter.notifyDataSetChanged();
            Log.e("ArrayList" , studentsDetailsList.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer id = item.getItemId();
        Log.d("ID", id.toString());
        switch (id){
            case R.id.name_sort:
                sortListByName(studentsDetailsList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.roll_sort:
                sortListByRollNo(studentsDetailsList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.action_view:
                if(studentsDetailsList.size() != 0){

                    if(gridview_icon_clicked) {
                        item.setIcon(R.drawable.ic_listview);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Fragment recyclerViewFragment = new RecyclerViewFragment(mAdapter, false);
                        fragmentTransaction.replace(R.id.student_listing_place_holder, recyclerViewFragment);
                        fragmentTransaction.commit();
                        gridview_icon_clicked = false;
                    }
                    else{
                        item.setIcon(R.drawable.ic_gridview);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Fragment recyclerViewFragment = new RecyclerViewFragment(mAdapter, true);
                        fragmentTransaction.replace(R.id.student_listing_place_holder, recyclerViewFragment);
                        fragmentTransaction.commit();
                        gridview_icon_clicked = true;
                    }
                }
        }
        return true;
    }

    private void sortListByRollNo(List<StudentDetails> list) {
        Collections.sort(list, new Comparator<StudentDetails>() {
            @Override
            public int compare(StudentDetails o1, StudentDetails o2) {
                return o1.getRollNumber().toLowerCase().compareTo(o2.getRollNumber().toLowerCase());
            }
        });
    }

    private void sortListByName(List<StudentDetails> list) {
        Collections.sort(list, new Comparator<StudentDetails>() {
            @Override
            public int compare(StudentDetails o1, StudentDetails o2) {
                return o1.getStudentName().toLowerCase().compareTo(o2.getStudentName().toLowerCase());
            }
        });
    }
}
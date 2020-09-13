package com.example.assignment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment2.R;
import com.google.android.material.snackbar.Snackbar;

public class OtpActivity extends AppCompatActivity {

    private static final String RESEND_MESSAGE = "OTP is sent again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        final EditText et1 = findViewById(R.id.et1);
        final EditText et2 = findViewById(R.id.et2);
        final EditText et3 = findViewById(R.id.et3);
        final EditText et4 = findViewById(R.id.et4);
        et1.requestFocus();
        et1.addTextChangedListener(new MyTextWatcher(et1,et1,et2,et3,et4));
        et2.addTextChangedListener(new MyTextWatcher(et2,et1,et2,et3,et4));
        et3.addTextChangedListener(new MyTextWatcher(et3,et1,et2,et3,et4));
        et4.addTextChangedListener(new MyTextWatcher(et4,et1,et2,et3,et4));

        Button resend = findViewById(R.id.resend_button);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v , RESEND_MESSAGE, Snackbar.LENGTH_SHORT).show();
            }
        });

        final String valid_otp = "1234";

        Button submit = findViewById(R.id.submit_otp);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = et1.getText().toString() +
                             et2.getText().toString() +
                             et3.getText().toString() +
                             et4.getText().toString();

                if(otp.equals(valid_otp)){
                    Snackbar.make(v, "Correct OTP", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Snackbar.make(v, "Please enter correct OTP", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }
}

class MyTextWatcher implements TextWatcher {

    private View view;
    EditText et1, et2, et3, et4;

    public MyTextWatcher(View view, EditText et1, EditText et2, EditText et3, EditText et4) {
        this.view = view;
        this.et1 = et1;
        this.et2 = et2;
        this.et3 = et3;
        this.et4 = et4;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        switch (view.getId()) {
            case R.id.et1:
                if (text.length() == 1)
                    et2.requestFocus();
                break;
            case R.id.et2:
                if (text.length() == 1)
                    et3.requestFocus();
                else if (text.length() == 0)
                    et1.requestFocus();
                break;
            case R.id.et3:
                if (text.length() == 1)
                    et4.requestFocus();
                else if (text.length() == 0)
                    et2.requestFocus();
                break;
            case R.id.et4:
                if (text.length() == 0)
                    et3.requestFocus();
                break;
        }
    }
}

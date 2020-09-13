package com.example.assignment2.Activities;

import Services.Validators;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String VALID_EMAIL = "admin@gmail.com";
    public static final String VALID_PASSWORD = "qwertyui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = findViewById(R.id.register_now);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.email);
                String emailString = email.getText().toString();
                EditText password = findViewById(R.id.email);
                String passwordString = password.getText().toString();

                if (emailString.equals(VALID_EMAIL) && passwordString.equals(passwordString)) {
                    Snackbar.make(v, "Login Successful", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(v, "invalid email or password", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
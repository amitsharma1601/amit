package com.example.assignment2.Activities;

import Services.Validators;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SpannableString terms = new SpannableString(getString(R.string.terms_and_conditions));
        terms.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        TextView term = findViewById(R.id.terms_textview);
        term.setText(terms);
        SpannableString sign_in = new SpannableString(getString(R.string.sign_in));
        sign_in.setSpan(new UnderlineSpan(), 0, sign_in.length(), 0);
        TextView sign = findViewById(R.id.sign_in_textview);
        sign.setText(sign_in);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        Button submit = findViewById(R.id.submit_reg);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText fullname = findViewById(R.id.fullname);
               if(fullname.getText().toString().isEmpty()){
                   Snackbar.make(v, "Please Enter your full name", Snackbar.LENGTH_SHORT).show();
                   return;
               }

               EditText email = findViewById(R.id.email_reg);
               String emailString = email.getText().toString();
               if(emailString.isEmpty()){
                   Snackbar.make(v, "Please Enter your email", Snackbar.LENGTH_SHORT).show();
                   return;
               }

               if(!Validators.IsEmailFormatValid(emailString)){
                   Snackbar.make(v, "Email format is not valid", Snackbar.LENGTH_SHORT).show();
                   return;
               }

                EditText password = findViewById(R.id.password_reg);
                String passwordString = password.getText().toString();
                if(passwordString.length() < 6){
                    Snackbar.make(v, "Password should be of minimum 6 characters", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                CheckBox checkBox = findViewById(R.id.agreement);
                if(!checkBox.isChecked()){
                    Snackbar.make(v, "Please accept the terms and conditions", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Snackbar.make(v, "Please login now", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
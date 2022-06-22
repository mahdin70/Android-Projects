package com.example.rentalboss;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullname,email,contact,password;
    TextView fullname_label,username_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullname = findViewById(R.id.fullname_profile);
        email = findViewById(R.id.email_profile);
        contact = findViewById(R.id.contact_profile);
        password = findViewById(R.id.password_profile);
        fullname_label = findViewById(R.id.fullname_label);
        username_label = findViewById(R.id.username_label);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();

        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullname_label.setText(user_name);
        username_label.setText(user_username);
        fullname.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        contact.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_name);
    }
}
package com.example.rentalboss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputEditText fullname,email,contact,password;
    TextView fullname_label,username_label;

    DatabaseReference reference;

    String user_username,user_name,user_email,user_phoneNo,user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        fullname = findViewById(R.id.fullname_profile);
        email = findViewById(R.id.email_profile);
        contact = findViewById(R.id.contact_profile);
        password = findViewById(R.id.password_profile);
        fullname_label = findViewById(R.id.fullname_label);
        username_label = findViewById(R.id.username_label);

        showAllUserData();
    }

    public void showAllUserData() {
        Intent intent = getIntent();

        user_username = intent.getStringExtra("username");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_phoneNo = intent.getStringExtra("phoneNo");
        user_password = intent.getStringExtra("password");

        fullname_label.setText(user_name);
        username_label.setText(user_username);
        fullname.setText(user_name);
        email.setText(user_email);
        contact.setText(user_phoneNo);
        password.setText(user_password);
    }

    public void update(View view){
        if(isNameChanged() || isPasswordChanged() || isEmailChanged() || isPhoneChanged()){
            Toast.makeText(this, "Profile has been updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPhoneChanged() {
        if(!user_phoneNo.equals(contact.getEditableText().toString())){
            reference.child(user_username).child("phoneNo").setValue(contact.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!user_email.equals(email.getEditableText().toString())){
            reference.child(user_username).child("email").setValue(email.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!user_password.equals(password.getEditableText().toString())){
            reference.child(user_username).child("password").setValue(password.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!user_name.equals(fullname.getEditableText().toString())){
            reference.child(user_username).child("name").setValue(fullname.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}
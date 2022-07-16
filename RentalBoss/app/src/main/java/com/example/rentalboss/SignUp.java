package com.example.rentalboss;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity
{
    TextInputLayout reg_name,reg_username,reg_email,reg_password,reg_phoneNo;
    Button reg_btn,reg_loginbtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        reg_name = findViewById(R.id.reg_name);
        reg_username = findViewById(R.id.reg_username);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        reg_phoneNo = findViewById(R.id.reg_phoneNo);
        reg_btn = findViewById(R.id.reg_btn);
        reg_loginbtn = findViewById(R.id.reg_loginbtn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName()| !validateUserName() | !validateEmail() | !validatePhoneNo() | !validatePassword())
                {
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = reg_name.getEditText().getText().toString();
                String username = reg_username.getEditText().getText().toString();
                String email = reg_email.getEditText().getText().toString();
                String phoneNo = reg_phoneNo.getEditText().getText().toString();
                String password = reg_password.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignUp.this, "You have been registered to Rental Boss", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        reg_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }

   private boolean validateName()
    {
        String val = reg_name.getEditText().getText().toString();

        if(val.isEmpty()){
            reg_name.setError("This field cannot be left empty");
            return false;
        }
        else{
            reg_name.setError(null);
            reg_name.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateUserName()
    {
        String val = reg_username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty())
        {
            reg_username.setError("This field cannot be left empty");
            return false;
        }
        else if(val.length() >=15)
        {
            reg_username.setError("Username is too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace))
        {
            reg_username.setError("Whitespaces and special characters are not allowed");
            return false;
        }
        else
        {
            reg_username.setError(null);
            reg_username.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail()
    {
        String val = reg_email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty())
        {
            reg_email.setError("This field cannot be left empty");
            return false;
        }
        else if(!val.matches(emailPattern))
        {
            reg_email.setError("Invalid Email Address");
            return false;
        }
        else
        {
            reg_email.setError(null);
            reg_email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhoneNo()
    {
        String val = reg_phoneNo.getEditText().getText().toString();

        if(val.isEmpty())
        {
            reg_phoneNo.setError("This field cannot be left empty");
            return false;
        }
        else
        {
            reg_phoneNo.setError(null);
            reg_phoneNo.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword()
    {
        String val =  reg_password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.isEmpty())
        {
            reg_password.setError("This field cannot be left empty");
            return false;
        }
        else if (!val.matches(passwordVal))
        {
            reg_password.setError("Password is too weak");
            return false;
        }
        else
        {
            reg_password.setError(null);
            reg_password.setErrorEnabled(false);
            return true;
        }
    }
}

package com.example.rentalboss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity
{

    Button call_SignUp;
    Button login_User;
    ImageView logo_image;
    TextView logo_text,signin_text;
    TextInputLayout username_text,password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        call_SignUp = findViewById(R.id.call_SignUp);
        logo_image = findViewById(R.id.logo_image);
        logo_text = findViewById(R.id.logo_text);
        login_User = findViewById(R.id.login_User);
        signin_text = findViewById(R.id.signin_text);
        username_text = findViewById(R.id.username_text);
        password_text = findViewById(R.id.password_text);


        call_SignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
        login_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() | !validatePassword()){
                    return;
                }
                else
                {
                    isUser();
                }
            }
        });
    }

    private boolean validateUsername(){
        String val = username_text.getEditText().getText().toString();
        if(val.isEmpty())
        {
            username_text.setError("This field cannot be left empty");
            return false;
        }
        else
        {
            username_text.setError(null);
            username_text.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password_text.getEditText().getText().toString();
        if(val.isEmpty())
        {
            password_text.setError("This field cannot be left empty");
            return false;
        }
        else
        {
            password_text.setError(null);
            password_text.setErrorEnabled(false);
            return true;
        }
    }
    private void isUser() {
        String userEnteredUserName = username_text.getEditText().getText().toString().trim();
        String userEnteredPassword = password_text.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUserName);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(6);
                if(dataSnapshot.exists()){

                    username_text.setError(null);
                    username_text.setErrorEnabled(false);

                    String passwordfromDB = dataSnapshot.child(userEnteredUserName).child("password").getValue(String.class);
                    if(passwordfromDB.equals(userEnteredPassword)){
                        username_text.setError(null);
                        username_text.setErrorEnabled(false);

                        String namefromDB = dataSnapshot.child(userEnteredUserName).child("name").getValue(String.class);
                        String emailfromDB = dataSnapshot.child(userEnteredUserName).child("email").getValue(String.class);
                        String phoneNofromDB = dataSnapshot.child(userEnteredUserName).child("phoneNo").getValue(String.class);
                        String usernamefromDB = dataSnapshot.child(userEnteredUserName).child("username").getValue(String.class);



                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("name",namefromDB);
                        intent.putExtra("email",emailfromDB);
                        intent.putExtra("phoneNo",phoneNofromDB);
                        intent.putExtra("username",usernamefromDB);
                        intent.putExtra("password",passwordfromDB);

                        startActivity(intent);
                    }
                    else{
                        password_text.setError("Wrong Password");
                        password_text.requestFocus();
                    }
                }
                else{
                    username_text.setError("No Such User Exists");
                    username_text.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
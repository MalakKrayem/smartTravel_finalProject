package com.example.smarttravel_finalproject.auth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttravel_finalproject.AdminUI.AdminsMainActivity;
import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.UserUI.UserMainActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ActionBar actionBar;
    EditText email;
    EditText password;
    Button loginadmin;
    Button loginuser;
    TextView signup;
    FirebaseAuth auth;
    TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.tv_createaccount);
        actionBar = getSupportActionBar();
        actionBar.hide();

        email=findViewById(R.id.ed_email_login);
        password=findViewById(R.id.ed_password_login);
        loginadmin=findViewById(R.id.btn_adminLogin);
        loginuser=findViewById(R.id.btn_userLogin);
        auth = FirebaseAuth.getInstance();
        forgetPassword=findViewById(R.id.tv_forget_password);

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task<AuthResult> result=auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString());
                result.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent i=new Intent(getApplicationContext(), UserMainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this, "Faild", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task<AuthResult> result=auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString());
                result.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent i=new Intent(getApplicationContext(), AdminsMainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this, "Faild", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Signup.class);
                startActivity(i);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(i);
            }
        });
    }
}
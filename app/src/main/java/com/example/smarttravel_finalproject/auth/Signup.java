package com.example.smarttravel_finalproject.auth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smarttravel_finalproject.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signup extends AppCompatActivity {
    ActionBar actionBar;
    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText phone;
    Button signup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        actionBar = getSupportActionBar();
        actionBar.hide();
        first_name=findViewById(R.id.ed_fname);
        last_name=findViewById(R.id.ed_lname);
        email=findViewById(R.id.ed_email_signup);
        password=findViewById(R.id.ed_password);
        phone=findViewById(R.id.ed_phone);
        signup=findViewById(R.id.btn_signup);
        auth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress=email.getText().toString();
                String pass=password.getText().toString();
                if(emailAddress.equals("")){
                    Log.d("mm","email null");
                }
                if(pass.equals("")){
                    Log.d("mm","pass null");
                }
                Task<AuthResult> result=auth.createUserWithEmailAndPassword(emailAddress,pass);
                Log.d("mm","hello1");
                result.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent i=new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        Log.d("mm","hello2");
                    }else{
                        Toast.makeText(Signup.this, "Faild Signup!", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });
    }
    private void updateUserProfile(FirebaseUser user) {
        // TODO
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(first_name.getText().toString())
                .setPhotoUri(Uri.parse("https//www.google.com/photo"))
                .build();

        Task<Void> result = user.updateProfile(request);

        result.addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }else{
                Log.d("DDDD" , " Error on update profile: " + task.getException().getLocalizedMessage());
            }
        });
    }

}
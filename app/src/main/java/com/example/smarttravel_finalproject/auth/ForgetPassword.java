package com.example.smarttravel_finalproject.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.UserUI.UserMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ForgetPassword extends AppCompatActivity {
    ActionBar actionBar;
    EditText phone,code;
    Button sendCode,verifyCode;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        actionBar = getSupportActionBar();
        actionBar.hide();

        phone=findViewById(R.id.ed_phonenum);
        code=findViewById(R.id.ed_codenum);
        sendCode=findViewById(R.id.btn_sendCode);
        verifyCode=findViewById(R.id.btn_verifycode);
        sendCode.setOnClickListener(view -> {

            PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                    .setPhoneNumber(phone.getText().toString())
                    .setActivity(ForgetPassword.this)
                    .setCallbacks(callback)
                    .setTimeout(30L, TimeUnit.SECONDS)
                    .build();

            PhoneAuthProvider.verifyPhoneNumber(options);
        });

        verifyCode.setOnClickListener(view -> {
            String codeNum = code.getText().toString();


            AuthCredential phoneCredential = PhoneAuthProvider.getCredential(verificationId ,codeNum );

            auth.signInWithCredential(phoneCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ForgetPassword.this, "Verifed Successfuly", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(), UserMainActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(ForgetPassword.this, "Verifed faild, Try again!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            //Go to home
            Log.d("DDDD", "PhoneVerification: onVerificationCompleted");
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(ForgetPassword.this, "Verified code faild!", Toast.LENGTH_SHORT).show();
            Log.d("DDDD", "PhoneVerification: onVerificationFailed " + e.getLocalizedMessage());

        }


        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);


            ForgetPassword.this.verificationId = s;
        }
    };


    private String verificationId = "";
}
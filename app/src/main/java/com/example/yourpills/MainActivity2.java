package com.example.yourpills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    EditText phone,otp;
    Button btngenerateOTP, btnverifyOTP;
    FirebaseAuth mAuth;

    String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);
        btngenerateOTP = findViewById(R.id.btngenerateOTP);
        btnverifyOTP = findViewById(R.id.btnverifyOTP);
        mAuth = FirebaseAuth.getInstance();

        btngenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText().toString()))
                {
                    Toast.makeText(MainActivity2.this, "Preencha com um numero real", Toast.LENGTH_SHORT).show();
                }else {
                    String phoneNumber = phone.getText().toString();
                    sendVerificationCode(phoneNumber);
                }
                ;
            }
        });

        btnverifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(otp.getText().toString()))
                {
                    Toast.makeText(MainActivity2.this, "Escreva o número certo", Toast.LENGTH_SHORT).show();
                }
                else {
                    VerificationCode(otp.getText().toString());
                }
            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+351" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code!=null){
                VerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity2.this, "Verificação falhou!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationId = s;
        }
    };

    private void VerificationCode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, Code);
        singinbyCredentials(credential);
    }

    private void singinbyCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity2.this, "Entrou com Sucesso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity2.this, HomeActivity.class));
                        }
                    }
                });
    }
}
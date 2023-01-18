package com.example.yourpills;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

        public static final String TAG = "TAG";
        private EditText musername, memail, mtelemovel, midade, mpassword, mrepassword;
        private TextView voltar;
        private Button btnsign;
        FirebaseAuth mAuth;
        FirebaseFirestore fstore;
        String usersID;

        @Override
        public void onStart() {
            super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            mAuth = FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();
            musername = (EditText) findViewById(R.id.username1);
            memail = (EditText) findViewById(R.id.conta1);
            mtelemovel = (EditText) findViewById(R.id.numero1);
            midade = (EditText) findViewById(R.id.idade) ;
            mpassword = (EditText) findViewById(R.id.password1);
            mrepassword= (EditText) findViewById(R.id.repassword1);
            btnsign = (Button) findViewById(R.id.btnsingup1);
            voltar = (TextView) findViewById(R.id.voltar);

            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

            btnsign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String nome, conta, numero, idade, password2, repassword;

                    nome = (musername.getText().toString().trim());
                    conta = (memail.getText().toString().trim());
                    numero = (mtelemovel.getText().toString().trim());
                    idade= (midade.getText().toString().trim());
                    password2 = (mpassword.getText().toString().trim());
                    repassword = (mrepassword.getText().toString().trim());

                    if (nome.isEmpty()) {
                        musername.setError("Escreva seu nome");
                        musername.requestFocus();
                        return;
                    }

                    if (conta.isEmpty()){
                        memail.setError("Escreva o seu email");
                        memail.requestFocus();
                        return;
                    }

                    if(numero.isEmpty()){
                        mtelemovel.setError("Insira seu número");
                        mtelemovel.requestFocus();
                        return;
                    }

                    if(idade.isEmpty()){
                        midade.setError("Sua idade porfavor");
                        midade.requestFocus();
                        return;
                    }

                    if(password2.isEmpty()){
                        mpassword.setError("Introduza uma palavra passe");
                        mpassword.requestFocus();
                        return;
                    }

                    if (password2.length() < 6){
                        mpassword.setError("A palavra passe deve ter mais que 6 caracter");
                        mpassword.requestFocus();
                        return;
                    }

                    String email = memail.getText().toString();
                    String password = mpassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        user user = new user(nome, conta, numero, idade);
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                                        if (user.isEmailVerified()){
                                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }else{
                                                            user.sendEmailVerification();
                                                            Toast.makeText(RegisterActivity.this, "Verifique o seu email para verificar o seu email", Toast.LENGTH_SHORT).show();
                                                        }

                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(RegisterActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();

                                                        }else{
                                                            Toast.makeText(RegisterActivity.this, "Erro! tente de novo!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Erro ao registrar!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            })

            ;}

        ;}

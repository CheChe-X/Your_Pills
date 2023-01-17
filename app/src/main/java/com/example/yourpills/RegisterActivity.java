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
import com.google.firebase.firestore.DocumentReference;
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


                    String username2, conta, numero, idade2, password2, repassword2;

                    username2 = String.valueOf(musername.getText());
                    conta = String.valueOf(memail.getText());
                    numero = String.valueOf(mtelemovel.getText());
                    idade2= String.valueOf(midade.getText());
                    password2 = String.valueOf(mpassword.getText());
                    repassword2 = String.valueOf(mrepassword.getText());

                    if (TextUtils.isEmpty(conta)) {
                        Toast.makeText(RegisterActivity.this, "Escreva o seu Email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password2)){
                        Toast.makeText(RegisterActivity.this, "Introduza a sua Palavra Passe ", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password2.length() < 6){
                        mpassword.setError("A palavra passe deve ter mais que 6 caracter");
                    }

                    String email = memail.getText().toString();
                    String password = mpassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Conta criada.",
                                                Toast.LENGTH_SHORT).show();
                                        usersID = mAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fstore.collection("users").document(usersID);
                                        Map<String,Object> user = new HashMap<>();
                                        user.put("nome", musername);
                                        user.put("email", memail);
                                        user.put("numero_telemovel", mtelemovel);
                                        user.put("idade", midade);
                                        user.put("password", mpassword);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Log.d(TAG, "OnSucess: Perfil de usuário criado "+ usersID);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "OnFailure: "+ e.toString());
                                            }
                                        });
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Erro.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            })

            ;}

        ;}

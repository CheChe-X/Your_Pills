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
        //estes códigos são referencias aos campos do layout
        private EditText musername, memail, mtelemovel, midade, mpassword, mrepassword;
        private TextView voltar;
        private Button btnsign;
        //este código cria uma instancia da FirebaseAuth
        FirebaseAuth mAuth;
        //com este código cria um instancia da FirebaseFirestore e com ela tenho acesso a base de dados
        FirebaseFirestore fstore;
        String usersID;

        //este codigo vai verificar se o utilizador está com conta já conectada
        //se ele tiver conectado o utilizador continua na página principal senão tiver vai para a página de login
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
            //estes códigos iram procurar no layout o id das variaveis
            musername = (EditText) findViewById(R.id.username1);
            memail = (EditText) findViewById(R.id.conta1);
            mtelemovel = (EditText) findViewById(R.id.numero1);
            midade = (EditText) findViewById(R.id.idade) ;
            mpassword = (EditText) findViewById(R.id.password1);
            mrepassword= (EditText) findViewById(R.id.repassword1);
            btnsign = (Button) findViewById(R.id.btnsingup1);
            voltar = (TextView) findViewById(R.id.voltar);

            //volta a página anterior ou seja ao login
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

            //com este código aqui vou dar a função ao botao btnsing de criar uma conta de utilizador
            btnsign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //a criação das strings
                    String nome, conta, numero, idade, password2, repassword;
                    //está linha define as variaveis que são armazenas na criação do utilizador
                    nome = (musername.getText().toString().trim());
                    conta = (memail.getText().toString().trim());
                    numero = (mtelemovel.getText().toString().trim());
                    idade= (midade.getText().toString().trim());
                    password2 = (mpassword.getText().toString().trim());
                    repassword = (mrepassword.getText().toString().trim());

                    //este if vai fazer com que quando não escrever no campo lhe vai aparecer uma mensagem de erro
                    if (nome.isEmpty()) {
                        musername.setError("Escreva seu nome");
                        musername.requestFocus();
                        return;
                    }

                    //este if vai fazer com que quando não escrever no campo lhe vai aparecer uma mensagem de erro
                    if (conta.isEmpty()){
                        memail.setError("Escreva o seu email");
                        memail.requestFocus();
                        return;
                    }

                    //este if vai fazer com que quando não escrever no campo lhe vai aparecer uma mensagem de erro
                    if(numero.isEmpty()){
                        mtelemovel.setError("Insira seu número");
                        mtelemovel.requestFocus();
                        return;
                    }

                    //este if vai fazer com que quando não escrever no campo lhe vai aparecer uma mensagem de erro
                    if(idade.isEmpty()){
                        midade.setError("Sua idade porfavor");
                        midade.requestFocus();
                        return;
                    }

                    //este if vai fazer com que quando não escrever no campo lhe vai aparecer uma mensagem de erro
                    if(password2.isEmpty()){
                        mpassword.setError("Introduza uma palavra passe");
                        mpassword.requestFocus();
                        return;
                    }

                    //este if vai obrigar a pessoa a ter uma palavra passe maior que 6 caracters
                    if (password2.length() < 6) {
                        mpassword.setError("A palavra passe deve ter mais que 6 caracter");
                        mpassword.requestFocus();
                        return;
                    }

                    String email = memail.getText().toString();
                    String password = mpassword.getText().toString();
                    //está linha de código tem o método createUser da Firebase para assim criar uma conta
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

                                                        //este código serve para mandar um email de verificação ao utilizador
                                                        if (user.isEmailVerified()){
                                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }else{
                                                            user.sendEmailVerification();
                                                            //mensagem para avisar
                                                            Toast.makeText(RegisterActivity.this, "Verifique o seu email para verificar o seu email", Toast.LENGTH_SHORT).show();
                                                        }

                                                        if (task.isSuccessful()) {
                                                            //mensagem de sucesso
                                                            Toast.makeText(RegisterActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();

                                                        }else{
                                                            //mensagem de erro
                                                            Toast.makeText(RegisterActivity.this, "Erro! tente de novo!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }else{
                                        //mensagem de erro
                                        Toast.makeText(RegisterActivity.this, "Erro ao registrar!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            })

            ;}

        ;}

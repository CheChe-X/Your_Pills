package com.example.yourpills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityManagerCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    //estes códigos são referencias aos campos do layout
    private EditText memail, mpassword;
    private Button singin, user_telemovel;
    private TextView singup, resetpassword,voltar;

    //este código cria uma instancia da FirebaseAuth
    private FirebaseAuth mAuth;

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
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //estes códigos iram procurar no layout o id das variaveis
        memail = (EditText) findViewById(R.id.conta);
        mpassword = (EditText) findViewById(R.id.password);
        singin = (Button) findViewById(R.id.btnlogin);
        singup = (TextView) findViewById(R.id.btnsingup);
        resetpassword = (TextView) findViewById(R.id.resetpassword);
        voltar = (TextView) findViewById(R.id.voltar4);

        user_telemovel = findViewById(R.id.userphone);

        user_telemovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        //este código vai servir para dar ordens ao botão singin
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //a criacão das strings
                String email, password;
                email = (memail.getText().toString().trim());
                password = (mpassword.getText().toString().trim());

                //está parte vai fazer com que se o utilizador não escrever nada no campo do email vai aparecer uma mensagem de erro
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Insira o seu Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Insira a sua Palavra Passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                //e isto vai fazer com que o utilizador entre com a sua conta na aplicação
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //e se o utilizadore entrar vai aparecer uma mensagem
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Entrou na sua conta", Toast.LENGTH_SHORT).show();
                                    //este código vai levar o utilizador para a página principal
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    //está linha de código vai mostrar uma mensagem de erro
                                    Toast.makeText(MainActivity.this, "Erro.",
                                            Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });

        //este código vai fazer com que quando clique no botão vá para a página de registro
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //este código vai fazer com que quando clique no botão para ir a página de resetar a palavra passe
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        //se diz voltar não vale a pena explicar (volta a página principal)
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
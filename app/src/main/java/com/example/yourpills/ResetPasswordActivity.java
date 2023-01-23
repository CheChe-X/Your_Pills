package com.example.yourpills;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import java.util.regex.PatternSyntaxException;

public class ResetPasswordActivity extends AppCompatActivity {

    //estes códigos são referencias aos campos do layout
    private EditText conta;
    private Button resetpassword;
    private TextView voltar;
    //este código cria uma instancia da FirebaseAuth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();
        //estes códigos iram procurar no layout o id das variaveis
        conta = (EditText) findViewById(R.id.conta1);
        resetpassword = (Button) findViewById(R.id.resetpassword1);
        voltar = (TextView) findViewById(R.id.voltar5);

        //funções para o botão resetpassword
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = conta.getText().toString().trim();

                //se o campo estiver vazio ele manda uma mensagem de erro
                if(email.isEmpty()){
                    conta.setError("Inserir o email é necesserario!");
                    conta.requestFocus();
                    return;
                }

                //se o email não existir ele manda está mensagem de erro
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    conta.setError("Insira um email valido!");
                    conta.requestFocus();
                    return;
                }

                //este código utiliza a função sendPasswordResetEmail da firebase para enviar um email para o utilizador
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            //se o email for enviando com sucesso aparece está mensagem
                            Toast.makeText(ResetPasswordActivity.this, "Verifique o seu email!", Toast.LENGTH_SHORT).show();
                        }else{
                            //mensagem de erro
                            Toast.makeText(ResetPasswordActivity.this, "Algo deu errado! Tente novamente!", Toast.LENGTH_SHORT).show();
                        }

                        //manda o utilizador para a página de login
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        //acho que já ta claro o que não é
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
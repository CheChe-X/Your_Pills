package com.example.yourpills;

import static com.example.yourpills.R.id.item1;
import static com.example.yourpills.R.id.item2;
import static com.example.yourpills.R.id.item3;
import static com.example.yourpills.R.id.item4;
import static com.example.yourpills.R.id.item5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ApagarActivity extends AppCompatActivity {

    //"apagarBTN" é uma variável que armazena um botão (Button)
    //"voltar" é uma variável que armazena outro botão (Button)
    private Button apagarBTN, voltar;

    //"nomeComprimido" é uma variável que armazena um campo de texto (EditText)
    private EditText nomeComprimido;

    //"db" é uma variável que armazena uma instância do banco de dados do Firestore
    private FirebaseFirestore db;
    
    //"bottom5" é uma variável que armazena uma instância do BottomNavigationView
    private BottomNavigationView bottom5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar);

        db = FirebaseFirestore.getInstance();
        //com este código ele vai buscar a referência do botão apagarBTN e vai atribuir ao apagarBTN
        apagarBTN = findViewById(R.id.apagarBTN);
        //com este código ele vai buscar a referência do botão nomeComprimido e vai atribuir ao nameinput
        nomeComprimido = findViewById(R.id.nameinput);
        //com este código ele vai buscar a referência do botão voltar e vai atribuir ao voltar3
        voltar = findViewById(R.id.voltar3);

        //com este código vai fazer com que quando o botão voltar for chamado ele vai chamar a página do ComprimidosActivity
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComprimidosActivity.class);
                startActivity(intent);
            }
        });

        //com este código ele vai buscar a referência do botão bottom5 e vai atribuir ao BottomMenu5
        bottom5 = findViewById(R.id.BottomMenu5);

        //com este código cada vez que uma pessoa seleciona na barra de navegação ele ira chamar a activity
        bottom5.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //este codigo vai chamar a MainActivity
                switch (item.getItemId()){
                    case item1:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
                //este vai chamar o HomeActivity
                switch (item.getItemId()){
                    case item2:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
                //este vai chamar o ComprimidosActivity
                switch (item.getItemId()){
                    case item3:
                        startActivity(new Intent(getApplicationContext(), ComprimidosActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
                //vai chamar a ReceitasActivity
                switch (item.getItemId()){
                    case item4:
                        startActivity(new Intent(getApplicationContext(), ReceitasActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
                //este codigo vai fazer com que quando o utilizador clique no botao sair vai fazer com que
                //a pessoa sai da conta e apareca uma mensagem a dizer saiu da sua conta
                switch (item.getItemId()){
                    case item5:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(ApagarActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

        //com este código vai fazer com que quando o botão voltar for chamado ele vai chamar a página do ComprimidosActivity
        apagarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nomeComprimido.getText().toString();
                nomeComprimido.setText("");
                DeleteData(nome);
            }
        });

    }


    private void DeleteData(String nome) {
        //Acessa a coleção "Comprimidos" do Firestore usando o objeto "db"
        db.collection("Comprimidos")
                //ele vai verificar se o campo nome é igual ao nome que foi introduzido
                .whereEqualTo("nome", nome)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            //o método usa o ID do documento para excluir o documento da coleção "Comprimidos"
                            db.collection("Comprimidos")
                                    .document(documentID)
                                    .delete()
                                    //se tudo correr bem vai aparecer uma mensagem a dizer "Apagado com Sucesso"
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ApagarActivity.this, "Apagado com Sucesso", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    //se ele nao tiver o nome igual por exemplo ele vai dar uma mensagem de Erro
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ApagarActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            //se for erro de sistema ele vai dar a mensagem "Um Erro Ocorreu
                        }else {
                            Toast.makeText(ApagarActivity.this, "Um Erro Ocorreu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
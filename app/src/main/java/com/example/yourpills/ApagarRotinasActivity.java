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

public class ApagarRotinasActivity extends AppCompatActivity {

    private Button apagar, voltar;
    private EditText nomerotina;
    private FirebaseFirestore db;
    private BottomNavigationView bottom7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_rotinas);
        apagar = findViewById(R.id.apagar);
        nomerotina = findViewById(R.id.nameinput2);
        db = FirebaseFirestore.getInstance();

        voltar = findViewById(R.id.voltar5);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HabaRotinasActivity.class);
                startActivity(intent);
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome_rotina = nomerotina.getText().toString();
                nomerotina.setText("");
                DeleteData(nome_rotina);
            }
        });

        bottom7 = findViewById(R.id.BottomMenu6);

        bottom7.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        Toast.makeText(ApagarRotinasActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

    }

    private void DeleteData(String nome_rotina) {
        db.collection("Rotinas")
                .whereEqualTo("nome", nome_rotina)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            db.collection("Rotinas")
                                    .document(documentID)
                                    .delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ApagarRotinasActivity.this, "Apagado com Sucesso", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ApagarRotinasActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }else {
                            Toast.makeText(ApagarRotinasActivity.this, "Um Erro Ocurreu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
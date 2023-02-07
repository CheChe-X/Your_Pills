package com.example.yourpills;

import static com.example.yourpills.R.id.item2;
import static com.example.yourpills.R.id.item3;
import static com.example.yourpills.R.id.item4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ComprimidosActivity extends AppCompatActivity {

    private RecyclerView view;
    private FloatingActionButton add;
    private ArrayList<comprimido> comprimidoArrayList;
    private CustomAdapter customAdapter;
    FirebaseFirestore db;
    private Button volta1;
    private BottomNavigationView BottomMenu2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimidos);

        comprimidoArrayList = new ArrayList<comprimido>();
        customAdapter = new CustomAdapter(comprimidoArrayList);
        // este código ira procurar no layout o id das variaveis
        view = findViewById(R.id.view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(customAdapter);
        add = findViewById(R.id.add);
        volta1 = (Button) findViewById(R.id.voltar1);
        BottomMenu2 = findViewById(R.id.BottomMenu2);
        BottomMenu2.setSelectedItemId(R.id.item2);
        BottomMenu2.setSelectedItemId(R.id.item3);
        BottomMenu2.setSelectedItemId(item4);

        //este código vai a Firebase Firestore e vai acessar a coleção comprimidos
        db = FirebaseFirestore.getInstance();
        //com o get ela vai recuperar todos os dados na coleção
        db.collection("Comprimidos").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                //com o list ela atribui todos os dados retornados
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                //e com o for ela vai iterar na lista e criar um objeto da classe comprimido
                                for (DocumentSnapshot d:list){
                                    comprimido obj = d.toObject(comprimido.class);
                                    //e ela adiciona cada objeto criado à coleção comprimido
                                    comprimidoArrayList.add(obj);
                                }
                                customAdapter.notifyDataSetChanged();
                            }
                        });

        //isto vai fazer com o view use o customAdapter para exibir a informaçáo necessaria
        view.setAdapter(customAdapter);

        //com este código cada vez que uma pessoa seleciona na barra de navegação ele ira iniciar a activity
        BottomMenu2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case item2:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }

                switch (item.getItemId()){
                    case item3:
                        startActivity(new Intent(getApplicationContext(), ComprimidosActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }

                switch (item.getItemId()){
                    case item4:
                        startActivity(new Intent(getApplicationContext(), ReceitasActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }

                return false;

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AdicionarCompActivity.class);
                startActivity(intent);
                finish();
            }
        });

        volta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

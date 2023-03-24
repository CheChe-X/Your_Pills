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


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ReceitasActivity extends AppCompatActivity {

    private RecyclerView view;
    private FloatingActionButton add;
    private ArrayList<receitas> receitasArrayList;
    private ReceitasAdapter receitasAdapter;
    FirebaseFirestore db;
    private BottomNavigationView BottomMenu1;
    private Button apagar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        receitasArrayList = new ArrayList<receitas>();
        receitasAdapter = new ReceitasAdapter(receitasArrayList);
        view = findViewById(R.id.view1);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(receitasAdapter);
        add = findViewById(R.id.add1);
        BottomMenu1 = findViewById(R.id.BottomMenu1);
        BottomMenu1.setSelectedItemId(R.id.item2);
        BottomMenu1.setSelectedItemId(R.id.item3);
        BottomMenu1.setSelectedItemId(item4);

        apagar = findViewById(R.id.apagar1);

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ApagarReceitasActivity.class);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(ReceitasActivity.this);
        progressDialog.setTitle("Carregar...");
        progressDialog.setMessage("As Receitas");

        progressDialog.show();
        db = FirebaseFirestore.getInstance();
        db.collection("Receitas").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d:list){
                                    receitas obj = d.toObject(receitas.class);
                                    receitasArrayList.add(obj);
                                }
                                receitasAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        });

        view.setAdapter(receitasAdapter);

        BottomMenu1.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case item1:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }

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

                switch (item.getItemId()){
                    case item5:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(ReceitasActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), AdicionarRecActivity.class);
                startActivity(intent);
            }
        });
    }
}
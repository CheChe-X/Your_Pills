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


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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


public class HabaRotinasActivity extends AppCompatActivity {

    private RecyclerView eventList;
    private FloatingActionButton add;
    private ArrayList<rotina> rotinaArrayList;
    private RotinaAdapter rotinaAdapter;
    FirebaseFirestore db;
    private BottomNavigationView BottomMenu3;
    private Button apagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haba_rotinas);

        rotinaArrayList = new ArrayList<rotina>();
        rotinaAdapter = new RotinaAdapter(rotinaArrayList);
        eventList = findViewById(R.id.eventList);
        eventList.setLayoutManager(new LinearLayoutManager(this));
        eventList.setAdapter(rotinaAdapter);
        apagar = findViewById(R.id.apagar_rotina);

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ApagarRotinasActivity.class);
                startActivity(intent);
            }
        });

        add = findViewById(R.id.add2);

        BottomMenu3 = findViewById(R.id.BottomMenu3);
        BottomMenu3.setSelectedItemId(item2);
        BottomMenu3.setSelectedItemId(item3);
        BottomMenu3.setSelectedItemId(item4);

        db = FirebaseFirestore.getInstance();

        db.collection("Rotinas").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list){
                            rotina obj = d.toObject(rotina.class);
                            rotinaArrayList.add(obj);
                        }
                        rotinaAdapter.notifyDataSetChanged();
                    }
                });

        eventList.setAdapter(rotinaAdapter);

        BottomMenu3.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        Toast.makeText(HabaRotinasActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rotinasActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
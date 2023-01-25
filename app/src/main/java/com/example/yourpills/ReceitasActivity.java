package com.example.yourpills;

import static com.example.yourpills.R.id.BottomMenu1;
import static com.example.yourpills.R.id.item2;
import static com.example.yourpills.R.id.item3;
import static com.example.yourpills.R.id.item4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ReceitasActivity extends AppCompatActivity {

    private RecyclerView view;
    private FloatingActionButton add;
    private ArrayList<receitas> receitasArrayList;
    private CustomAdapter2 customAdapter2;
    FirebaseFirestore db;

    private Button volta;
    private BottomNavigationView BottomMenu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        receitasArrayList = new ArrayList<receitas>();
        customAdapter2 = new CustomAdapter2(receitasArrayList);
        view = findViewById(R.id.view1);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(customAdapter2);
        volta = (Button) findViewById(R.id.voltar1);
        add = findViewById(R.id.add1);
        BottomMenu1 = findViewById(R.id.BottomMenu1);
        BottomMenu1.setSelectedItemId(R.id.item2);
        BottomMenu1.setSelectedItemId(R.id.item3);
        BottomMenu1.setSelectedItemId(item4);

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
                                customAdapter2.notifyDataSetChanged();
                            }
                        });

        view.setAdapter(customAdapter2);

        BottomMenu1.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case item2:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                }

                switch (item.getItemId()){
                    case item3:
                        startActivity(new Intent(getApplicationContext(), ComprimidosActivity.class));
                        overridePendingTransition(0,0);
                }

                switch (item.getItemId()){
                    case item4:
                        startActivity(new Intent(getApplicationContext(), ReceitasActivity.class));
                        overridePendingTransition(0,0);
                }

            }
        });



        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                startActivity(intent);
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
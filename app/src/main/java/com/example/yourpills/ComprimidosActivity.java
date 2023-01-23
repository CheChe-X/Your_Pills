package com.example.yourpills;


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
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ComprimidosActivity extends AppCompatActivity {

    private RecyclerView view;
    private FloatingActionButton add;
    private ArrayList<comprimido> comprimidoArrayList;
    private CustomAdapter customAdapter;
    private FirebaseFirestore db;
    private Button volta1;
    private BottomNavigationView BottomMenu2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimidos);

        view = findViewById(R.id.view);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        comprimidoArrayList = new ArrayList<comprimido>();
        customAdapter = new CustomAdapter(ComprimidosActivity.this,comprimidoArrayList);

        add = findViewById(R.id.add);
        volta1 = (Button) findViewById(R.id.voltar1);
        BottomMenu2 = findViewById(R.id.BottomMenu2);
        BottomMenu2.setSelectedItemId(R.id.item2);
        BottomMenu2.setSelectedItemId(R.id.item3);
        BottomMenu2.setSelectedItemId(item4);

        view.setAdapter(customAdapter);

        EventChangeListener();

        BottomMenu2.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
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

    private void EventChangeListener() {

        db.collection("Comprimidos").orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.e("Erro!", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                comprimidoArrayList.add(dc.getDocument().toObject(comprimido.class));
                            }
                            customAdapter.notifyDataSetChanged();

                        }

                    }
                });

    }

}
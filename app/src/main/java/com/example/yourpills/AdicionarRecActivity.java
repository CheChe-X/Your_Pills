package com.example.yourpills;

import static com.example.yourpills.R.id.item4;
import static com.example.yourpills.R.id.voltar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdicionarRecActivity extends AppCompatActivity {

    private Button voltar3 , inserir1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_rec);

        voltar3 = findViewById(R.id.voltar3);
        inserir1 = findViewById(R.id.inserir1);


        voltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReceitasActivity.class);
                startActivity(intent);
            }
        });
    }
}
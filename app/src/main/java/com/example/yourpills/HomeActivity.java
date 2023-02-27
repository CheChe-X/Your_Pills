package com.example.yourpills;

import static com.example.yourpills.CalendarioUtilities.daysInMonthArray;
import static com.example.yourpills.CalendarioUtilities.monthYearFromDate;
import static com.example.yourpills.R.id.item1;
import static com.example.yourpills.R.id.item2;
import static com.example.yourpills.R.id.item3;
import static com.example.yourpills.R.id.item4;
import static com.example.yourpills.R.id.item5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.ArrayList;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    FirebaseAuth auth;
    FirebaseUser user;
    private TextView mes_ano;
    private RecyclerView calendarRecyclerView;
    private Button semana;
    private BottomNavigationView BottomMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    private void iniWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarioRecycler);
        mes_ano = findViewById(R.id.mes_ano);

    }

    private void setMonthView() {
        mes_ano.setText(monthYearFromDate(CalendarioUtilities.selectData));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarioUtilities.selectData);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view){
        CalendarioUtilities.selectData = CalendarioUtilities.selectData.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view){
        CalendarioUtilities.selectData = CalendarioUtilities.selectData.plusMonths(1);
        setMonthView();
    }

    public void onItemClick(int position, LocalDate date) {
        if(date !=null) {
            CalendarioUtilities.selectData = date;
            setMonthView();
        }
    }

    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        // este código ira procurar no layout o id das variaveis
        semana = findViewById(R.id.semana);
        BottomMenu = findViewById(R.id.BottomMenu);
        BottomMenu.setSelectedItemId(item2);
        BottomMenu.setSelectedItemId(item3);
        BottomMenu.setSelectedItemId(item4);
        iniWidgets();
        CalendarioUtilities.selectData = LocalDate.now();
        setMonthView();

        user = auth.getCurrentUser();

        BottomMenu.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
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
                        Toast.makeText(HomeActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // e com este código a variavel rotina vai chamar a página RotinaActivity
        semana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WeekViewActivity.class);
                startActivity(intent);
            }
        });

    }

}



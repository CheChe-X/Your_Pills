package com.example.yourpills;

import static com.example.yourpills.CalendarioUtilities.daysInWeekArray;
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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView mes_ano;
    private RecyclerView calendarRecyclerView;
    private Object setWeekView;
    private BottomNavigationView BottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        iniWidgets();
        setWeekView();
        BottomMenu = findViewById(R.id.BottomMenu);

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
                        Toast.makeText(WeekViewActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void iniWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarioRecycler);
        mes_ano = findViewById(R.id.mes_ano);

    }

    private void setWeekView() {
        mes_ano.setText(monthYearFromDate(CalendarioUtilities.selectData));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarioUtilities.selectData);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousWeekAction(View view){
        CalendarioUtilities.selectData = CalendarioUtilities.selectData.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view){
        CalendarioUtilities.selectData = CalendarioUtilities.selectData.plusWeeks(1);
        setWeekView();
    }

    public void onItemClick(int position, LocalDate date) {
            CalendarioUtilities.selectData = date;
            setWeekView();
    }

}
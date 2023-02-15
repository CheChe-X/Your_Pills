package com.example.yourpills;

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
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements CustomAdapter3.OnItemListener {
    FirebaseAuth auth;
    FirebaseUser user;
    private TextView mes_ano;
    private String selectedDate;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectData;
    private Button bottom, bottom1, rotina;
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
        mes_ano.setText(monthYearFromDate(selectData));
        ArrayList<String> daysInMonth = daysInMonthArray(selectData);

        CustomAdapter3  customAdapter3 = new CustomAdapter3(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(customAdapter3);
    }

    private ArrayList<String> daysInMonthArray(LocalDate Data) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(Data);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectData.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= daysInMonth + dayOfWeek; i++){
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view){
        selectData = selectData.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view){
        selectData = selectData.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (dayText.equals("")){
            String message = "Selecione uma Data" + dayText + " " + monthYearFromDate(selectData);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        // este código ira procurar no layout o id das variaveis
        rotina = findViewById(R.id.rotina);
        BottomMenu = findViewById(R.id.BottomMenu);
        BottomMenu.setSelectedItemId(item2);
        BottomMenu.setSelectedItemId(item3);
        BottomMenu.setSelectedItemId(item4);
        iniWidgets();
        selectData = LocalDate.now();
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
        rotina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RotinaActivity.class);
                startActivity(intent);
            }
        });

    }

}



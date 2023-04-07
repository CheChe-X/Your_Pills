package com.example.yourpills;

import static com.example.yourpills.CalendarUtils.daysInMonthArray;
import static com.example.yourpills.CalendarUtils.monthYearFromDate;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    //este código é uma instância da classe FirebaseAuth, que é utilizada para autenticar o usuário e gerenciar as credenciais de autenticação
    FirebaseAuth auth;
    //este código é uma instância da classe FirebaseUser, que representa o usuário atualmente autenticado no aplicativo
    FirebaseUser user;
    //este código é uma instância da classe FirebaseFirestore, que é utilizada para interagir com o banco de dados Firestore do Firebase
    FirebaseFirestore db;
    //Declaração de uma variável do tipo TextView
    private TextView mes_ano;
    //declaração de uma variável do tipo RecyclerView
    private RecyclerView calendarRecyclerView;
    //Declaração de uma variável do tipo BottomNavigationView,
    private BottomNavigationView BottomMenu;

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.bottom_nav_menu, menu);
            iniWidgets();
            return true;
        }

    //este código é responsavel por inicializar as variáveis e widgets usados para mostrar o conteúdo do aplicativo
    private void iniWidgets() {
        //este código está a atribuir à variável
        calendarRecyclerView = findViewById(R.id.calendarioRecycler);

        mes_ano = findViewById(R.id.mes_ano);
    }

    //este código vai exibir um  calendário na interface do utilizador na app
    private void setMonthView() {
        mes_ano.setText(monthYearFromDate(CalendarUtils.selectDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectDate);

        //cria uma instância do objeto CalendarAdapter, que é responsavel por exibir os dias do calendário na interface do utilizador
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        //este código cria um novo GridLayoutManager para o RecyclerView "calendarRecyclerView" com 7 colunas, uma para cada dia da semana
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        //define o layout manager para o RecyclerView "calendarRecyclerView"
        calendarRecyclerView.setLayoutManager(layoutManager);
        //define o adapter para o RecyclerView "calendarRecyclerView", que exibirá os dias do mês na interface do utilizador na app
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //com este código sempre que clicarmos no botão o mês anterior ao mês que está a ser exibido será chamada e será exibido
    public void previousMonthAction(View view){
        CalendarUtils.selectDate = CalendarUtils.selectDate.minusMonths(1);
        setMonthView();
    }

    //com este código sempre que clicarmos no botão o proximo mês será chamado mês
    public void nextMonthAction(View view){
        CalendarUtils.selectDate = CalendarUtils.selectDate.plusMonths(1);
        setMonthView();
    }

    //este código faz com que o utilizador quando clica no dia ele seja clicado
    public void onItemClick(int position, LocalDate date) {
        if(date !=null) {
            CalendarUtils.selectDate = date;
            setMonthView();
        }
    }

    protected void OnResume(){
        super.onResume();

    }

    @SuppressLint({"MissingInflatedId", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        // este código ira procurar no layout o id das variaveis
        BottomMenu = findViewById(R.id.BottomMenu);
        BottomMenu.setSelectedItemId(item2);
        BottomMenu.setSelectedItemId(item3);
        BottomMenu.setSelectedItemId(item4);

        //a classe iniWidgets que é chamada quando abrimos a página HomeActivity
        iniWidgets();
        //este código define a data atual como data no calendário
        CalendarUtils.selectDate = LocalDate.now();
        //é responsável por configurar a exibição do calendário com base na data selecionada
        setMonthView();

        //obtém o usuário atualmente autenticado no Firebase Auth
        user = auth.getCurrentUser();

        db = FirebaseFirestore.getInstance();


        //com este código cada vez que uma pessoa seleciona na barra de navegação ele ira chamar a activity
        BottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        Toast.makeText(HomeActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

    }

}



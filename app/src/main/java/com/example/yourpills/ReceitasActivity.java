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

    //declaração de uma variável do tipo RecyclerView
    private RecyclerView view;
    //declaração de uma variável do tipo FloatingActionButton
    private FloatingActionButton add;
    //Declaração de uma variável do tipo ArrayList que armazenará objetos do tipo "receita"
    private ArrayList<receitas> receitasArrayList;
    //Declaração de uma variável do tipo CustomAdapter que é uma classe personalizada responsável
    // por conectar o RecyclerView aos dados da lista
    private ReceitasAdapter receitasAdapter;
    //Declaração de uma variável do tipo FirebaseFirestore
    // que é a classe de banco de dados do Firebase Firestore
    FirebaseFirestore db;
    //Declaração de uma variável do tipo BottomNavigationView,
    private BottomNavigationView BottomMenu1;
    private Button apagar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        //esta linha cria uma novo Arraylist que armazena os objetos dos receitas
        receitasArrayList = new ArrayList<receitas>();
        //esta linha cria um customadapter
        receitasAdapter = new ReceitasAdapter(receitasArrayList);
        //este codigo vai localizar o recyclerview no layout
        view = findViewById(R.id.view1);
        //este codigo vai configurar o layoutmanager
        view.setLayoutManager(new LinearLayoutManager(this));
        //e este codigo vai defenir o recyclerview como o receitasAdapter
        view.setAdapter(receitasAdapter);
        //estas linhas de codigo vao localizar os views no layout
        add = findViewById(R.id.add1);
        BottomMenu1 = findViewById(R.id.BottomMenu1);
        BottomMenu1.setSelectedItemId(R.id.item2);
        BottomMenu1.setSelectedItemId(R.id.item3);
        BottomMenu1.setSelectedItemId(item4);

        apagar = findViewById(R.id.apagar1);

        //com este código vamos chamar a página ApagarReceitasActivity
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ApagarReceitasActivity.class);
                startActivity(intent);
            }
        });

        //com este código um loading vai ser exibido a dizer
        //A Carregar...
        //As Receitas
        progressDialog = new ProgressDialog(ReceitasActivity.this);
        progressDialog.setTitle("Carregar...");
        progressDialog.setMessage("As Receitas");

        progressDialog.show();
        //este código vai a Firebase Firestore e vai acessar a coleção comprimidos
        db = FirebaseFirestore.getInstance();
        //com o get ela vai recuperar todos os dados na coleção
        //e assim vai mostrar os comprimidos no recyclerview
        db.collection("Receitas").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                //com o list ela atribui todos os dados retornados
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                //e com o for ela vai iterar na lista e criar um objeto da classe receita
                                for (DocumentSnapshot d:list){
                                    receitas obj = d.toObject(receitas.class);
                                    //e ela adiciona cada objeto criado à coleção receita
                                    receitasArrayList.add(obj);
                                }
                                receitasAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        });
        //isto vai fazer com o view use o receitasAdapter para exibir a informaçáo necessaria
        view.setAdapter(receitasAdapter);

        //com este código cada vez que uma pessoa seleciona na barra de navegação ele ira chamar a activity
        BottomMenu1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        Toast.makeText(ReceitasActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

        //este botao com o intent vai iniciar a pagina adicionar
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), AdicionarRecActivity.class);
                startActivity(intent);
            }
        });
    }
}
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


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class ComprimidosActivity extends AppCompatActivity {

    //declaração de uma variável do tipo RecyclerView
        private RecyclerView view;
        //declaração de uma variável do tipo FloatingActionButton
        private FloatingActionButton add;
        //Declaração de uma variável do tipo ArrayList que armazenará objetos do tipo "comprimido"
        private ArrayList<comprimido> comprimidoArrayList;
        //Declaração de uma variável do tipo CustomAdapter que é uma classe personalizada responsável
        // por conectar o RecyclerView aos dados da lista
        private CustomAdapter customAdapter;
        //Declaração de uma variável do tipo FirebaseFirestore
        // que é a classe de banco de dados do Firebase Firestore
        FirebaseFirestore db;
        //Declaração de uma variável do tipo BottomNavigationView,
        private BottomNavigationView BottomMenu2;
        private Button apagar;
        private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprimidos);

        //esta linha cria uma novo Arraylist que armazena os objetos dos comprimidos
        comprimidoArrayList = new ArrayList<comprimido>();
        //esta linha cria um customadapter
        customAdapter = new CustomAdapter(comprimidoArrayList);
        //estes tres codigos vai localizar o recyclerview no layout
        view = findViewById(R.id.view);
        //este codigo vai configurar o layoutmanager
        view.setLayoutManager(new LinearLayoutManager(this));
        //e este codigo vai defenir o recyclerview como o customadapter
        view.setAdapter(customAdapter);
        //estas linhas de codigo vao localizar os views no layout
        add = findViewById(R.id.add);
        BottomMenu2 = findViewById(R.id.BottomMenu2);
        BottomMenu2.setSelectedItemId(R.id.item2);
        BottomMenu2.setSelectedItemId(R.id.item3);
        BottomMenu2.setSelectedItemId(item4);

        apagar = findViewById(R.id.apagar);

        //com este código vamos chamar a página apagaractivity
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ApagarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //com este código um loading vai ser exibido a dizer
        //A Carregar...
        //Os Comprimidos
        progressDialog = new ProgressDialog(ComprimidosActivity.this);
        progressDialog.setTitle("Carregar...");
        progressDialog.setMessage("Os Comprimidos");

        progressDialog.show();
        //este código vai a Firebase Firestore e vai acessar a coleção comprimidos
        db = FirebaseFirestore.getInstance();
        //com o get ela vai recuperar todos os dados na coleção
        //e assim vai mostrar os comprimidos no recyclerview
        db.collection("Comprimidos").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                //com o list ela atribui todos os dados retornados
                                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                //e com o for ela vai iterar na lista e criar um objeto da classe comprimido
                                for (DocumentSnapshot d:list){
                                    comprimido obj = d.toObject(comprimido.class);
                                    //e ela adiciona cada objeto criado à coleção comprimido
                                    comprimidoArrayList.add(obj);
                                }
                                customAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        });


        //isto vai fazer com o view use o customAdapter para exibir a informaçáo necessaria
        view.setAdapter(customAdapter);

        //com este código cada vez que uma pessoa seleciona na barra de navegação ele ira chamar a activity
        BottomMenu2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        Toast.makeText(ComprimidosActivity.this, "Saiu da sua conta", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;

            }
        });

        //este botao com o intent vai iniciar a pagina adicionar
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AdicionarCompActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}

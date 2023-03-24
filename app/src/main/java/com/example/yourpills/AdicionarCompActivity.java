package com.example.yourpills;

import static com.example.yourpills.R.id.BottomMenu2;
import static com.example.yourpills.R.id.item4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AdicionarCompActivity extends AppCompatActivity {

    //este codigo declara varios campos de texto
    private EditText name_comp, mil_comp, med_comp, emb_comp, data_comp;
    private DatePickerDialog.OnDateSetListener getmDateSetListener;
    //este codigo vai declarar dois botoes
    private Button inserir, voltar;
    //esta classe db vai representar a base de dados da FirebaseFirestore
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_comp);

        // este código ira procurar no layout o id das variaveis
        name_comp = findViewById(R.id.name_comp);
        mil_comp = findViewById(R.id.mil_comp);
        med_comp = findViewById(R.id.med_comp);
        emb_comp = findViewById(R.id.emb_comp);
        data_comp = findViewById(R.id.date_comp);
        inserir = findViewById(R.id.inserir);
        voltar = findViewById(R.id.voltar2);

        //este codigo de este botao vai chamar o ComprimidosAcitivity
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComprimidosActivity.class);
                startActivity(intent);
            }
        });

        data_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdicionarCompActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        getmDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //este codigo vai criar uma conexao com a base de dados da FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        //este código do botão inserir vai fazer com que
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //estás strings vão ser definidas como o texto que está contido nos objetos
                String compnome = name_comp.getText().toString();
                String compmiligramas = mil_comp.getText().toString();
                String compembalagens = emb_comp.getText().toString();
                String compmedicamentos = med_comp.getText().toString();
                String compdata = data_comp.getText().toString();
                //e depois um mapa é chamado com o nome comprimidodata em que ele é preenchido com as chaves
                //e assim com este mapa ele armazena os dados das strings
                Map<String, String> ComprimidoData = new HashMap<>();
                ComprimidoData.put("nome", compnome);
                ComprimidoData.put("miligramas", compmiligramas);
                ComprimidoData.put("medicamentos", compmedicamentos);
                ComprimidoData.put("embalagens", compembalagens);
                ComprimidoData.put("data", compdata);

                //com este codigo vai ser usado para adicionar os dados na coleção "Comprimido"
                db.collection("Comprimidos")
                        .add(ComprimidoData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //se os dados forem criados vai exibir uma mensagem a dizer criada
                                Toast.makeText(AdicionarCompActivity.this, "Criada", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                //e se caso deia um erro ele vai exibir este mensagem
                                Toast.makeText(AdicionarCompActivity.this, "Erro!" + error, Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }
}
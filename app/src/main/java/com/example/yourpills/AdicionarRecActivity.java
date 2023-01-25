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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdicionarRecActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private Button voltar , inserir;
    private EditText nome_receita, entidade_responsavel, especialidade, designacao_medicamento, forma_farmaceutica, n_utente, n_medico, numero_telemovel, n_beneficiario, contacto, dosagem, dimensao_embalagem, numero, extenso, validade, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_rec);

        nome_receita = findViewById(R.id.nome_receita);
        entidade_responsavel = findViewById(R.id.entidade_responsavel);
        especialidade = findViewById(R.id.especialidade);
        designacao_medicamento = findViewById(R.id.des_medicamento);
        forma_farmaceutica = findViewById(R.id.forma_farmaceutica);
        n_utente = findViewById(R.id.n_utente);
        n_medico = findViewById(R.id.n_medico);
        numero_telemovel = findViewById(R.id.telemovel1);
        n_beneficiario = findViewById(R.id.n_beneficiario);
        contacto = findViewById(R.id.contacto);
        dosagem = findViewById(R.id.dosagem);
        dimensao_embalagem = findViewById(R.id.dimensao_embalagem);
        numero = findViewById(R.id.numero);
        extenso = findViewById(R.id.Extenso);
        validade = findViewById(R.id.validade);
        data = findViewById(R.id.data);
        voltar = findViewById(R.id.voltar3);
        inserir = findViewById(R.id.inserir1);

        db = FirebaseFirestore.getInstance();

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receita_nome = nome_receita.getText().toString();
                String numero_utente = n_utente.getText().toString();
                String numero_medico = n_medico.getText().toString();
                String n_telemovel = numero_telemovel.getText().toString();
                String entidade = entidade_responsavel.getText().toString();
                String beneficiario = n_beneficiario.getText().toString();
                String especialidade1 = especialidade.getText().toString();
                String contacto1 = contacto.getText().toString();
                String designacao = designacao_medicamento.getText().toString();
                String dosagem1 = dosagem.getText().toString();
                String forma = forma_farmaceutica.getText().toString();
                String dimensao = dimensao_embalagem.getText().toString();
                String numero1 = numero.getText().toString();
                String extenso1 = extenso.getText().toString();
                String validade1 = validade.getText().toString();
                String data1 = data.getText().toString();

                Map<String, String> ReceitaData = new HashMap<>();
                ReceitaData.put("nome", receita_nome);
                ReceitaData.put("n_utente", numero_utente);
                ReceitaData.put("n_medico", numero_medico);
                ReceitaData.put("telemovel", n_telemovel);
                ReceitaData.put("entidade_responsavel", entidade);
                ReceitaData.put("beneficiario", beneficiario);
                ReceitaData.put("especialidade", especialidade1);
                ReceitaData.put("contacto", contacto1);
                ReceitaData.put("designação_medicamento", designacao);
                ReceitaData.put("dosagem", dosagem1);
                ReceitaData.put("forma_farmaceutica", forma);
                ReceitaData.put("dimensao_embalagem", dimensao);
                ReceitaData.put("numero", numero1);
                ReceitaData.put("extenso", extenso1);
                ReceitaData.put("validade", validade1);
                ReceitaData.put("data", data1);

                db.collection("Receitas")
                        .add(ReceitaData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AdicionarRecActivity.this, "Criada!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AdicionarRecActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReceitasActivity.class);
                startActivity(intent);
            }
        });
    }
}
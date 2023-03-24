package com.example.yourpills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AtualizarCompActivity extends AppCompatActivity {

    EditText antes_comp, novo_comp, antes_mil, novo_mil, antes_emb, novo_emb, antes_med, novo_med, antes_data, nova_data1;
    Button upgradeBTN;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizarcomp);

        antes_comp = findViewById(R.id.antes_comp);
        novo_comp = findViewById(R.id.novo_comp);
        antes_mil = findViewById(R.id.antes_mil);
        novo_mil = findViewById(R.id.novo_mil);
        antes_emb = findViewById(R.id.antes_emb);
        novo_emb = findViewById(R.id.novo_emb);
        antes_med = findViewById(R.id.antes_med);
        novo_med = findViewById(R.id.novo_med);
        antes_data = findViewById(R.id.antes_data);
        nova_data1 = findViewById(R.id.nova_data1);

        db = FirebaseFirestore.getInstance();

        upgradeBTN = findViewById(R.id.updateBTN);

        upgradeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String antescomp = antes_comp.getText().toString();
                String compnome = novo_comp.getText().toString();
                String antesmiligramas = antes_mil.getText().toString();
                String compmiligramas = novo_mil.getText().toString();
                String antesembalagens = antes_emb.getText().toString();
                String compembalagens = novo_emb.getText().toString();
                String antesmedicamentos = antes_med.getText().toString();
                String compmedicamentos = novo_med.getText().toString();
                String antesdata = antes_data.getText().toString();
                String compdata = nova_data1.getText().toString();
                antes_comp.setText("");
                novo_comp.setText("");
                antes_mil.setText("");
                novo_mil.setText("");
                antes_emb.setText("");
                novo_emb.setText("");
                antes_med.setText("");
                novo_med.setText("");
                antes_data.setText("");
                nova_data1.setText("");

                atualizarData(antescomp, compnome, antesmiligramas, compmiligramas, antesembalagens, compembalagens, antesmedicamentos, compmedicamentos, antesdata, compdata);
            }
        });

    }

    private void atualizarData(String antescomp, String compnome, String antesmiligramas, String compmiligramas, String antesembalagens, String compembalagens, String antesmedicamentos, String compmedicamentos, String antesdata, String compdata) {
        Map<String, Object> comprimidoDetails = new HashMap<>();
        comprimidoDetails.put("nome", compnome);
        comprimidoDetails.put("miligramas", compmiligramas);
        comprimidoDetails.put("embalagens", compembalagens);
        comprimidoDetails.put("medicamentos", compmedicamentos);
        comprimidoDetails.put("data", compdata);

        db.collection("Comprimidos")
                .whereEqualTo("nome", antescomp)
                .whereEqualTo("miligramas", antesmiligramas)
                .whereEqualTo("embalagens", antesembalagens)
                .whereEqualTo("medicamentos", antesmedicamentos)
                .whereEqualTo("data", antesdata)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            db.collection("Comprimido")
                                    .document(documentID)
                                    .update(comprimidoDetails)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(AtualizarCompActivity.this, "Atualizado com Sucesso", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AtualizarCompActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }else {
                            Toast.makeText(AtualizarCompActivity.this, "Falhou", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
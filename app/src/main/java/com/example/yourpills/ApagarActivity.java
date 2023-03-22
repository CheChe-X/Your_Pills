package com.example.yourpills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ApagarActivity extends AppCompatActivity {

    Button apagarBTN;
    EditText nomeComprimido;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar);
        db = FirebaseFirestore.getInstance();
        apagarBTN = findViewById(R.id.apagarBTN);
        nomeComprimido = findViewById(R.id.nameinput);

        apagarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nomeComprimido.getText().toString();
                nomeComprimido.setText("");
                DeleteData(nome);
            }
        });

    }

    private void DeleteData(String nome) {
        db.collection("Comprimidos")
                .whereEqualTo("nome", nome)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            db.collection("Comprimidos")
                                    .document(documentID)
                                    .delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ApagarActivity.this, "Apagado com Sucesso", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ApagarActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }else {
                            Toast.makeText(ApagarActivity.this, "Um Erro Ocurreu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
package com.example.yourpills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class rotinasActivity extends AppCompatActivity {

    private static final String TAG = "rotinasActivity";
    private EditText nome_rotina;
    private TextView timepicker;
    private DatePickerDialog.OnDateSetListener getmDateSetListener,mDateSetListener;
    private TimePickerDialog timePickerDialog;
    private Button criar_rotina, voltar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotina);
        nome_rotina = (EditText) findViewById(R.id.nome_rotina);
        timepicker = findViewById(R.id.timepicker);

        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(rotinasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timepicker.setText(hourOfDay + ":" + minute);
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });

        criar_rotina = (Button) findViewById(R.id.criar_rotina);
        voltar = (Button) findViewById(R.id.voltar7);

        db = FirebaseFirestore.getInstance();

        criar_rotina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome_rotina = nome_rotina.getText().toString();
                String Hora = timepicker.getText().toString();

                Map<String , String> RotinasData = new HashMap<>();
                RotinasData.put("nome", Nome_rotina);
                RotinasData.put("hora", Hora);

                db.collection("Rotinas")
                        .add(RotinasData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //se os dados forem criados vai exibir uma mensagem a dizer criada
                                Toast.makeText(rotinasActivity.this, "Criado", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                //e se caso deia um erro ele vai exibir este mensagem
                                Toast.makeText(rotinasActivity.this, "Erro!" + error, Toast.LENGTH_SHORT).show();

                            }
                        });

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), HabaRotinasActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });


    }
}
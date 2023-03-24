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
    private EditText nome_rotina, timepicker, startDate, finalDate;
    private DatePickerDialog.OnDateSetListener getmDateSetListener,mDateSetListener;
    private TimePickerDialog timePickerDialog;
    private Button criar_rotina, voltar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotina);
        startDate = findViewById(R.id.starDate);


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        rotinasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        getmDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        getmDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                startDate.setText(date);
            }
        };
        nome_rotina = (EditText) findViewById(R.id.nome_rotina);

        finalDate = findViewById(R.id.finalDate);

        finalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        rotinasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                finalDate.setText(date);
            }
        };

        timepicker = (EditText) findViewById(R.id.timepicker);

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
                String StartDate = startDate.getText().toString();
                String FinalDate = finalDate.getText().toString();
                String Hora = timepicker.getText().toString();

                Map<String , String> RotinasData = new HashMap<>();
                RotinasData.put("nome", Nome_rotina);
                RotinasData.put("data de inicio", StartDate);
                RotinasData.put("data final", FinalDate);
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
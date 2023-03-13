package com.example.yourpills;

import com.google.firebase.database.PropertyName;

public class rotina {
    @PropertyName("nome")
    public String nome;
    @PropertyName("data de inicio")
    public String data_de_inicio;
    @PropertyName("data final")
    public String data_final;
    @PropertyName("hora")
    public String hora;
    public rotina(){
        super();
    }
    public rotina(String nome, String data_de_inicio, String data_final, String hora){
        this.nome = nome;
        this.data_de_inicio= data_de_inicio;
        this.data_final = data_final;
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_de_inicio() {
        return data_de_inicio;
    }

    public void setData_de_inicio(String data_de_inicio) {
        this.data_de_inicio = data_de_inicio;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String finalDate) {
        this.data_final = finalDate;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

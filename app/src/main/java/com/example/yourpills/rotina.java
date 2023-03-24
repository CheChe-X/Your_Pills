package com.example.yourpills;

import com.google.firebase.database.PropertyName;

public class rotina {
    @PropertyName("nome")
    public String nome;
    @PropertyName("datadeinicio")
    public String datadeinicio;
    @PropertyName("datafinal")
    public String datafinal;
    @PropertyName("hora")
    public String hora;
    public rotina(){
        super();
    }
    public rotina(String nome, String datadeinicio, String datafinal, String hora){
        this.nome = nome;
        this.datadeinicio= datadeinicio;
        this.datafinal = datafinal;
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

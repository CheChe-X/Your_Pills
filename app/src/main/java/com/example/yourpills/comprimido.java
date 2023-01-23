package com.example.yourpills;

public class comprimido {

    public String nome;
    public long miligramas, embalagens, medicamentos, data;

    public comprimido(){

    }

    public comprimido(String nome, long miligramas, long embalagens, long medicamentos, long data){
        this.nome = nome;
        this.miligramas = miligramas;
        this.medicamentos = medicamentos;
        this.embalagens = embalagens;
        this.data = data;
    }
}

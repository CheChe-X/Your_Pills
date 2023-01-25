package com.example.yourpills;

import androidx.annotation.NonNull;

import java.util.Date;

public class comprimido {

    public String nome, embalagens, miligramas, medicamentos, data;


    public comprimido(){
        super();
    }

    public comprimido(String nome, String miligramas, String embalagens, String medicamentos, String data){
        this.nome = nome;
        this.miligramas = miligramas;
        this.medicamentos = medicamentos;
        this.embalagens = embalagens;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMiligramas() {
        return miligramas;
    }

    public void setMiligramas(String miligramas) {
        this.miligramas = miligramas;
    }

    public String getEmbalagens() {
        return embalagens;
    }

    public void setEmbalagens(String embalagens) {
        this.embalagens = embalagens;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

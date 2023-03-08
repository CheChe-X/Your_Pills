package com.example.yourpills;

public class rotina {
    public String nome_rotina, startDate, finalDate, hour;

    public String getNome_rotina() {
        return nome_rotina;
    }

    public void setNome_rotina(String nome_rotina) {
        this.nome_rotina = nome_rotina;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public rotina(String nome_rotina, String startDate, String finalDate, String hour) {
        this.nome_rotina = nome_rotina;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.hour = hour;
    }
}

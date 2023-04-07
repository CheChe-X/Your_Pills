package com.example.yourpills;

import androidx.annotation.NonNull;

import java.util.Date;
//com este código vamos fazer uma classe java chamada receita
public class receitas {
    //e a classe receita vai ter todas estas variaveis:
    public String nome_receita, entidade_responsavel, especialidade, designacao_medicamento, forma_farmaceutica,
            n_utente, n_medico, numero_telemovel, n_beneficiario, contacto, dosagem, dimensao_embalagem, numero,
            extenso, validade, data;

    public receitas(){
        super();
    }

    //e estas aqui serao todas as suas variaveis
    public receitas(String nome_receita, String entidade_responsavel, String especialidade, String designacao_medicamento,
                    String forma_farmaceutica, String n_utente, String n_medico, String numero_telemovel, String n_beneficiario,
                    String contacto, String dosagem, String dimensao_embalagem, String numero, String extenso, String validade,
                    String data){

        this.contacto = contacto;
        this.data = data;
        this.designacao_medicamento = designacao_medicamento;
        this.dimensao_embalagem = dimensao_embalagem;
        this.dosagem = dosagem;
        this.entidade_responsavel = entidade_responsavel;
        this.especialidade = especialidade;
        this.extenso = extenso;
        this.forma_farmaceutica = forma_farmaceutica;
        this.n_medico = n_medico;
        this.validade = validade;
        this.nome_receita = nome_receita;
        this.n_utente = n_utente;
        this.numero_telemovel = numero_telemovel;
        this.n_beneficiario = n_beneficiario;
        this.numero = numero;
    }

    //neste código temos o métodos de acesso "getters" e métodos de atribuição "setters"
    //em que um dos métodos vai retornar o valor atual de uma variável e o outro vai ser o que define um novo valor
    //e assim estes métodos permitem que outras classes tenham acesso e possam modificar

    public String getNome_receita() {
        return nome_receita;
    }

    public void setNome_receita(String nome_receita) {
        this.nome_receita = nome_receita;
    }

    public String getEntidade_responsavel() {
        return entidade_responsavel;
    }

    public void setEntidade_responsavel(String entidade_responsavel) {
        this.entidade_responsavel = entidade_responsavel;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDesignacao_medicamento() {
        return designacao_medicamento;
    }

    public void setDesignacao_medicamento(String designacao_medicamento) {
        this.designacao_medicamento = designacao_medicamento;
    }

    public String getForma_farmaceutica() {
        return forma_farmaceutica;
    }

    public void setForma_farmaceutica(String forma_farmaceutica) {
        this.forma_farmaceutica = forma_farmaceutica;
    }

    public String getN_utente() {
        return n_utente;
    }

    public void setN_utente(String n_utente) {
        this.n_utente = n_utente;
    }

    public String getN_medico() {
        return n_medico;
    }

    public void setN_medico(String n_medico) {
        this.n_medico = n_medico;
    }

    public String getNumero_telemovel() {
        return numero_telemovel;
    }

    public void setNumero_telemovel(String numero_telemovel) {
        this.numero_telemovel = numero_telemovel;
    }

    public String getN_beneficiario() {
        return n_beneficiario;
    }

    public void setN_beneficiario(String n_beneficiario) {
        this.n_beneficiario = n_beneficiario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getDimensao_embalagem() {
        return dimensao_embalagem;
    }

    public void setDimensao_embalagem(String dimensao_embalagem) {
        this.dimensao_embalagem = dimensao_embalagem;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getExtenso() {
        return extenso;
    }

    public void setExtenso(String extenso) {
        this.extenso = extenso;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

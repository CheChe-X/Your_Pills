package com.example.yourpills;

//com este código vamos fazer uma classe java chamada comprimido
//e a classe comprimido vai ter as variaveis nome embalagens miligramas medicamentos e data
public class comprimido {

    public String id, nome, embalagens, miligramas, medicamentos, data;

    public comprimido(){
        super();
    }

    //e vai ter cinco variáveis chamadas nome, miligramas, embalagens, medicamentos e data
    public comprimido(String id, String nome, String miligramas, String embalagens, String medicamentos, String data){
        this.id = id;
        this.nome = nome;
        this.miligramas = miligramas;
        this.medicamentos = medicamentos;
        this.embalagens = embalagens;
        this.data = data;
    }

    //neste código temos o métodos de acesso "getters" e métodos de atribuição "setters"
    //em que um dos métodos vai retornar o valor atual de uma variável e o outro vai ser o que define um novo valor
    //e assim estes métodos permitem que outras classes tenham acesso e possam modificar


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

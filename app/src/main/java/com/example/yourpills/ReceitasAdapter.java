package com.example.yourpills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//o customAdapter é usado para criar um adaptador para listas
public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.MyViewHolder> {

    //está linha define uma variável "context" do tipo Context, que será usada para armazenar o contexto em que o adapter será exibido
    private Context context;
    //A segunda linha define uma variável "receitasArrayList" do tipo ArrayList, que contém a lista de objectos "receita" que o adapter irá exibir
    ArrayList<receitas>  receitasArrayList;

    //o construtor da classe vai atribuir os valores para as variaveis "context" e "receitaArrayList"
    public ReceitasAdapter(ArrayList<receitas> receitasArrayList) {
        this.context = context;
        this.receitasArrayList = receitasArrayList;
    }

    @NonNull
    @Override
    //este metodo vai chamar o View de uma lista criada
    public ReceitasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receitas_cell,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    //com este código vai exibir os dados em uma lista
    public void onBindViewHolder(ReceitasAdapter.MyViewHolder holder, int position) {

        receitas receitas = receitasArrayList.get(position);

        //este código esta definindo os valores do TextView do layout personalizado com as variáveis, que são obtidos do objecto "receita" correspondente na lista
        holder.nome_receita.setText(receitasArrayList.get(position).getNome_receita());
        holder.n_utente.setText( receitasArrayList.get(position).getN_utente());
        holder.forma_farma.setText( receitasArrayList.get(position).getForma_farmaceutica());
        holder.dosagem.setText( receitasArrayList.get(position).getDosagem());
        holder.data.setText( receitasArrayList.get(position).getData());
    }

    //com este metodo vamos retornar o número de itens da lista
    @Override
    public int getItemCount() {
        return receitasArrayList.size();
    }

    //este codigo vai ser usado para associar os dados do recyclerview aos elementos do View
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //com este codigo estamos a definindo as TextViews que vao ser utilizadas para exibir os dados do comprimido
        TextView nome_receita, n_utente, forma_farma, dosagem, data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //e com este código estamos vinculando esses TextViews aos elementos correspondentes do layout XML
            nome_receita = itemView.findViewById(R.id.name_receita_txt);
            n_utente = itemView.findViewById(R.id.n_utente_txt);
            forma_farma = itemView.findViewById(R.id.forma_txt);
            dosagem = itemView.findViewById(R.id.dosagem_txt);
            data = itemView.findViewById(R.id.date_receita_txt);
        }

    }
}

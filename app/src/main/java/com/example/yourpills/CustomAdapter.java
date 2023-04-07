package com.example.yourpills;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

//o customAdapter é usado para criar um adaptador para listas
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //está linha define uma variável "context" do tipo Context, que será usada para armazenar o contexto em que o adapter será exibido
    private Context context;
    //A segunda linha define uma variável "comprimidoArrayList" do tipo ArrayList, que contém a lista de objectos "comprimido" que o adapter irá exibir
    ArrayList<comprimido> comprimidoArrayList;

    //o construtor da classe vai atribuir os valores para as variaveis "context" e "comprimidoArrayList"
    public CustomAdapter(ArrayList<comprimido> comprimidoArrayList) {
        this.context = context;
        this.comprimidoArrayList = comprimidoArrayList;
    }

    @NonNull
    @Override
    //este metodo vai chamar o View de uma lista criada
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comprimidos_cell,parent,false);

        return new MyViewHolder(view);
    }

    //com este código vai exibir os dados em uma lista
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        comprimido comprimido = comprimidoArrayList.get(position);

        //este código esta definindo os valores do TextView do layout personalizado com as variáveis, que são obtidos do objecto "comprimido" correspondente na lista
        holder.name_comp.setText(comprimidoArrayList.get(position).getNome());
        holder.mil_comp.setText( comprimidoArrayList.get(position).getMiligramas());
        holder.med_comp.setText( comprimidoArrayList.get(position).getMedicamentos());
        holder.emb_comp.setText( comprimidoArrayList.get(position).getEmbalagens());
        holder.data_comp.setText( comprimidoArrayList.get(position).getData());

    }

    //com este metodo vamos retornar o número de itens da lista
    @Override
    public int getItemCount() {
        return comprimidoArrayList.size();
    }

    //este codigo vai ser usado para associar os dados do recyclerview aos elementos do View
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //com este codigo estamos a definindo as TextViews que vao ser utilizadas para exibir os dados do comprimido
        TextView name_comp, mil_comp, emb_comp, med_comp, data_comp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //e com este código estamos vinculando esses TextViews aos elementos correspondentes do layout XML
            name_comp = itemView.findViewById(R.id.name_comp_txt);
            mil_comp = itemView.findViewById(R.id.mil_comp_txt);
            emb_comp = itemView.findViewById(R.id.emb_comp_txt);
            med_comp = itemView.findViewById(R.id.med_comp_txt);
            data_comp = itemView.findViewById(R.id.date_comp_txt);

            

        }

    }
}

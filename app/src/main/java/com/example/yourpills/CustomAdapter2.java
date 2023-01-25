package com.example.yourpills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {
    private Context context;
    ArrayList<receitas>  receitasArrayList;

    public CustomAdapter2(ArrayList<receitas> receitasArrayList) {
        this.context = context;
        this.receitasArrayList = receitasArrayList;
    }

    @NonNull
    @Override
    public CustomAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recyclerview2,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter2.MyViewHolder holder, int position) {
        receitas receitas = receitasArrayList.get(position);

        holder.nome_receita.setText(receitasArrayList.get(position).getNome_receita());
        holder.n_utente.setText( receitasArrayList.get(position).getN_utente());
        holder.forma_farma.setText( receitasArrayList.get(position).getForma_farmaceutica());
        holder.dosagem.setText( receitasArrayList.get(position).getDosagem());
        holder.data.setText( receitasArrayList.get(position).getData());
    }


    @Override
    public int getItemCount() {
        return receitasArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome_receita, n_utente, forma_farma, dosagem, data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_receita = itemView.findViewById(R.id.name_receita_txt);
            n_utente = itemView.findViewById(R.id.n_utente);
            forma_farma = itemView.findViewById(R.id.forma_txt);
            dosagem = itemView.findViewById(R.id.dosagem_txt);
            data = itemView.findViewById(R.id.date_receita_txt);
        }
    }
}

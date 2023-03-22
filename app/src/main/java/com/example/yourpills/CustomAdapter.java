package com.example.yourpills;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//o customAdapter é usado para criar um adaptador para listas
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    ArrayList<comprimido> comprimidoArrayList;
    private Dialog dialog;

    public interface Dialog{
            void onClick(int poss);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

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

    //com este codigo vai exibir os dados em uma lista
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        comprimido comprimido = comprimidoArrayList.get(position);

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

        TextView name_comp, mil_comp, emb_comp, med_comp, data_comp;

        ImageButton edit_Button, delete_Button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_comp = itemView.findViewById(R.id.name_comp_txt);
            mil_comp = itemView.findViewById(R.id.mil_comp_txt);
            emb_comp = itemView.findViewById(R.id.emb_comp_txt);
            med_comp = itemView.findViewById(R.id.med_comp_txt);
            data_comp = itemView.findViewById(R.id.date_comp_txt);

            edit_Button = itemView.findViewById(R.id.edit_Button);
            delete_Button = itemView.findViewById(R.id.delete_Button);

        }

    }
}

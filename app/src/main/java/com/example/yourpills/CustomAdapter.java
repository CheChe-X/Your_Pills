package com.example.yourpills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    ArrayList<comprimido> comprimidoArrayList;

    public CustomAdapter(ArrayList<comprimido> comprimidoArrayList) {
        this.context = context;
        this.comprimidoArrayList = comprimidoArrayList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recyclerview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        comprimido comprimido = comprimidoArrayList.get(position);

        holder.name_comp.setText(comprimidoArrayList.get(position).getNome());
        holder.mil_comp.setText( comprimidoArrayList.get(position).getMiligramas());
        holder.med_comp.setText( comprimidoArrayList.get(position).getMedicamentos());
        holder.emb_comp.setText( comprimidoArrayList.get(position).getEmbalagens());
        holder.data_comp.setText( comprimidoArrayList.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return comprimidoArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name_comp, mil_comp, emb_comp, med_comp, data_comp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_comp = itemView.findViewById(R.id.name_comp_txt);
            mil_comp = itemView.findViewById(R.id.mil_comp_txt);
            emb_comp = itemView.findViewById(R.id.emb_comp_txt);
            med_comp = itemView.findViewById(R.id.med_comp_txt);
            data_comp = itemView.findViewById(R.id.date_comp_txt);
        }

    }
}

package com.example.yourpills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    ArrayList<comprimido> comprimidoArrayList;

    public CustomAdapter(Context context, ArrayList<comprimido> comprimidoArrayList) {
        this.context = context;
        this.comprimidoArrayList = comprimidoArrayList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.my_recyclerview,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        comprimido comprimido = comprimidoArrayList.get(position);

        holder.name_comp.setText(comprimido.nome);
        holder.mil_comp.setText((int) comprimido.miligramas);
        holder.med_comp.setText((int) comprimido.medicamentos);
        holder.emb_comp.setText((int) comprimido.embalagens);
        holder.data_comp.setText((int) comprimido.data);



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

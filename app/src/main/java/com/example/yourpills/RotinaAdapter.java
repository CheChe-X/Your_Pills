package com.example.yourpills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RotinaAdapter extends RecyclerView.Adapter<RotinaAdapter.MyViewHolder> {

    private Context context;
    ArrayList<rotina> rotinaArrayList;


    public RotinaAdapter(ArrayList<rotina> rotinaArrayList){
        this.context = context;
        this.rotinaArrayList = rotinaArrayList;
    }

    @NonNull
    @Override
    public RotinaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rotina_cell,parent,false);

            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RotinaAdapter.MyViewHolder holder, int position) {
        rotina rotina = rotinaArrayList.get(position);

        holder.nome_rotina.setText(rotinaArrayList.get(position).getNome());
        holder.hour.setText(rotinaArrayList.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return rotinaArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome_rotina, hour;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_rotina = itemView.findViewById(R.id.name_rotina_txt);
            hour = itemView.findViewById(R.id.hour_txt);
        }
    }
}

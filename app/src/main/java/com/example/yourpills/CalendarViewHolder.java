package com.example.yourpills;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    //este ArrayList de objetos LocalDate que contém todas as datas do mês atual exibidas no RecyclerView
    private final ArrayList<LocalDate> days;
    //este View vai representar o item do RecyclerView, que contém o dia do mês a ser exibido
    public final View parentView;
    //Declaração de uma variável do tipo TextView
    public final TextView dayOfMonth;
    //este código é uma interface que define um método onItemClick que é chamado quando um item é clicado no RecyclerView
    private final CalendarAdapter.OnItemListener onItemListener;
    //este código é um construtor que recebe como parâmetros o itemView, o onItemListener e o ArrayList de dias do mês atual.
    // Ele inicializa as variáveis parentView, dayOfMonth, onItemListener e days com os respectivos parâmetros.
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days)
    {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    //este código vai chamar o método onItemClick() definido na interface onItemListener e
    // ele vai passar a posição do item clicado no RecyclerView e a data correspondente (obtida do ArrayList de dias) como parâmetros
    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
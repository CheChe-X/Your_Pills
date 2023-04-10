package com.example.yourpills;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    //este código vai definir uma variável final chamada "days"
    //este código é usado para armazenar uma lista de datas que serão exibidas no calendário
    private final ArrayList<LocalDate> days;

    //este código vai definir uma variável final chamada "onItemListener"
    //este código é usado para capturar eventos de clique do usuário em um item do calendário
    private final OnItemListener onItemListener;

    //está classe de adaptador é tipicamente usada em conjunto com a classe RecyclerView para
    // exibir uma lista de datas em um layout de calendário personalizado.
    // O construtor é chamado quando o adaptador é instanciado
    // e define as variáveis days e onItemListener com as entradas fornecidas.
    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    //este código é responsável por criar a visualização de cada célula do calendário
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //O primeiro passo é criar um objeto LayoutInflater usando o contexto do ViewGroup pai passado como parâmetro
        //Isso permite que o código infle o layout da célula do calendário,
        //que é definido em um arquivo XML (calendar_cell.xml)
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //O método inflate() é usado para criar uma instância da view da célula do calendário
        // usando o layout definido em calendar_cell.xml
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        //o código obtém os parâmetros de layout da view recém-criada
        // e ajusta sua altura com base no número de dias no mês
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = (int) parent.getHeight();

        return new CalendarViewHolder(view, onItemListener, days);
    }

    //este código é responsável por vincular os dados de um objeto CalendarViewHolder
    //com um item específico na lista de datas exibida pelo adaptador
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        //
        final LocalDate date = days.get(position);
        //Se a data for nula, significa que não há um dia correspondente àquela posição
        //então o texto do dia do mês é definido como vazio no TextView correspondente
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            //Caso contrário, o dia do mês é definido como o dia do mês da data correspondente
            //se a data correspondente for igual à data selecionada
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtils.selectDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    //Este código é parte de uma classe que implementa o RecyclerView.Adapter no Android Studio
    //e o método getItemCount() é responsável por retornar o número de itens que o RecyclerView irá exibir
    @Override
    public int getItemCount()
    {
        return days.size();
    }

    //este código define uma interface OnItemListener que declara um método onItemClick que recebe dois parâmetros
    //a posição do item na lista e a data correspondente a esse item na lista
    public interface  OnItemListener
    {
        //está interface é utilizada para permitir que a classe CalendarAdapter possa se comunicar
        //com a atividade que a utiliza, notificando-a quando um item da lista é clicado
        void onItemClick(int position, LocalDate date);
    }
}
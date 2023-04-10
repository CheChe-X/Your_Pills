package com.example.yourpills;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtils {
    //A selectDate é uma variável que pertence a class LocalDate que representa a data e hora
    public static LocalDate selectDate;

    //este código vai chamar o método formattedDate recebe um objeto LocalDate
    // como entrada e retorna uma string formatada no formato "dd MMMM yyyy"
    public static String formattedDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    //este código vai chamar o método formattedTime recebe um objeto LocalTime
    // como entrada e retorna uma string formatada no formato "hh:mm:ss a"
    public static String formattedTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);
    }

    //este código o método recebe um objeto LocalDate como entrada,
    // que representa uma data sem hora e sem fuso horário
    // Em seguida, ele cria um objeto DateTimeFormatter usando o padrão "MMMM yyyy"
    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }


    //Este código é um método que recebe uma data do tipo LocalDate como entrada e retorna
    // uma lista de todas as datas dentro do mês correspondente.
    public static ArrayList<LocalDate> daysInMonthArray(LocalDate Data) {
        //O método começa criando um objeto ArrayList para armazenar todas as datas dentro do mês
        // correspondente e obtém o mês e o ano
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(Data);
        int daysInMonth = yearMonth.lengthOfMonth();

        //neste código o método vai determinar em qual dia da semana o primeiro dia do mês começa
        // usando withDayOfMonth(1) e getDayOfWeek().getValue()
        LocalDate firstOfMonth = CalendarUtils.selectDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        //agora o método então percorre todos os dias do mês em um loop, adicionando cada data à lista
        for(int i = 1; i <= daysInMonth + dayOfWeek; i++){
            //Se o dia em questão não corresponder ao mês em questão, o método adiciona null à lista
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add(null);
            }
            else {
                daysInMonthArray.add(LocalDate.of(selectDate.getYear(),selectDate.getMonth(), i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }
}
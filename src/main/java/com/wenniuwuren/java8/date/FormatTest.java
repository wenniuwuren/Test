package com.wenniuwuren.java8.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 与老的DateFormat相比，DateTimeFormatter 是线程安全的
 * Created by hzzhuyibin on 2017/5/25.
 */
public class FormatTest {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2017, 5, 25);
        // 格式化日期
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20170525

        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2017-05-25

        // 回退格式化
        LocalDate date1 = LocalDate.parse("20170525", DateTimeFormatter.BASIC_ISO_DATE); // 2017-05-25
        System.out.println(date1.toString());
        LocalDate date2 = LocalDate.parse("2017-05-25", DateTimeFormatter.ISO_LOCAL_DATE); // 2017-05-25
        System.out.println(date2.toString());

        // 指定格式的格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.of(2017, 5, 25);
        String formatterDate = date3.format(formatter); // 25/05/2017
        LocalDate date4 = LocalDate.parse(formatterDate, formatter); // 2017-05-25


    }
}


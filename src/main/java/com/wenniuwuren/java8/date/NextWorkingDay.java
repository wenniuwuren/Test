package com.wenniuwuren.java8.date;


import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 定制的 TemporalAdjuster
 * 如果当天介意周一到周五，则+1天；如果是周六或者周日，则返回周一
 * Created by hzzhuyibin on 2017/5/25.
 */
public class NextWorkingDay implements TemporalAdjuster {


    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 读取当前日期
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

        // 默认+1天
        int addDay = 1;
        if (dow == DayOfWeek.FRIDAY) {
            addDay = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            addDay = 2;
        }

        return temporal.plus(addDay, ChronoUnit.DAYS);
    }
}


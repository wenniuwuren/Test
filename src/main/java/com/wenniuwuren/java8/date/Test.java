package com.wenniuwuren.java8.date;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by hzzhuyibin on 2017/5/25.
 */
public class Test {

    public static void main(String[] args) {

        /**
         *  final 类 LocalDate（年月日），不可变对象
         */
        LocalDate date = LocalDate.of(2020, 01, 01);
        // 年
        int year = date.getYear();
        int year1 = date.get(ChronoField.YEAR);

        // 月
        Month month = date.getMonth();
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);

        // 这个月的几号
        int day = date.getDayOfMonth();
        int day1 = date.get(ChronoField.DAY_OF_MONTH);

        // 这个月有多少天
        int len = date.lengthOfMonth();
        // 是否闰年
        boolean leap = date.isLeapYear();
        // 当前时间
        LocalDate today = LocalDate.now();
        // str解析
        LocalDate date1 = LocalDate.parse("2017-05-25");

        /**
         * LocalTime（时分秒）
         */
        LocalTime time = LocalTime.of(11, 11, 11);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        LocalTime localTime = LocalTime.parse("16:24:24");

        /**
         * LocalDateTime 日期+时间（便于人使用）
         */
        LocalDateTime dt1 = LocalDateTime.of(2017, 05, 25, 16, 00, 00);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(16, 00, 00);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate localDate = dt1.toLocalDate();
        LocalTime localTime1 = dt1.toLocalTime();

        /**
         * Instant (便于机器使用)
         */
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(3, 0); // 3秒之后再加0纳秒

        /**
         * Duration 以秒和纳秒衡量时间长短
         */
        Duration d1 = Duration.between(time, time);
        Duration d2 = Duration.between(instant1, instant2);
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);

        /**
         * Period 如果需要以年、月或日方式建模
         */
        Period period = Period.between(LocalDate.of(2017, 05, 25), LocalDate.of(2017, 05, 30));
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        // ---------------以上都是不可修改对象，为了更好支持函数式编程，线程安全-----------------------

        // 可以看到改变日期，都是新生成一个新对象
        LocalDate date2 = LocalDate.of(2017, 05, 25); // 2017-05-25
        LocalDate date3 = date2.withYear(2016);       // 2016-05-25
        LocalDate date4 = date2.withDayOfMonth(20);   // 2017-05-20
        LocalDate date5 = date2.with(ChronoField.MONTH_OF_YEAR, 9);  // 2017-09-25
        LocalDate date6 = date2.plusWeeks(1);         // 2017-06-01
        LocalDate date7 = date2.minusYears(1);        // 2017-05-25
        LocalDate date8 = date2.plus(2, ChronoUnit.MONTHS);  // 2017-07-25

        // TemporalAdjusters
        LocalDate date9 = date2.with(nextOrSame(DayOfWeek.FRIDAY)); // 下一个周五 2017-05-26
        LocalDate date10 = date2.with(lastDayOfMonth());            // 这个月最后一天 2017-05-31


    }
}


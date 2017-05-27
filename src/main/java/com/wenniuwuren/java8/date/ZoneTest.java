package com.wenniuwuren.java8.date;

import java.time.*;

/**
 * 处理不同时区和历法
 * Created by hzzhuyibin on 2017/5/26.
 */
public class ZoneTest {

    public static void main(String[] args) {
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        /**
         * 为时间添加时区信息
         */
        LocalDate date = LocalDate.of(2017, 05, 26);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone); // 2017-05-26T00:00+02:00[Europe/Rome]

        LocalDateTime dateTime = LocalDateTime.of(2017, 5, 26, 11, 00, 00);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone); // 2017-05-26T11:00+02:00[Europe/Rome]

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone); // 2017-05-26T05:19:34.425+02:00[Europe/Rome]




    }

}


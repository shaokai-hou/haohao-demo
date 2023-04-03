package com.haohao.demo.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateQuarter {

    /**
     * 当前季度开始日期时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime beginDateTime() {
        LocalDate now = LocalDate.now();
        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue());
        return LocalDateTime.of(LocalDate.of(now.getYear(), month, 1), LocalTime.MIN);
    }

    /**
     * 当前季度结束日期时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime endDateTime() {
        LocalDate now = LocalDate.now();
        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue()).plus(2L);
        return LocalDateTime.of(LocalDate.of(now.getYear(), month, month.length(now.isLeapYear())), LocalTime.MAX);
    }

    public static void main(String[] args) {
        System.out.println(beginDateTime());
        System.out.println(endDateTime());
    }
}

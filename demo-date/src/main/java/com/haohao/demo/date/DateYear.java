package com.haohao.demo.date;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateYear {

    /**
     * 当前年开始日期时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime beginDateTime() {
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
    }

    /**
     * 当前年开始日期时间字符串
     *
     * @return LocalDateTime
     */
    public static String beginDateTimeStr() {
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN).format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE_TIME));
    }

    /**
     * 当前年结束日期时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime endDateTime() {
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
    }

    /**
     * 当前年结束日期时间字符串
     *
     * @return LocalDateTime
     */
    public static String endDateTimeStr() {
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX).format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE_TIME));
    }

    /**
     * 当前年开始日期
     *
     * @return LocalDateTime
     */
    public static LocalDate beginDate() {
        return beginDateTime().toLocalDate();
    }

    /**
     * 当前年开始日期字符串
     *
     * @return LocalDateTime
     */
    public static String beginDateStr() {
        return beginDateTime().toLocalDate().format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE));
    }

    /**
     * 当前年结束日期
     *
     * @return LocalDateTime
     */
    public static LocalDate endDate() {
        return endDateTime().toLocalDate();
    }

    /**
     * 当前年结束日期字符串
     *
     * @return LocalDateTime
     */
    public static String endDateStr() {
        return endDateTime().toLocalDate().format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE));
    }

    public static void main(String[] args) {
        System.out.println(DateYear.beginDateTime());
        System.out.println(DateYear.beginDateTimeStr());
        System.out.println(DateYear.beginDate());
        System.out.println(DateYear.beginDateStr());
        System.out.println(DateYear.endDateTime());
        System.out.println(DateYear.endDateTimeStr());
        System.out.println(DateYear.endDate());
        System.out.println(DateYear.endDateStr());
    }
}

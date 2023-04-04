package com.haohao.demo.date;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {


    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    public static String nowDateStr() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE));
    }

    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    public static String nowDateTimeStr() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE_TIME));
    }

    /**
     * 判断是否是日期
     *
     * @param dateStr 日期字符串
     * @return result
     */
    public static Boolean isDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE));
            return Boolean.TRUE;
        } catch (DateTimeException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * 判断是否是日期时间
     *
     * @param dateStr 日期时间字符串
     * @return result
     */
    public static Boolean isDateTime(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DateConstant.FORMATTER_DATE_TIME));
            return Boolean.TRUE;
        } catch (DateTimeException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * 是否包含指定范围内
     *
     * @param dateTime      日期时间
     * @param beginDateTime 开始日期时间
     * @param endDateTime   结束日期时间
     * @return result
     */
    public static Boolean isIn(LocalDateTime dateTime, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        if ((beginDateTime.isBefore(dateTime) || beginDateTime.equals(dateTime)) && (endDateTime.isAfter(dateTime) || endDateTime.equals(dateTime))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 是否包含指定范围内
     *
     * @param date      日期
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return result
     */
    public static Boolean isIn(LocalDate date, LocalDate beginDate, LocalDate endDate) {
        if ((beginDate.isBefore(date) || beginDate.equals(date)) && (endDate.isAfter(date) || endDate.equals(date))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.nowDate());
        System.out.println(DateUtil.nowDateStr());
        System.out.println(DateUtil.nowDateTime());
        System.out.println(DateUtil.nowDateTimeStr());
        System.out.println(DateUtil.isDate("2022-02-12"));
        System.out.println(DateUtil.isDateTime("2022-02-12"));
        System.out.println(DateUtil.isIn(LocalDateTime.of(2023, 1, 1, 0, 0), DateYear.beginDateTime(), DateYear.endDateTime()));
        System.out.println(DateUtil.isIn(LocalDate.of(2023, 1, 1), DateYear.beginDate(), DateYear.endDate()));
    }
}

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

    public static void main(String[] args) {
        System.out.println(DateUtil.nowDate());
        System.out.println(DateUtil.nowDateStr());
        System.out.println(DateUtil.nowDateTime());
        System.out.println(DateUtil.nowDateTimeStr());
        System.out.println(DateUtil.isDate("2022-02-12"));
        System.out.println(DateUtil.isDateTime("2022-02-12"));
    }
}

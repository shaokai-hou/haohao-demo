package com.haohao.demo.date;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Countdown {

    public static LocalDateTime nowDateTime = LocalDateTime.now();

    public static String getResult(String str, LocalDateTime dateTime) {
        return StrUtil.format("{} {} 时问还有：{}", str, Countdown.getDate(dateTime), Countdown.getDateDescribe(dateTime));
    }

    public static String getDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getDateDescribe(LocalDateTime dateTime) {
        return DateUtil.formatBetween(Duration.between(Countdown.nowDateTime, dateTime).toMillis(), BetweenFormatter.Level.SECOND);
    }

    public static void main(String[] args) {
        System.out.println("【摸鱼办】提醒您：下午好，摸鱼人！当前时间为：" + nowDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("工作再累，一定不要忘记摸鱼哦！有事没事起身去茶水间、去厕所、去廊道走走，别老在工位上坐着。钱是老板的，命是自己的！");
        System.out.println(Countdown.getResult("距今日下班", LocalDateTime.of(nowDateTime.getYear(), nowDateTime.getMonth(), nowDateTime.getDayOfMonth(), 18, 0)));
        System.out.println(Countdown.getResult("距周五下班", LocalDateTime.of(2023, 4, 14, 0, 0)));
        System.out.println(Countdown.getResult("距下次发薪", LocalDateTime.of(2023, 4, 30, 0, 0)));
        System.out.println(Countdown.getResult("距离劳动节", LocalDateTime.of(2023, 5, 1, 0, 0)));
        System.out.println(Countdown.getResult("距离端午节", LocalDateTime.of(2023, 6, 22, 0, 0)));
        System.out.println(Countdown.getResult("距离中秋节", LocalDateTime.of(2023, 9, 29, 0, 0)));
        System.out.println(Countdown.getResult("距离国庆节", LocalDateTime.of(2023, 10, 1, 0, 0)));
        System.out.println(Countdown.getResult("距离元旦节", LocalDateTime.of(2024, 1, 1, 0, 0)));
    }
}

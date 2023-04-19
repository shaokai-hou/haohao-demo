package com.haohao.demo.date;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 假期倒计时
 */
public class Countdown {

    public static LocalDateTime nowDateTime = LocalDateTime.now();

    public static String getResult(String str, LocalDateTime dateTime) {
        return StrUtil.format("{} {} 时间还有：{}", str, Countdown.getDate(dateTime), Countdown.getDateDescribe(dateTime));
    }

    public static String getDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getDateDescribe(LocalDateTime dateTime) {
        return DateUtil.formatBetween(Duration.between(Countdown.nowDateTime, dateTime).toMillis(), BetweenFormatter.Level.SECOND);
    }

    public static List<JSONObject> getHolidays() {
        String result = HttpUtil.get("http://timor.tech/api/holiday/year/" + Countdown.nowDateTime.getYear() + "/");
        JSONObject obj = JSONUtil.parseObj(result);
        if (StrUtil.equals("0", obj.getStr("code"))) {

            JSONObject holiday = obj.getJSONObject("holiday");
            // 过滤节假日、排序
            return holiday.keySet()
                    .stream()
                    .map(holiday::getJSONObject)
                    .filter(item -> item.getBool("holiday") && Countdown.nowDateTime.isBefore(item.getLocalDateTime("date", LocalDateTime.now())))
                    .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(item -> item.getStr("name")))), LinkedList::new))
                    .stream().sorted(Comparator.comparing(o -> o.getLocalDateTime("date", LocalDateTime.now()).toLocalDate())).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        List<JSONObject> list = getHolidays();
        System.out.println("下午好！当前时间为：" + nowDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Countdown.getResult("距今日下班", LocalDateTime.of(nowDateTime.getYear(), nowDateTime.getMonth(), nowDateTime.getDayOfMonth(), 18, 0)));
        list.forEach(item -> System.out.println(Countdown.getResult("距离" + item.getStr("name"), item.getLocalDateTime("date", LocalDateTime.now()))));
    }
}

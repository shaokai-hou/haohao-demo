package com.haohao.demo.date;

import cn.hutool.core.lang.Validator;

public class DateUtil {

    public static void main(String[] args) {
        cn.hutool.core.date.DateUtil.parse("2021-02-31", "yyyy-MM-dd");
        System.out.println(Validator.isMatchRegex("", "2021-02-31"));
    }
}

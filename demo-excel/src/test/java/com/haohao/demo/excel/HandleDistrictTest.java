package com.haohao.demo.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


@SpringBootTest
class HandleDistrictTest {

    @Resource
    private HandleDistrict handleDistrict;

    @Test
    void handleData() {
        List<Map<Integer, String>> mapList = handleDistrict.handleData();

        // 过滤省份
//        List<Map<Integer, String>> collect = mapList.stream().filter(map -> StrUtil.equals(MapUtil.getStr(map, 1), "陕西省")).collect(Collectors.toList());
        // 编码组成数组
        StringJoiner content = new StringJoiner(",", "String[] codes = new String[]{", "};");
        mapList.stream().filter(map -> StrUtil.equals(MapUtil.getStr(map, 1), "陕西省"))
                .map(map -> "\"" + MapUtil.getStr(map, 5) + "\"")
                .forEach(content::add);
        String fileName = "/Users/haohao/Documents/weather_district_id.java";
        FileUtil.writeUtf8String(content.toString(), fileName);

        // 过滤市
//        mapList.stream().filter(map -> StrUtil.equals(MapUtil.getStr(map, 2), "宝鸡市")).collect(Collectors.toList()).forEach(System.err::println);
        // 过滤省、市
//        mapList.stream().filter(map -> StrUtil.equals(MapUtil.getStr(map, 1), "陕西省") && StrUtil.equals(MapUtil.getStr(map, 2), "宝鸡市")).collect(Collectors.toList()).forEach(System.err::println);

    }
}
package com.haohao.demo.excel.finance;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class Demo {

    public static void main(String[] args) {
        List<DemoData> data = new ArrayList<>();
        String fileName = "/Users/haohao/temp/子长东5-16.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {

            @Override
            public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                log.info("解析到一条数据：{}", demoData);
                data.add(demoData);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                log.info("数据解析完成！");
            }
        }).sheet("Sheet1").doRead();

        List<DemoData> writerData = new ArrayList<>();
        data.stream()
                .filter(item -> Objects.nonNull(item.getTwo()))
                .collect(Collectors.groupingBy(DemoData::getTwo))
                .forEach((k, v) -> {
                    DemoData demoData = new DemoData();
                    demoData.setZero(k);
                    long count = v.stream()
                            .filter(item -> StrUtil.isNotBlank(item.getFive()))
                            .mapToDouble(item -> Double.parseDouble(item.getFive().replaceAll(",", "")))
                            .count();

                    double sum = v.stream()
                            .filter(item -> StrUtil.isNotBlank(item.getFive()))
                            .mapToDouble(item -> Double.parseDouble(item.getFive().replaceAll(",", "")))
                            .sum();

                    demoData.setOne(count + "个");
                    demoData.setTwo(NumberUtil.decimalFormat("###,###,###.##", sum));
                    writerData.add(demoData);
                });

        String outFileName = "/Users/haohao/temp/out_put.xlsx";
        EasyExcel.write(outFileName, DemoData.class).sheet(0).doWrite(writerData);
    }
}

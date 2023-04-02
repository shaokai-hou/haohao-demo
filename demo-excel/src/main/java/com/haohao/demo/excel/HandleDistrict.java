package com.haohao.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HandleDistrict {

    public List<Map<Integer, String>> handleData() {
        List<Map<Integer, String>> data = new ArrayList<>();

        String fileName = "/Users/haohao/Documents/weather_district_id.xlsx";
        EasyExcel.read(fileName, new ReadListener<Map<Integer, String>>() {

            @Override
            public void invoke(Map<Integer, String> excelData, AnalysisContext analysisContext) {
                log.info("解析到一条数据：{}", excelData);
                data.add(excelData);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                log.info("数据解析完成！");
            }
        }).sheet().doRead();
        return data;
    }
}

package com.haohao.demo.excel;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HandleDistrict {

    public List<Map<Integer, String>> handleData() {
        List<Map<Integer, String>> data = new ArrayList<>();
        Integer batchSize = 100;

        String fileName = "/Users/haohao/Documents/数据/weather_district_id.xlsx";
        EasyExcel.read(fileName, new ReadListener<Map<Integer, String>>() {

            @Override
            public void invoke(Map<Integer, String> excelData, AnalysisContext analysisContext) {
                log.info("解析到一条数据：{}", excelData);
                if (data.size() == batchSize) {
                    handleInsert(data);
                    data.clear();
                }
                data.add(excelData);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                handleInsert(data);
                log.info("数据解析完成！");
            }
        }).sheet().doRead();
        return data;
    }

    public void handleInsert(List<Map<Integer, String>> data) {
        JdbcTemplate jdbcTemplate = SpringUtil.getBean(JdbcTemplate.class);

        String sql = "insert into `demo_district` (`province`, `city`, `city_code`, `district`, `district_code`, `longitude`, `latitude`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<Integer, String> rowMap = data.get(i);
                ps.setString(1, MapUtil.getStr(rowMap, 1));
                ps.setString(2, MapUtil.getStr(rowMap, 2));
                ps.setString(3, MapUtil.getStr(rowMap, 3));
                ps.setString(4, MapUtil.getStr(rowMap, 4));
                ps.setString(5, MapUtil.getStr(rowMap, 5));
                ps.setString(6, MapUtil.getStr(rowMap, 6));
                ps.setString(7, MapUtil.getStr(rowMap, 7));
            }

            @Override
            public int getBatchSize() {
                return data.size();
            }
        });
    }
}

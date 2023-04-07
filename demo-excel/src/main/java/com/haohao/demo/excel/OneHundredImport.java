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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 百万数据导入
 * 124秒、2分钟左右
 */
@Slf4j
@Component
public class OneHundredImport {


    public void handleImport() {

        long begin = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> sheets = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(sheets.stream().map(sheetNo ->
                        CompletableFuture.supplyAsync(() -> handleInsert(handleData(sheetNo)), executor)
                                .whenCompleteAsync((ints, throwable) -> {
                                    log.info("sheetNo :{} 添加结果:{}", sheetNo, ints);
                                    log.error("sheetNo error ", throwable);
                                })
                ).toArray(CompletableFuture[]::new)
        );
        allFutures.join(); //阻塞主线程
        System.out.println("耗时：" + (System.currentTimeMillis() - begin) / 1000);
    }

    public int[] handleInsert(List<Map<Integer, String>> data) {
        JdbcTemplate jdbcTemplate = SpringUtil.getBean(JdbcTemplate.class);

        String sql = "INSERT INTO `demo` (`number`, `name`) VALUES (?, ?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<Integer, String> rowMap = data.get(i);
                ps.setString(1, MapUtil.getStr(rowMap, 0));
                ps.setString(2, MapUtil.getStr(rowMap, 1));
            }

            @Override
            public int getBatchSize() {
                return data.size();
            }
        });
    }

    public List<Map<Integer, String>> handleData(Integer sheet) {
        log.info("当前线程：{}", Thread.currentThread().getName());
        int batchSize = 20000;
        List<Map<Integer, String>> data = new ArrayList<>();
        String fileName = "/Users/haohao/Documents/数据/one_hundred.xlsx";
        EasyExcel.read(fileName, new ReadListener<Map<Integer, String>>() {

            @Override
            public void invoke(Map<Integer, String> excelData, AnalysisContext analysisContext) {
//                log.info("sheet{}、解析到一条数据：{}", sheet, excelData);
                data.add(excelData);
                if (data.size() == batchSize) {
                    handleInsert(data);
                    data.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                handleInsert(data);
                log.info("sheet{}、数据解析完成！", sheet);
            }
        }).sheet(sheet).doRead();
        return data;
    }
}

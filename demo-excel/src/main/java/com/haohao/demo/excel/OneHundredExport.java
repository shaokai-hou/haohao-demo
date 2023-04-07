package com.haohao.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * 百万数据导出
 */
public class OneHundredExport {

    public static void main(String[] args) {
        String fileName = "/Users/haohao/Documents/数据/one_hundred.xlsx";
        try (ExcelWriter writer = EasyExcel.write(fileName).build()) {
            for (int i = 0; i < 10; i++) {
                WriteSheet sheet = EasyExcel.writerSheet(i, "Sheet" + i).head(head()).build();
                writer.write(data(), sheet);
            }
        }
    }

    public static List<List<String>> head() {
        List<List<String>> head = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("序号");
        List<String> head1 = new ArrayList<>();
        head1.add("名称");
        head.add(head0);
        head.add(head1);
        return head;
    }

    public static List<List<Object>> data() {
        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            List<Object> row = new ArrayList<>();
            row.add(String.valueOf(i));
            row.add("张三" + i);
            data.add(row);
        }
        return data;
    }
}

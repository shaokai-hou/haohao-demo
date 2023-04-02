package com.haohao.demo.excel.write;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DemoData {

    @ExcelIgnore
    private Long id;
    @ExcelProperty(value = "字符串", index = 3)
    private String str;
    @ExcelProperty(value = "日期", index = 2)
    private LocalDate date;
    @ExcelProperty(value = "数字", index = 1)
    private Double number;
}

package com.haohao.demo.excel.finance;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class DemoData {

    @ExcelProperty(index = 0)
    private String zero;

    @ExcelProperty(index = 1)
    private String one;

    @ExcelProperty(index = 2)
    private String two;

    @ExcelProperty(index = 3)
    private String three;

    @ExcelProperty(index = 4)
    private String four;

    @ExcelProperty(index = 5)
    private String five;
}

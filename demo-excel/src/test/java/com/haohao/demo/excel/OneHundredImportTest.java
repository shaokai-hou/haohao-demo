package com.haohao.demo.excel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OneHundredImportTest {

    @Resource
    private OneHundredImport oneHundredImport;

    @Test
    void handleImport() {
        oneHundredImport.handleImport();
    }
}
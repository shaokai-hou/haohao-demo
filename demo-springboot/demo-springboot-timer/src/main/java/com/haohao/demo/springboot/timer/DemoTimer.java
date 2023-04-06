package com.haohao.demo.springboot.timer;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoTimer {

    @Scheduled(cron = "00 10 00 * * ?")
    public void test1() {
        // 每天凌晨00点10分执行一次
        log.info("我被执行了 test:{}", DateUtil.now());
    }

    @Scheduled(cron = "00 00 09 15 03 ?")
    public void test2() {
        // 每年3月15号09点00分执行一次
        log.info("我被执行了 test:{}", DateUtil.now());
    }

    @Scheduled(fixedRate = 1000)
    public void interval() {
        // 一秒钟执行一次
        log.info("我被执行了 interval:{}", DateUtil.now());
    }
}

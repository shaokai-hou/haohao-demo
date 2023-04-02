package com.haohao.demo.ftp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.Ftp;

import java.io.File;
import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FtpClient {
    public static void main(String[] args) {

        /*
         * 参数解读
         * corePoolSize：核心线程数，线程池中始终存活的线程数。
         * maximumPoolSize：最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数。
         * keepAliveTime：最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程。
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

        for (int i = 0; i < 2; i++) {
            poolExecutor.execute(() -> {
                Ftp ftp = new Ftp("127.0.0.1");
                ftp.setBackToPwd(Boolean.TRUE);
                long begin = Instant.now().toEpochMilli();
                ftp.recursiveDownloadFolder("/temphr", new File("/Users/haohao/temp-back"));
                long end = Instant.now().toEpochMilli();
                System.out.println(StrUtil.format("下载完成，使用时间:{}", end - begin));
            });
        }
    }
}

package com.haohao.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestRunnable {

    public static final ThreadLocal<String> threadName = new ThreadLocal<>();
    public static String name;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);

    @GetMapping("/1")
    public void test() {
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                TestRunnable.threadName.set(Thread.currentThread().getName());
                System.out.println("当前线程名称" + Thread.currentThread().getName() + "|" + TestRunnable.threadName.get());
                TestRunnable.threadName.remove();

                TestRunnable.name = Thread.currentThread().getName();
                try {
                    // 模拟一分钟业务
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.err.println("当前线程名称" + Thread.currentThread().getName() + "|" + TestRunnable.name);
            });
        }
    }
}

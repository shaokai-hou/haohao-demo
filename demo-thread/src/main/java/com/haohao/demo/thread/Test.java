package com.haohao.demo.thread;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 正常流程
        long begin = System.currentTimeMillis();
//        Test.test1();
//        Test.test2();
//        Test.test3();
//        Test.test4();

        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> jg1 = CompletableFuture.supplyAsync(Test::test1, executor);
//        CompletableFuture<String> jg2 = CompletableFuture.supplyAsync(Test::test2, executor);
//        CompletableFuture<String> jg3 = CompletableFuture.supplyAsync(Test::test3, executor);
//        CompletableFuture<String> jg4 = CompletableFuture.supplyAsync(Test::test4, executor);
//        CompletableFuture<String> jg5 = CompletableFuture.supplyAsync(Test::test4, executor);

//        String s1 = jg1.get(5, TimeUnit.SECONDS);
//        String s2 = jg2.get();
//        String s3 = jg3.get();
//        String s4 = jg4.get();
//        String s5 = jg5.get();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Test::test1, executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(Test::test2, executor);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(Test::test3, executor);
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(Test::test4, executor);
        System.out.println("future1：" + future1.get());
        System.out.println("future2：" + future2.get());
        System.out.println("future3：" + future3.get());
        System.out.println("future4：" + future4.get());
        System.out.println(Thread.currentThread().getName());
        System.out.println("耗时" + (System.currentTimeMillis() - begin));
    }

    public static String test1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123";
    }

    public static String test2() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123567";
    }

    public static String test3() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123";
    }

    public static String test4() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123";
    }
}

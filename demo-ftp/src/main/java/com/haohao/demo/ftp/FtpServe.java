package com.haohao.demo.ftp;

import cn.hutool.extra.ftp.SimpleFtpServer;

public class FtpServe {

    public static void main(String[] args) {
        SimpleFtpServer.create().addAnonymous("/Users/haohao/temp").start();
    }
}

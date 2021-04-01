package com.fml.learn.theadpool;

import com.fml.learn.BargainCommonUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务。
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
//        processCommand();
        int count = 100;
        BigDecimal price = BigDecimal.valueOf(800L);
        BigDecimal finalPrice = BigDecimal.ZERO;
        List<BigDecimal> list = BargainCommonUtils.getTempBargainList(count, price, finalPrice);
//        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}

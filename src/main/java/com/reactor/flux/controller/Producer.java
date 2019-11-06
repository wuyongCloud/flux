package com.reactor.flux.controller;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @ProjectName 公牛智能家居云服务平台
 * @Description
 * @date 2019/11/6 11:01
 */
public class Producer implements Runnable {

    private BlockingDeque blockingDeque;

    private volatile boolean flag = true;

    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingDeque blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                System.out.println("开始生产数据>>>>>>>>>>>>>>>>>>>>>>>>");
                String s = count.incrementAndGet() + "";
                boolean offer = blockingDeque.offer(s, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("生产者存入数据" + s + "到队列中，成功");
                } else {
                    System.out.println("生产者存入数据" + s + "到队列中，失败");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<生产者线程退出");
        }
    }


    public void stopFlag(){
        flag=false;
    }
}

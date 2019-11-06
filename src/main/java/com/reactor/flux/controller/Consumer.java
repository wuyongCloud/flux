package com.reactor.flux.controller;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @ProjectName 公牛智能家居云服务平台
 * @Description
 * @date 2019/11/6 11:27
 */
public class Consumer implements Runnable {

    private BlockingDeque<String> blockingDeque;

    private volatile boolean flag = true;

    public Consumer(BlockingDeque blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {

        try {
            while (flag) {
                System.out.println("消费者开始消费数据>>>>>>>>>>>>>>>>>>>>>>");
                String poll = blockingDeque.poll(2, TimeUnit.SECONDS);
                if(poll !=null){
                    System.out.println("消费者消费到数据="+poll);
                }else {
                    System.out.println("消费者无法获取数据");
                    flag=false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者退出线程>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    public void stopFlag(){
        flag=false;
    }
}

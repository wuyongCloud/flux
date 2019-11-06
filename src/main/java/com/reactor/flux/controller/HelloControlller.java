package com.reactor.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author wy
 * @ProjectName 公牛智能家居云服务平台
 * @Description
 * @date 2019/11/5 15:44
 */
@RestController
public class HelloControlller {

    @GetMapping("/hello")
    public String getHello(){
        return "hello web flux";
    }

    @GetMapping("/hello1")
    public Mono<String> getHello1(){
        return Mono.just("hello web flux");
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Object> objects = new LinkedBlockingDeque<>(10);
        Producer producer = new Producer(objects);
        Producer producer1 = new Producer(objects);
        Consumer consumer = new Consumer(objects);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(producer1);
        Thread thread3 = new Thread(consumer);
        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(20000);
        producer.stopFlag();
        producer1.stopFlag();
    }
}

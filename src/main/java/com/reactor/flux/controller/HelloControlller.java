package com.reactor.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
}

package com.feng.controller;

import org.example.common.Fengkuanwen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/order/hello")
    public String hello(){
        Fengkuanwen fengkuanwen = new Fengkuanwen();

        return "hello Order";
    }
    @GetMapping("/hello")
    public String hello2(){
        Fengkuanwen fengkuanwen = new Fengkuanwen();

        return "hello Order22";
    }
}

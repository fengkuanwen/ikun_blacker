package com.feng.controller;
import org.example.common.Fengkuanwen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class TestController {
    @GetMapping("/order/hello")
    public String hello(){
        Fengkuanwen fengkuanwen = new Fengkuanwen();
        return "hello Order";
    }

}

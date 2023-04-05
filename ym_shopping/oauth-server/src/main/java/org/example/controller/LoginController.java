package org.example.controller;

import cn.hutool.core.lang.UUID;
import com.feng.result.CResponse;
import com.feng.result.HttpCode;
import com.feng.result.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/login-success")
    public String loginSuccess(){
        return "登录成功";
    }

    @RequestMapping("/r/r1")
    @PreAuthorize("hasAuthority('p1')")//拥有p1权限方可访问
    public String r1(){
        return "访问r1资源";
    }

    @RequestMapping("/r/r2")
    @PreAuthorize("hasAuthority('p2')")//拥有p1权限方可访问
    public String r2(){
        return "访问r2资源";
    }

    @GetMapping("/auth/smsLogin/smsCode")
    public CResponse getSmsCode(@RequestParam("phone")String phone){
        UUID uuid = UUID.fastUUID();
        Random random =new Random();
        String smsCode = "";
        for (int i = 0; i < 6; i++) {
            smsCode+=Integer.valueOf(random.nextInt(10)).toString();
        }
//        手机验证码携带用户唯一ID存储到缓存中,默认缓存时间3分钟
        stringRedisTemplate.opsForValue().set("smsCode:smsLogin:"+phone,smsCode,180, TimeUnit.SECONDS);
        return new Success(HttpCode.OK,smsCode);
    }
}
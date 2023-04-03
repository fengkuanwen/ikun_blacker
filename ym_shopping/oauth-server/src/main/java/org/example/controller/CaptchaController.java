package org.example.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth/captcha")
public class CaptchaController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/login")
    public void getLoginCaptcha(@RequestParam("timeStamp") String timeStamp,HttpServletResponse response) throws Exception{

        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        ServletOutputStream outputStream = response.getOutputStream();
        //图形验证码写出到流
        lineCaptcha.write(outputStream);
        //redis生成全局ID 存储验证码 ：前端验证时需携带timeStamp时间戳
        stringRedisTemplate.opsForValue().set("captchaCode:login:"+timeStamp,lineCaptcha.getCode(),2, TimeUnit.MINUTES);
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/registry")
    public void getRegistryCaptcha(@RequestParam("timeStamp") String timeStamp, HttpServletResponse response) throws Exception{
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        获取手机短信的 验证码字符直接传输到前端，让前端自我验证
        Cookie cookie = new Cookie("registryCaptchaCode",lineCaptcha.getCode());
        cookie.setPath("/");
        response.addCookie(cookie);
        ServletOutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}

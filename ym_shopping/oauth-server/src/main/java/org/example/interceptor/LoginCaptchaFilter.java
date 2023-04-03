package org.example.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@Component
public class LoginCaptchaFilter implements Filter {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 因为 HttpServletRequest 不能直接操作 parmeterMap.所以构建 Mapper 对象来进行操作
        String captcha = request.getParameterValues("captcha")[0];
        String timeStamp = request.getParameterValues("timeStamp")[0];
//        简单写一下嘻嘻，正确的应该是获取response流，然后响应
        if (captcha==null||captcha.isEmpty()||timeStamp==null||timeStamp.isEmpty()) throw new ServletException("验证码错误");
        else {
            String redisCaptcha = stringRedisTemplate.opsForValue().get("captchaCode:login:" + timeStamp);
           if(captcha.equals(redisCaptcha)){
               filterChain.doFilter(servletRequest,servletResponse);
           }else {
               throw new ServletException("验证码错误");
           }
        }

    }
}
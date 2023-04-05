package org.example.config;


import org.example.UserMapper;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(username);
        if (user != null) return user;
        else throw new UsernameNotFoundException("用户不存在");
    }

    public UserDetails loadUserByUserPhone(String phone,String smsCode) throws UsernameNotFoundException {
        String smsCodeCache = stringRedisTemplate.opsForValue().get("smsCode:smsLogin:"+phone);
        if(smsCode.isEmpty() || !smsCode.equals(smsCodeCache)){
            throw new BadCredentialsException("验证码错误!");
        }
        User user = userMapper.loadUserByUserPhone(phone);
        if (user != null) return user;
        else throw new UsernameNotFoundException("用户不存在");
    }

}

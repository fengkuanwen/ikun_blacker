package org.example.controller;

import cn.hutool.core.lang.UUID;
import com.feng.result.CResponse;
import com.feng.result.Error;
import com.feng.result.HttpCode;
import com.feng.result.Success;
import org.example.UserMapper;
import org.example.entity.User;
import org.example.entity.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class RegistryController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserMapper userMapper;

//    用户注册服务
    @PostMapping ("/registry")
    public CResponse registry(@RequestBody UserRegistry userRegistry){
        String smsCode = userRegistry.getSmsCode();
        String uuid = userRegistry.getUuid();
        if (uuid != null && !uuid.isEmpty()){
            String redisCode = stringRedisTemplate.opsForValue().get("smsCode:login"+uuid);
            if(smsCode != null && !smsCode.isEmpty()&& redisCode != null && !redisCode.isEmpty()&&redisCode.equals(smsCode)){
//                不把时间浪费在判断为空上
//                用户UUID
                String userid = UUID.randomUUID().toString();
                //                密码加密
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String pwd = bCryptPasswordEncoder.encode(userRegistry.getPassword());
                User user = new User(userid,userRegistry.getUsername(),22,pwd,userRegistry.getPhone());
                Integer integer = userMapper.insertUser(user);
                if (integer == 0) return new Error(HttpCode.ERROR,"注册失败");
                else return new Success(HttpCode.OK,"注册成功");

            }
            else return new Error(HttpCode.ERROR,"短信验证码输入错误");
        }
        else return new Error(HttpCode.ERROR,"短信验证码输入错误");
    }
//短信验证码服务
    @GetMapping("/smsCode")
    public CResponse getSmsCode(){
        UUID uuid = UUID.fastUUID();
        Random random =new Random();
        String smsCode = "";
        for (int i = 0; i < 6; i++) {
            smsCode+=Integer.valueOf(random.nextInt(10)).toString();
        }
//        手机验证码携带用户唯一ID存储到缓存中,默认缓存时间3分钟
        stringRedisTemplate.opsForValue().set("smsCode:login"+uuid,smsCode,180, TimeUnit.SECONDS);
        HashMap<String,Object> result = new HashMap<>();
        result.put("smsCode",smsCode);
        result.put("uuid",uuid.toString());
        return new Success(HttpCode.OK,result);
    }
}

package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistry {
    private String username;
    private String phone;
    private String password;
    private String smsCode;
//    查验注册短信的唯一ID
    private String uuid;
}

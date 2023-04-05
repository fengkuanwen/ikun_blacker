package org.example.smsLogin;

import org.example.config.MyUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 手机号-短信验证码
 */
public class PhoneSmsTokenGranter extends CustomAbstractTokenGranter {
    private static final String PHONE_SMS = "sms_code";

    private MyUserDetailsService myUserDetailsService;

    public PhoneSmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, MyUserDetailsService  myUserDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, PHONE_SMS);
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String phone = parameters.get("phone");
        String smsCode = parameters.get("sms_code");
        return myUserDetailsService.loadUserByUserPhone(phone,smsCode);
    }
}

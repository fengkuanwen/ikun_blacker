package org.example.smsLogin;

import org.example.config.MyUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

public class PasswordTokenGranter extends CustomAbstractTokenGranter{
    private static final String _Type = "password";

    private MyUserDetailsService myUserDetailsService;

    public PasswordTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, MyUserDetailsService  myUserDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, _Type);
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String username = parameters.get("username");
        return myUserDetailsService.loadUserByUsername(username);
    }
}

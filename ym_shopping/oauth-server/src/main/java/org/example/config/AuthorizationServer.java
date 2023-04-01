package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Resource(name="authorizationServerTokenServicesCustom")
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    //客户端详情服务
    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 随便一个客户端都可以随便接入到它的认证服务吗？
     * 答案是否定的，服务提供商会给批准接入的客户端一个身份，用于接入时的凭据，
     * 有客户端标识和客户端秘钥，在这里配置批准接入的客户端的详细信息。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.inMemory()// 使用in-memory存储
                .withClient("XcWebApp")// client_id
//                .secret("XcWebApp")//客户端密钥
                .secret(new BCryptPasswordEncoder().encode("XcWebApp"))//客户端密钥
                .resourceIds("xuecheng-plus")//资源列表
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")// 该client允许的授权类型authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false)//false跳转到授权页面
                //客户端接收授权码的重定向地址
                .redirectUris("http://ip:8100/api/user");
        /*
                1、get请求获取授权码
                地址: /oauth/authorize?client_id=XcWebApp&response_type=code&scope=all&redirect_uri=http://ip:8100/api/user
                参数列表如下：
                •client_id：客户端准入标识。
                •response_type：授权码模式固定为code。
                •scope：客户端权限。
                •redirect_uri：跳转uri，当授权码申请成功后会跳转到此地址，并在后边带上code参数（授权码）。

                2、请求成功，重定向至http://www.xuecheng-plus.com/?code=授权码，比如：http://www.xuecheng-plus.com/?code=Wqjb5H
                3、使用httpclient工具post申请令牌
                /oauth/token?client_id=XcWebApp&client_secret=XcWebApp&grant_type=authorization_code&code=授权码&redirect_uri=http://ip:8100/api/user
                参数列表如下
                •client_id：客户端准入标识。
                •client_secret：客户端秘钥。
                •grant_type：授权类型，填写authorization_code，表示授权码模式
                •code：授权码，就是刚刚获取的授权码，注意：授权码只使用一次就无效了，需要重新申请。
                •redirect_uri：申请授权码时的跳转url，一定和申请授权码时用的redirect_uri一致。

                4、申请令牌成功如下所示：
                JSON
                {
                  "access_token": "368b1ee7-a9ee-4e9a-aae6-0fcab243aad2",
                  "token_type": "bearer",
                  "refresh_token": "3d56e139-0ee6-4ace-8cbe-1311dfaa991f",
                  "expires_in": 7199,
                  "scope": "all"
                }
                说明：
                1、access_token，访问令牌，用于访问资源使用。
                2、token_type，bearer是在RFC6750中定义的一种token类型，在携带令牌访问资源时需要在head中加入bearer 空格 令牌内容
                3、refresh_token，当令牌快过期时使用刷新令牌可以再次生成令牌。
                4、expires_in：过期时间（秒）
                5、scope，令牌的权限范围，服务端可以根据令牌的权限范围去对令牌授权。
         */
    }

    //令牌端点的访问配置
    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)//认证管理器
                .tokenServices(authorizationServerTokenServices)//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //令牌端点的安全配置
    /**
     * 用来配置令牌端点的安全约束.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security
                .tokenKeyAccess("permitAll()")                    //oauth/token_key是公开
                .checkTokenAccess("permitAll()")                  //oauth/check_token公开
                .allowFormAuthenticationForClients()				//表单认证（申请令牌）
        ;
    }
}
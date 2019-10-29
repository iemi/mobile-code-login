package com.lingyumin.mobilecodelogin.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * [简要描述]: 手机短信验证码验证流程配置
 * [详细描述]:
 *
 * @author:
 * @date: 9:39 AM 2019/10/21
 * @since: JDK 1.8
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        //配置smsCodeAuthenticationFilter成功和失败的处理器
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        //设置SmsCodeAuthenticationProvider的UserDetailsService
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(myUserDetailsService);

        //把smsCodeAuthenticationFilter过滤器添加在UsernamePasswordAuthenticationFilter之前
        builder.authenticationProvider(smsCodeAuthenticationProvider);
        builder.addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

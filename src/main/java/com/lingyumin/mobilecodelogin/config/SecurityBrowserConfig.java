package com.lingyumin.mobilecodelogin.config;

import com.lingyumin.mobilecodelogin.authentication.SmsCodeAuthenticationSecurityConfig;
import com.lingyumin.mobilecodelogin.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * [简要描述]: 浏览器安全配置类
 * [详细描述]:
 *
 * @author:
 * @date: 2:42 PM 2019/10/11
 * @since: JDK 1.8
 */
@Configuration
public class SecurityBrowserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //在UsernamePasswordAuthenticationFilter之前加上验证码过滤器
                .formLogin()
                .loginPage("/mobile-login.html")

                .and()
                .authorizeRequests()
                .antMatchers("/mobile-login.html").permitAll()
                .antMatchers("/code/*").permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .csrf().disable()
                //把SmsCodeAuthenticationSecurityConfig配置加进来
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}

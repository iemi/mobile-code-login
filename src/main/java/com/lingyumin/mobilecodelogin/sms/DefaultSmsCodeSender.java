package com.lingyumin.mobilecodelogin.sms;

import org.springframework.stereotype.Component;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author:
 * @date: 9:44 AM 2019/10/19
 * @since: JDK 1.8
 */
@Component
public class DefaultSmsCodeSender{
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}

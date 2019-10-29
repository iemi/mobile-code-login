package com.lingyumin.mobilecodelogin.sms;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * [简要描述]:短信验证码生成器
 * [详细描述]:
 *
 * @author:
 * @date: 9:39 AM 2019/10/19
 * @since: JDK 1.8
 */
@Component
public class SmsCodeGenerator{
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(4);
        return new ValidateCode(code, 60);
    }
}

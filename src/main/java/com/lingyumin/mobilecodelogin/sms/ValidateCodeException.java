package com.lingyumin.mobilecodelogin.sms;

import org.springframework.security.core.AuthenticationException;

/**
 * [简要描述]: 验证码验证异常
 * [详细描述]:
 *
 * @author:
 * @date: 3:49 PM 2019/10/18
 * @since: JDK 1.8
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

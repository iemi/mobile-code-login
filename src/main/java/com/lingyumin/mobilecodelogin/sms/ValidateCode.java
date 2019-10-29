package com.lingyumin.mobilecodelogin.sms;

import java.time.LocalDateTime;

/**
 * [简要描述]:验证码父类
 * [详细描述]:
 *
 * @author:
 * @date: 5:53 PM 2019/10/18
 * @since: JDK 1.8
 */
public class ValidateCode {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}

package com.lingyumin.mobilecodelogin.controller;

import com.lingyumin.mobilecodelogin.sms.DefaultSmsCodeSender;
import com.lingyumin.mobilecodelogin.sms.SmsCodeGenerator;
import com.lingyumin.mobilecodelogin.sms.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * [简要描述]: 验证码接口
 * [详细描述]:
 *
 * @author:
 * @date: 3:48 PM 2019/10/18
 * @since: JDK 1.8
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;
    @Autowired
    private DefaultSmsCodeSender defaultSmsCodeSender;

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam String mobile) throws IOException {

        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        session.setAttribute(mobile, smsCode);

        defaultSmsCodeSender.send(mobile,smsCode.getCode());
    }
}

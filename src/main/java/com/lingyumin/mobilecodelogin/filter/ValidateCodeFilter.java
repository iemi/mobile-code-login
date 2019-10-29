package com.lingyumin.mobilecodelogin.filter;

import com.lingyumin.mobilecodelogin.sms.ValidateCode;
import com.lingyumin.mobilecodelogin.sms.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * [简要描述]:处理图形验证码过滤器
 * [详细描述]:
 *
 * @author:
 * @date: 3:49 PM 2019/10/18
 * @since: JDK 1.8
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {


        if(StringUtils.equals("/authentication/mobile", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")){
            try {
                validateSmsCode(httpServletRequest,httpServletRequest.getSession());
            }catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    //校验手机验证码
    private void validateSmsCode(HttpServletRequest request, HttpSession session) throws ServletRequestBindingException {
        //请求里的手机号和验证码
        String mobileInRequest = request.getParameter("mobile");
        String codeInRequest = request.getParameter("smsCode");

        ValidateCode codeInSession = (ValidateCode) session.getAttribute(mobileInRequest);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("该手机号未发送验证码");
        }

        if(codeInSession.isExpried()){
            session.removeAttribute(mobileInRequest);
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        session.removeAttribute(mobileInRequest);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

}

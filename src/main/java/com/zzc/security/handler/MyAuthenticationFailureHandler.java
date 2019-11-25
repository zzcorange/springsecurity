package com.zzc.security.handler;

import com.zzc.security.code.ResultJSON;
import com.zzc.security.exception.VerificationCodeException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author chengzi
 * @Date 2019/11/1711:21
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(200);
        PrintWriter out = httpServletResponse.getWriter();
        System.out.println("进来异常处理了。");
        String message = e instanceof com.zzc.security.exception.VerificationCodeException?"验证码错误":"其他错误";
        e.printStackTrace();
        out.write(ResultJSON.FAIL.toJSON(message));
        out.close();

    }
}

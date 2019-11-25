package com.zzc.security.filter;

import com.zzc.security.exception.VerificationCodeException;
import com.zzc.security.handler.MyAuthenticationFailureHandler;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author chengzi
 * @Date 2019/11/1711:16
 */
public class VerifiCationCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException{
        //非登录请求不校验验证码
        System.out.println(httpServletRequest.getRequestURI()+"-->进来验证验证码了");
        if(!"/test/login".equals(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            try {
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }catch (VerificationCodeException v){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,v);
            }
        }
    }
    private void verificationCode(HttpServletRequest httpServletRequest) throws VerificationCodeException{
        String requestCode  = httpServletRequest.getParameter("captcha");
        HttpSession session = httpServletRequest.getSession();
        String saveCode = (String)session.getAttribute("captcha");
        if(!StringUtils.isEmpty(saveCode)){
            session.removeAttribute("captcha");
        }
        if(StringUtils.isEmpty(requestCode)||StringUtils.isEmpty(saveCode)||!requestCode.equals(saveCode)){
            throw  new VerificationCodeException();
        }
    }

}

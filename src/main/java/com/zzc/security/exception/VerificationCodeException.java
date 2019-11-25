package com.zzc.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author chengzi
 * @Date 2019/11/1711:15
 */
public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException(){
        super("图像验证码校验失败");
    }
}

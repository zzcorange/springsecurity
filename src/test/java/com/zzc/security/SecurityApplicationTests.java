package com.zzc.security;

import com.zzc.security.code.ResultJSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class SecurityApplicationTests {

    @Test
    void contextLoads() {
        String url="http://localhost:8080/test/12/test1212&tete";
        String serverAddress = "/test";
        String secondPart = url.substring(url.indexOf("/test")+serverAddress.length());
        System.out.println(secondPart.substring(0,secondPart.indexOf("?")>0?secondPart.indexOf("?"):secondPart.length()));
    }
    @Test
    void testJSONString(){
        System.out.println(ResultJSON.SUCCESS.toJSON("成功啦"));
        System.out.println(ResultJSON.FAIL.toJSON("失败啦"));
    }
    @Test
    void testGetEnum(){
    }


}

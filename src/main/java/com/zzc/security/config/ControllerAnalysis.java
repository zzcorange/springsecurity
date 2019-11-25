package com.zzc.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chengzi
 * @Date 2019/11/1320:42
 * 分析判断是否是发往后台的JSON请求
 * 判断依据，是判断返回的方法是否有@ResponseBody 或者父类Controller是否用了RestController修饰
 */
@Component
public class ControllerAnalysis implements InitializingBean {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    public  static Map<String,Boolean> returnTypeMap = new HashMap<>();
    public  static String serverAddress;
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<RequestMappingInfo, HandlerMethod> map =requestMappingHandlerMapping.getHandlerMethods();
        map.keySet().forEach((requestMappingInfo)->{
            HandlerMethod handlerMethod = map.get(requestMappingInfo);
            boolean isStringReturn = null!=handlerMethod.getMethod().getAnnotation(ResponseBody.class)||null!=handlerMethod.getMethod().getDeclaringClass().getAnnotation(RestController.class);
            requestMappingInfo.getPatternsCondition().getPatterns().forEach((url)->{
                returnTypeMap.put(url,isStringReturn);
            });

        });
        System.out.println("分析JSON返回值结果："+JSON.toJSON(returnTypeMap));
        System.out.println("serverAddress="+serverAddress);
    }
    @Value("${server.servlet.context-path}")
    public void setServerAddress(String serverAddress){
        ControllerAnalysis.serverAddress= serverAddress;
    }
    public boolean isReturnString(String url){
        String secondPart = url.substring(url.indexOf(serverAddress)+serverAddress.length());
        secondPart.substring(0,secondPart.indexOf("?")>0?secondPart.indexOf("?"):secondPart.length());
        return false;
    }
}

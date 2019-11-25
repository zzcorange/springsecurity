package com.zzc.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengzi
 * @Date 2019/11/1320:46
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "success";
    }
    @RequestMapping("/")
    public String black(){
        return "success";
    }

}

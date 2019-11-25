package com.zzc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengzi
 * @Date 2019/11/1710:32
 */
@RestController
@RequestMapping("/app/api")
public class AppController {
    @GetMapping("hello")
    public String hello(){
        return "hello app";
    }
}

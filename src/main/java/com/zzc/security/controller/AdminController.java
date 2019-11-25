package com.zzc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengzi
 * @Date 2019/11/1710:31
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {
    @GetMapping("/hello")
    public String hello(){
        return "hello admin";
    }
}

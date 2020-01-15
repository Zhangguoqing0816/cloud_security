package com.sso.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 16:06
 */
@RestController
public class MyController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello   zuul01";
    }
}

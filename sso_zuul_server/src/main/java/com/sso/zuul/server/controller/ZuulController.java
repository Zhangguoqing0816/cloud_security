package com.sso.zuul.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 15:50
 */
//@RestController
//@RequestMapping("/zuul")
public class ZuulController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello   zuul";
    }
}

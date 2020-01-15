package com.sso.zuul.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/15 11:03
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/home")
    public ModelAndView toProvider(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}

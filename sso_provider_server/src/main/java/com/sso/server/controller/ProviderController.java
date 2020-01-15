package com.sso.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/15 10:32
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @RequestMapping("/toProvider")
    public ModelAndView toProvider(){
        ModelAndView mv = new ModelAndView("provider");
        return mv;
    }
}

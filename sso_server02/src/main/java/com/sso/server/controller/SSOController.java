package com.sso.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sso.server.entity.User;
import com.sso.server.service.UserService;
import com.sso.server.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/13 16:10
 */
@RestController
@RequestMapping("/sso")
@Slf4j
public class SSOController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    /**
     * 进入登录页面
     * @param url
     * @return
     */
    @RequestMapping("/loginPage")
    public ModelAndView loginPage(String url){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("url", url);
        return mv;
    }

    /**
     * 登陆
     * @param response
     * @param username
     * @param password
     * @param url
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletResponse response, String username, String password, String url){
        try{
            String token = checkUserAndPwd(username, password);
            if(StringUtils.isEmpty(token)){
                return "登录失败";
            }
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setMaxAge(60 * 3);
            // 设置域
//            cookie.setDomain("HaHa.cn");
            // 设置访问路径
            cookie.setPath("/");
            //重定向到原先访问的页面
//            url = "http://localhost:8081/sso/loginPage";
            response.addCookie(cookie);
            response.sendRedirect(url);
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "登录失败";
    }

    /**
     * 校验用户名密码，成功则返回令牌
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/checkUserAndPwd")
    public String checkUserAndPwd(String username, String password) {
        //令牌
        String token = null;
        //查看是否有这个 username
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", username);
        User user = userService.getOne(wrapper);
        //获取数据库加密的密码 和 页面的密码进行比较
        if(!Objects.isNull(user)){
            String dbPWD = user.getUserPwd();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(dbPWD);
            if (!passwordEncoder.matches(password, dbPWD)) {
                log.info("用户密码不正确....");
            }else{
                token = username
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                redisUtil.set(token, username, (long)(3 *60));
            }
        }else{
            log.info("没有这个账号....");
        }
        return token;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    @RequestMapping("hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key){
        boolean hasKey = redisUtil.hasKey(key);
        return hasKey;
    }

}

package com.sso.zuul.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 19:08
 */
@FeignClient(name="sso-server")
@RequestMapping("/sso")
public interface SsoFeign {

    @RequestMapping("hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key);

    @RequestMapping("/setToken")
    public Boolean setToken(@RequestParam("key")String key, @RequestParam("value")Object value);

    @RequestMapping("/getToken/{key}")
    public String getToken(@PathVariable("key")String key);

}

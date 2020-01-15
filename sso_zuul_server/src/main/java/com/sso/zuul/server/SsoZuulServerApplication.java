package com.sso.zuul.server;

import com.sso.zuul.server.filter.MyZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableZuulProxy
@EnableFeignClients
public class SsoZuulServerApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(SsoZuulServerApplication.class, args).getEnvironment();
        log.info(" \n sso_zuul_server:{}", environment.getProperty("server.port"));
    }

    @Bean
    public MyZuulFilter myZuulFilter(){
        return new MyZuulFilter();
    }

}

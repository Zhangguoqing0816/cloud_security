package com.sso.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class SsoServerApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(SsoServerApplication.class, args).getEnvironment();
        log.info("\n sso_server:{} 启动完毕", environment.getProperty("server.port"));

    }

}

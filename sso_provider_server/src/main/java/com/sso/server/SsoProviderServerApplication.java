package com.sso.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class SsoProviderServerApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(SsoProviderServerApplication.class, args).getEnvironment();
        log.info("\n provider:{} 启动完毕", environment.getProperty("server.port"));
    }

}

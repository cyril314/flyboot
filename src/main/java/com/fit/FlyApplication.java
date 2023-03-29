package com.fit;

import lombok.extern.slf4j.Slf4j;
import org.flowable.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * SpringBoot方式启动类
 */
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class FlyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(FlyApplication.class, args);
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = application.getEnvironment().getProperty("server.port");

        log.info("\n---------------------------------------------------------\n" +
                "Application Fly-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External:\thttp://" + ip + ":" + port + "/" +
                "\n-----------------页面请部署 admin-web----------------------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FlyApplication.class);
    }
}
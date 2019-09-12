package com.shopping_center.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigServer
@SpringBootApplication
@RestController
public class ShoppingCenterConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCenterConfigApplication.class, args);
    }

}

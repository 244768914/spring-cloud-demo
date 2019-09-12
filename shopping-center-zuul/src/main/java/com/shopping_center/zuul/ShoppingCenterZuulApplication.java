package com.shopping_center.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ShoppingCenterZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCenterZuulApplication.class, args);
    }

}

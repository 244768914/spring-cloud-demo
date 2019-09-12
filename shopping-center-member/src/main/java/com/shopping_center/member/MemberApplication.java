package com.shopping_center.member;

import com.shopping_center.member.controller.BinaryNode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan(basePackages = {"com.shopping_center.member.mapper"})
public class MemberApplication {

    public static void main(String[] args) {

        double f=12d;
        double f1=12d;
        System.out.println(f==f1);

        BinaryNode binaryNode = new BinaryNode();
        BinaryNode node = binaryNode.createNode();
        binaryNode.levelIterator(node);

        SpringApplication.run(MemberApplication.class, args);
    }



}

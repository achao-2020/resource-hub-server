package com.achao.resourcehub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.achao.resourcehub.infrastructure.dao.*.mapper")
@SpringBootApplication
public class ResourceHubServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceHubServerApplication.class, args);
    }
}
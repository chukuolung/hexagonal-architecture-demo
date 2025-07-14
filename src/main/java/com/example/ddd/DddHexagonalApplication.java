package com.example.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DDD Hexagonal Architecture 主應用程式
 * 遵循 Hexagonal Architecture 規則：配置層管理所有技術特定適配器的實例化
 */
@SpringBootApplication
public class DddHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddHexagonalApplication.class, args);
    }
} 
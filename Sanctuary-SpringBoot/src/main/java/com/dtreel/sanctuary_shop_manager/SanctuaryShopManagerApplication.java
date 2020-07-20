package com.dtreel.sanctuary_shop_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dtreel.sanctuary_shop_manager.mapper")
public class SanctuaryShopManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanctuaryShopManagerApplication.class, args);
    }

}

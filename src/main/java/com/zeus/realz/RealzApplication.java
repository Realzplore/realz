package com.zeus.realz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zeus.realz.persistence")
public class RealzApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealzApplication.class, args);
    }

}

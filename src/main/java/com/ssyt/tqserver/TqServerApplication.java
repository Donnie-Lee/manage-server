package com.ssyt.tqserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssyt.tqserver.mapper")
@SpringBootApplication
public class TqServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TqServerApplication.class, args);
    }

}

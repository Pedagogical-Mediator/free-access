package com.example.freeaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FreeAccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeAccessApplication.class, args);
    }

}

package com.ny.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringTransactionalListenersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionalListenersApplication.class, args);
    }

}

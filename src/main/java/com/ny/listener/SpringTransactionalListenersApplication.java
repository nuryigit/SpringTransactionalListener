package com.ny.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class SpringTransactionalListenersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionalListenersApplication.class, args);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

}

package com.ny.listener;

import com.ny.listener.listener.config.ContextAwarePoolExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class SpringTransactionalListenersApplication extends AsyncConfigurerSupport {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionalListenersApplication.class, args);
    }

    @Bean
    public Executor cachedThreadPoolExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ContextAwarePoolExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setQueueCapacity(5);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setThreadNamePrefix("ThreadName-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }

}

package com.ny.listener.listener.config;

import com.ny.listener.listener.decorator.ContextCopyingDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig extends AsyncConfigurerSupport {

    @Override
    @Bean("asyncTaskExecutor")
    public Executor getAsyncExecutor() {
        ContextAwarePoolExecutor taskExecutor = new ContextAwarePoolExecutor();
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("ContextAwareExecutor-");
        return taskExecutor;
    }

}

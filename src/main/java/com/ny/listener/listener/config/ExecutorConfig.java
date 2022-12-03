package com.ny.listener.listener.config;

import com.ny.listener.listener.decorator.ContextAwareExecutorDecorator;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
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
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("contextAwareExecutor-");
        executor.initialize();
        return new ContextAwareExecutorDecorator(executor);
    }


}

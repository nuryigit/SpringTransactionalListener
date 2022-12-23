package com.ny.listener.listener.config;

import com.ny.listener.listener.decorator.ContextAwareExecutorDecorator;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

//@Configuration
//@EnableAsync
public class ExecutorConfig extends AsyncConfigurerSupport {



}

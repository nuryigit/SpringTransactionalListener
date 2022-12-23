package com.ny.listener.listener.config;


import com.ny.listener.listener.decorator.ContextCopyingDecorator;
import com.ny.listener.listener.same.type.bean.CustomObjectMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(value = {"com"})
@EnableWebMvc
@EnableCaching
@EnableConfigurationProperties
@EntityScan(basePackages = {"com"})
@EnableAsync
public class SpringConfiguration {


  @Primary
  @Bean(name = "customObjectMapper")
  public CustomObjectMapper customObjectMapper() {
    CustomObjectMapper mapper = new CustomObjectMapper(1);
    mapper.setId(1);
    mapper.print("customObjectMapper");
    return new CustomObjectMapper();
  }

  @Bean(name = "maskedCustomObjectMapper")
  public CustomObjectMapper maskedCustomObjectMapper() {
    CustomObjectMapper mapper = new CustomObjectMapper(2);
    mapper.print("maskedCustomObjectMapper");
    return mapper;
  }

  @Bean(name = "executorAsyncThread")
  public TaskExecutor getAccountAsyncExecutor() {
    ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
    poolExecutor.setTaskDecorator(new ContextCopyingDecorator());
    poolExecutor.setCorePoolSize(10);
    poolExecutor.setMaxPoolSize(20);
    poolExecutor.setQueueCapacity(80000);
    poolExecutor.setThreadNamePrefix("Async-Executor-");
    poolExecutor.initialize();
    return poolExecutor;
  }



}

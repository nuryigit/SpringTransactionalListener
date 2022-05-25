package com.ny.listener.listener;

import com.transaction.phase.listener.config.SpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Import(value = SpringConfiguration.class)
@ServletComponentScan
@EnableAsync
public class ListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListenerApplication.class, args);
	}

}

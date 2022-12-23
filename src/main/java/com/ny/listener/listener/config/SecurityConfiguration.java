package com.ny.listener.listener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/listener/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/customers/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}

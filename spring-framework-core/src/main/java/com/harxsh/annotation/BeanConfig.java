package com.harxsh.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.harxsh.annotation")
public class BeanConfig {

    @Bean
    public Teacher teacher() {
        return new Teacher();
    }
}

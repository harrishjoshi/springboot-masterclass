package com.harxsh.spring.aop.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.harxsh.spring.aop.core")
@EnableAspectJAutoProxy
public class BeanConfig {
}

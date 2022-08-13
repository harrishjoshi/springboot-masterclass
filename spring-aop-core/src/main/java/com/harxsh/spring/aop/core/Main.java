package com.harxsh.spring.aop.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Homework homework = context.getBean(Homework.class);
        homework.checkHomework("checked");
        homework.checkedDateAndTime();
    }
}

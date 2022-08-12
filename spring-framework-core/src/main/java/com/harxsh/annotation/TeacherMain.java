package com.harxsh.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TeacherMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Teacher teacher = context.getBean(Teacher.class);
        teacher.teaches();
    }
}

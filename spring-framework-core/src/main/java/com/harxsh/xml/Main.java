package com.harxsh.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Principal principal = context.getBean(Principal.class);
        Subject principalSubject = principal.getSubject();

        principal.teaches();
        LOGGER.info("Principal Name: {}", principal.getName());
        LOGGER.info("Subject: {}", principalSubject.getName());
        LOGGER.info("Credit hours: {}", principalSubject.getCreditHours());
        LOGGER.info("\n");

        Teacher teacher = context.getBean(Teacher.class);
        Subject teacherSubject = teacher.getSubject();

        teacher.teaches();
        LOGGER.info("Teacher Name: {}", teacher.getName());
        LOGGER.info("Subject: {}", teacherSubject.getName());
        LOGGER.info("Credit hours: {}", teacherSubject.getCreditHours());
    }
}

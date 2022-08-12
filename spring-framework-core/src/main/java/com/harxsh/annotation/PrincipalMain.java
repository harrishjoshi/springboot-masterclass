package com.harxsh.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrincipalMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalMain.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Principal.class);

        Principal principal = context.getBean(Principal.class);
        principal.setSubject("Java");
        principal.teaches();

        // scope
        LOGGER.info("{}", principal);

        Principal principal2 = context.getBean(Principal.class);
        LOGGER.info("{}", principal2);
    }
}

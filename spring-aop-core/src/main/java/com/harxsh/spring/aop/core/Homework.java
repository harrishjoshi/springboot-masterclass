package com.harxsh.spring.aop.core;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Homework {

    public void checkHomework(String status) {
        // Logging
        // Authentication and Authorization
        // Sanitize the data
        System.out.println("Homework's checkHomework method is called with status: " + status);
    }

    public Date checkedDateAndTime() {
        return new Date();
    }
}

package com.harxsh.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Principal implements Staff {

    private String subject;

    @Override
    public String toString() {
        return "Principal [subject=" + subject + "]";
    }

    public void teaches() {
        System.out.println("Principal teaches");
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

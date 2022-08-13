package com.harxsh.annotation;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class Principal implements Staff, BeanNameAware {

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

    @Override
    public void setBeanName(String name) {
        System.out.println("Set bean name method is called.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post construct method is called.");
    }
}

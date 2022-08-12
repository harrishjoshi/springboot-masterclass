package com.harxsh.xml;

public class Principal implements Staff {

    private String name;
    private Subject subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void teaches() {
        System.out.println("Principal teaches");
    }
}

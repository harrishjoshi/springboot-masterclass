package com.harxsh.xml;

public class Teacher implements Staff {

    private String name;
    private Subject subject;

    public Teacher(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void teaches() {
        System.out.println("Teacher teaches");
    }
}

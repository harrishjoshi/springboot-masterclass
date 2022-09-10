package com.harxsh.spring.data.jpa.controller.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentUtil {

    public static <T> String getFetchMessage(String name, T value) {
        return "Student data with " + name + ": '" + value + "' fetched successfully.";
    }

    public static <T> String dataNotFoundMessage(String name, T value) {
        return "Student data with " + name + ": '" + value + "' not found.";
    }
}

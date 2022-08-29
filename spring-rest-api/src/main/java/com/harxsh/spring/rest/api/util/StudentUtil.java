package com.harxsh.spring.rest.api.util;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class StudentUtil {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isNotNullAndEmpty(List<?> str) {
        return str != null && !str.isEmpty();
    }

    public static <T> List<T> singletonList(T object) {
        return object == null ? null : Collections.singletonList(object);
    }
}

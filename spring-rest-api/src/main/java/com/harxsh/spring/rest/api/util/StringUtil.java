package com.harxsh.spring.rest.api.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}

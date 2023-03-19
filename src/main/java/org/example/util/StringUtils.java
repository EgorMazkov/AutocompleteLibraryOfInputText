package org.example.util;

public class StringUtils {

    private static final int FIRST = 0;

    private static final char EMPTY_CHAR = 0;

    public static Character firstChar(String str) {
        return str.isEmpty() ? EMPTY_CHAR : str.charAt(FIRST);
    }

}

package com.jobsity.tenpinbowlingscore.utils;

public class FileUtils {

    private static final String VALID_LINE_REGEX = "[a-zA-Z]+\\t\\b([0-9]|10|F)\\b";
    public static final String CRLF = "\r\n";

    public static boolean isLineValid(String line) {
        if (line == null) {
            return false;
        }
        return line.matches(VALID_LINE_REGEX);
    }

}

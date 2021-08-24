package com.jobsity.tenpinbowlingscore.validation;

public enum ErrorMessagesEnum {

    FILE_DOES_NOT_EXIST("This file doesn't exist"),
    EMPTY_FILE("The file is empty"),
    CANNOT_READABLE("Cannot read file"),
    INVALID_DATA("The file contains invalid data"),
    EXTRA_SHOTS("Extra shoots are not allowed")
    ;

    private final String errorMessage;

    ErrorMessagesEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

package com.jobsity.tenpinbowlingscore.validor;

import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TextFileValidator {

    List<String> errorMessages = new ArrayList<>();

    public boolean isValid(File file) {
        boolean isValid = false;
        if (file.exists()) {
            if (file.canRead()) {
                if (file.length() != 0) {
                    isValid = true;
                } else {
                    errorMessages.add(ErrorMessagesEnum.EMPTY_FILE.getErrorMessage());
                }
            } else {
                errorMessages.add(ErrorMessagesEnum.CANNOT_READABLE.getErrorMessage());
            }
        } else {
            errorMessages.add(ErrorMessagesEnum.FILE_DOES_NOT_EXIST.getErrorMessage());
        }
        return isValid;
    }

    public String getErrorMessages() {
        return String.join("\n", errorMessages);
    }
}

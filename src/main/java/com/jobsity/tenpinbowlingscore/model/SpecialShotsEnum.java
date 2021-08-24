package com.jobsity.tenpinbowlingscore.model;

public enum SpecialShotsEnum {

    STRIKE("10", 10, "X"),
    FOUL("F", 0, "0");

    private final String valueFromFile;
    private final int score;
    private final String valueForPrint;

    SpecialShotsEnum(String valueFromFile, int score, String valueForPrint) {
        this.valueFromFile = valueFromFile;
        this.score = score;
        this.valueForPrint = valueForPrint;
    }

    public String getValueFromFile() {
        return valueFromFile;
    }

    public int getScore() {
        return score;
    }

    public String getValueForPrint() {
        return valueForPrint;
    }
}

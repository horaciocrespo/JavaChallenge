package com.jobsity.tenpinbowlingscore.utils;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void lineWithPlayerNameAndScoreOfZero_IsValid() {
        Assert.assertTrue(FileUtils.isLineValid("John\t0"));
    }

    @Test
    public void lineWithPlayerNameAndScoreOfFive_IsValid() {
        Assert.assertTrue(FileUtils.isLineValid("John\t5"));
    }

    @Test
    public void lineWithPlayerNameAndScoreOfTen_IsValid() {
        Assert.assertTrue(FileUtils.isLineValid("John\t10"));
    }

    @Test
    public void lineWithPlayerNameAndFoul_IsValid() {
        Assert.assertTrue(FileUtils.isLineValid("John\tF"));
    }

    // Invalid Test Cases

    @Test
    public void nullLine_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid(null));
    }

    @Test
    public void emptyLine_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid(""));
    }

    @Test
    public void lineStartingWithSpaces_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("   John\tF"));
    }

    @Test
    public void lineWithNameOnly_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("John"));
    }

    @Test
    public void lineWithScoreOnly_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("10"));
    }

    @Test
    public void lineWithNegativeScore_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("John\t-1"));
    }

    @Test
    public void lineWithScoreBiggerThanTen_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("John\t11"));
    }

    @Test
    public void lineWithMoreThanTwoWords_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("John\t5\tX"));
    }

    @Test
    public void lineWithPlayerNameAndScore_SpaceSeparated_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("John 5"));
    }

    @Test
    public void lineWithRandomWords_SpaceSeparated_IsInvalid() {
        Assert.assertFalse(FileUtils.isLineValid("Lorem ipsum dolor sit amet"));
    }

}

package com.jobsity.tenpinbowlingscore;

import com.jobsity.tenpinbowlingscore.reader.ScoreReader;
import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;

import java.io.*;

public class TenPinBowlingScorePrinter {

    public static void main(String[] args) {
        if (args[0] != null) {
            String fileName = args[0];
            ScoreReader scoreReader = new ScoreReader();
            try {
                scoreReader.read(
                        new BufferedReader(new StringReader(fileName)),
                        new BufferedWriter(new PrintWriter(System.out)));
            } catch (IOException | IllegalStateException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(ErrorMessagesEnum.MISSING_ARGUMENT.getErrorMessage());
        }
    }

}

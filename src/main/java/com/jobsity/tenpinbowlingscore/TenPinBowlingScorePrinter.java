package com.jobsity.tenpinbowlingscore;

import com.jobsity.tenpinbowlingscore.reader.ScoreReader;

import java.io.*;

public class TenPinBowlingScorePrinter {

    public static void main(String[] args) {
        ScoreReader scoreReader = new ScoreReader();
        try {
            scoreReader.read(
                    new BufferedReader(new InputStreamReader(System.in)),
                    new BufferedWriter(new PrintWriter(System.out)));
        } catch (IOException | IllegalStateException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.jobsity.tenpinbowlingscore.printer;

import com.jobsity.tenpinbowlingscore.model.Game;

import java.io.BufferedWriter;
import java.io.IOException;

public interface ScoreSheetPrinter {

    /**
     * Prints the score sheet to console.
     *
     * @param game is used to get the score sheet data.
     */
    void print(Game game, BufferedWriter writer) throws IOException;

}

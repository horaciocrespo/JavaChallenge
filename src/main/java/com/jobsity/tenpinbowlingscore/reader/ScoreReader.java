package com.jobsity.tenpinbowlingscore.reader;

import com.jobsity.tenpinbowlingscore.model.Game;
import com.jobsity.tenpinbowlingscore.parser.FileParser;
import com.jobsity.tenpinbowlingscore.parser.TextFileParser;
import com.jobsity.tenpinbowlingscore.printer.ClassicSheetPrinter;
import com.jobsity.tenpinbowlingscore.printer.ScoreSheetPrinter;

import java.io.*;

public class ScoreReader {

    public void read(BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        String fileName = bufferedReader.readLine();
        readFromFile(fileName, bufferedWriter);
    }

    private void readFromFile(String fileName, BufferedWriter bufferedWriter) throws IOException {
        FileParser fileParser = new TextFileParser();
        Game game = fileParser.parse(fileName);

        ScoreSheetPrinter printer = new ClassicSheetPrinter();
        printer.print(game, bufferedWriter);
    }

}

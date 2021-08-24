package com.jobsity.tenpinbowlingscore.parser;

import com.jobsity.tenpinbowlingscore.model.Game;
import com.jobsity.tenpinbowlingscore.utils.FileUtils;
import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;
import com.jobsity.tenpinbowlingscore.validor.TextFileValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileParser implements FileParser {

    TextFileValidator textFileValidator;

    public TextFileParser() {
        textFileValidator = new TextFileValidator();
    }

    /**
     * Parses the lines of a file. Each line's words should be delimited by the tab character.
     *
     * @param fileName is the file to be parsed
     * @return a Game object with contains information about the players, frames, etc.
     * @throws IOException if there's any issue reading the file
     */
    @Override
    public Game parse(String fileName) throws IOException {
        File file = new File(fileName);
        if (!textFileValidator.isValid(file)) {
            throw new IOException(textFileValidator.getErrorMessages());
        }

        Path path = Paths.get(file.getAbsolutePath());
        BufferedReader reader = Files.newBufferedReader(path);

        Game game = new Game();
        String line;
        while ((line = reader.readLine()) != null) {

            if (!FileUtils.isLineValid(line)) {
                throw new IOException(ErrorMessagesEnum.INVALID_DATA.getErrorMessage());
            }

            String[] lineArray = line.split("\\t");
            String playerName = lineArray[0];
            String score = lineArray[1];
            game.play(playerName, score);
        }
        game.calculateScores();
        return game;
    }

}

package com.jobsity.tenpinbowlingscore.parser;

import com.jobsity.tenpinbowlingscore.model.Game;

import java.io.IOException;

public interface FileParser {

    Game parse(String fileName) throws IOException;

}

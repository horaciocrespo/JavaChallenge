package com.jobsity.tenpinbowlingscore.printer;

import com.jobsity.tenpinbowlingscore.model.Frame;
import com.jobsity.tenpinbowlingscore.model.Game;
import com.jobsity.tenpinbowlingscore.model.SpecialShotsEnum;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class ClassicSheetPrinter implements ScoreSheetPrinter {

    @Override
    public void print(Game game, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        bufferedWriter.newLine();
        for (String playerName : game.getPlayers().keySet()) {
            bufferedWriter.write(playerName);
            bufferedWriter.newLine();
            bufferedWriter.write("PinFalls");
            for (Frame frame : game.getPlayers().get(playerName).values()) {
                if (frame.isLastFrame()) {
                    if (frame.hasStrike()) {
                        bufferedWriter.write("\t" + "X");
                        bufferedWriter.write("\t" + getCharacterForPrinting(frame.getSecondShot()));
                        bufferedWriter.write("\t" + getCharacterForPrinting(frame.getThirdShot()));
                    } else if (frame.hasSpare()) {
                        bufferedWriter.write("\t" + frame.getFirstShot());
                        bufferedWriter.write("\t" + "/");
                        bufferedWriter.write("\t" + getCharacterForPrinting(frame.getThirdShot()));
                    } else
                        for (Integer score : frame.getScoresList()) {
                            bufferedWriter.write("\t" + score);
                        }
                } else {
                    if (frame.hasStrike()) {
                        bufferedWriter.write("\t\t" + "X");
                    } else if (frame.hasSpare()) {
                        bufferedWriter.write("\t" + frame.getFirstShot());
                        bufferedWriter.write("\t" + "/");
                    } else
                        for (Integer score : frame.getScoresList()) {
                            bufferedWriter.write("\t" + score);
                        }
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("Score");
            for (Frame frame : game.getPlayers().get(playerName).values()) {
                bufferedWriter.write("\t\t" + frame.getTotalScore());
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    private String getCharacterForPrinting(int score) {
        if (SpecialShotsEnum.STRIKE.getScore() == score) {
            return SpecialShotsEnum.STRIKE.getValueForPrint();
        }
        return String.valueOf(score);
    }

}

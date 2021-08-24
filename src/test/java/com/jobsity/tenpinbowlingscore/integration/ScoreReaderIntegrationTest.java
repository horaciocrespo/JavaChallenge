package com.jobsity.tenpinbowlingscore.integration;

import com.jobsity.tenpinbowlingscore.reader.ScoreReader;
import com.jobsity.tenpinbowlingscore.utils.FileUtils;
import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertThrows;

public class ScoreReaderIntegrationTest {

    private ScoreReader scoreReader;

    @Before
    public void setUp() {
        scoreReader = new ScoreReader();
    }

    /* Positive scenarios */

    @Test
    public void canHandlePerfectScoreWithOnePlayer() throws IOException {
        String expected = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10" + FileUtils.CRLF +
                "Carl" + FileUtils.CRLF +
                "PinFalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX" + FileUtils.CRLF +
                "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300" + FileUtils.CRLF;
        StringWriter stringWriter = new StringWriter();
        scoreReader.read(
                new BufferedReader(new StringReader("src/test/resources/positive/perfect.txt")),
                new BufferedWriter(stringWriter));

        Assert.assertEquals(expected, stringWriter.toString());
    }

    @Test
    public void canHandleAMatchBetweenTwoPlayers() throws IOException {
        String expected = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10" + FileUtils.CRLF +
                "Jeff" + FileUtils.CRLF +
                "PinFalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\t0\t6\t\tX\t\tX\tX\t8\t1" + FileUtils.CRLF +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167" + FileUtils.CRLF +
                "John" + FileUtils.CRLF +
                "PinFalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0" + FileUtils.CRLF +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151" + FileUtils.CRLF;
        StringWriter stringWriter = new StringWriter();
        scoreReader.read(
                new BufferedReader(new StringReader("src/test/resources/positive/scores.txt")),
                new BufferedWriter(stringWriter));

        Assert.assertEquals(expected, stringWriter.toString());
    }

    /* Negative scenarios */

    @Test
    public void canHandleEmptyFiles() {
        Exception exception = assertThrows(IOException.class, () -> {
            scoreReader.read(
                    new BufferedReader(new StringReader("src/test/resources/negative/empty.txt")),
                    new BufferedWriter(new PrintWriter(System.out)));
        });

        Assert.assertEquals(ErrorMessagesEnum.EMPTY_FILE.getErrorMessage(), exception.getMessage());
    }

    @Test
    public void canHandleExtraScores() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            scoreReader.read(
                    new BufferedReader(new StringReader("src/test/resources/negative/extra-score.txt")),
                    new BufferedWriter(new PrintWriter(System.out)));
        });

        Assert.assertEquals(ErrorMessagesEnum.EXTRA_SHOTS.getErrorMessage(), exception.getMessage());
    }

    @Test
    public void canHandleRandomWords() {
        Exception exception = assertThrows(IOException.class, () -> {
            scoreReader.read(
                    new BufferedReader(new StringReader("src/test/resources/negative/free-text.txt")),
                    new BufferedWriter(new PrintWriter(System.out)));
        });

        Assert.assertEquals(ErrorMessagesEnum.INVALID_DATA.getErrorMessage(), exception.getMessage());
    }

    @Test
    public void canHandleInvalidScores() {
        Exception exception = assertThrows(IOException.class, () -> {
            scoreReader.read(
                    new BufferedReader(new StringReader("src/test/resources/negative/invalid-score.txt")),
                    new BufferedWriter(new PrintWriter(System.out)));
        });

        Assert.assertEquals(ErrorMessagesEnum.INVALID_DATA.getErrorMessage(), exception.getMessage());
    }

    @Test
    public void canHandleNegativeScores() {
        Exception exception = assertThrows(IOException.class, () -> {
            scoreReader.read(
                    new BufferedReader(new StringReader("src/test/resources/negative/negative.txt")),
                    new BufferedWriter(new PrintWriter(System.out)));
        });

        Assert.assertEquals(ErrorMessagesEnum.INVALID_DATA.getErrorMessage(), exception.getMessage());
    }

}

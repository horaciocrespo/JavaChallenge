package com.jobsity.tenpinbowlingscore.model;

import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;

import java.util.*;

public class Game {

    private final Map<String, Map<Integer, Frame>> players = new HashMap<>();
    private int currentFrame = 0;
    private String firstPlayer;
    private String previousPlayer;

    public void play(String playerName, String score) {
        if (players.get(playerName) == null) {
            initializeGameForPlayer(playerName);
        } else {
            if (startNewFrame(playerName)) {
                addFrame(playerName);
            }
        }

        addShot(playerName, score);
        this.previousPlayer = playerName;
    }

    private boolean startNewFrame(String playerName) {
        // all players already shot
        if (!playerName.equals(previousPlayer)) {
            return true;
        }

        // If its just me
        return players.get(playerName).get(currentFrame).getRemainingShots() == 0;
    }

    // We start a new frame when the first player plays again
    private boolean startNextFrame(String playerName) {
        return playerName.equals(firstPlayer);
    }

    private void initializeGameForPlayer(String playerName) {
        Frame frame = new Frame();
        Map<Integer, Frame> framesMap = new HashMap<>();
        framesMap.put(0, frame);
        players.put(playerName, framesMap);

        if (firstPlayer == null) {
            firstPlayer = playerName;
        }
    }

    private void addFrame(String playerName) {
        if (startNextFrame(playerName)) {
            currentFrame++;
            if (currentFrame > 9) {
                throw new IllegalStateException(ErrorMessagesEnum.EXTRA_SHOTS.getErrorMessage());
            }
        }

        Frame frame = new Frame();
        frame.setLastFrame(currentFrame == 9);
        players.get(playerName).put(currentFrame, frame);
    }

    private int getScoreAsNumericValue(String score) {
        if (SpecialShotsEnum.STRIKE.getValueFromFile().equals(score)) {
            return SpecialShotsEnum.STRIKE.getScore();
        } else if (SpecialShotsEnum.FOUL.getValueFromFile().equals(score)) {
            return SpecialShotsEnum.FOUL.getScore();
        } else {
            return Integer.parseInt(score);
        }
    }

    public void addShot(String playerName, String score) {
        int scoreNum = getScoreAsNumericValue(score);
        players.get(playerName).get(currentFrame).addScore(scoreNum);
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getPreviousPlayer() {
        return previousPlayer;
    }

    public Map<String, Map<Integer, Frame>> getPlayers() {
        return players;
    }

    public void calculateScores() {
        players.keySet().forEach(playerName -> {
            Map<Integer, Frame> frames = players.get(playerName);
            for (Map.Entry<Integer, Frame>  frameEntry : frames.entrySet()) {
                int frameNo = frameEntry.getKey();
                Frame frame = frameEntry.getValue();

                // current
                int frameTotal = frame.getSumOfPins();
                // previous
                if (players.get(playerName).get(frameNo - 1) != null) {
                    frameTotal += players.get(playerName).get(frameNo - 1).getTotalScore();
                }

                if (frame.hasStrike() && !frame.isLastFrame()) {
                    Frame nextFrame = players.get(playerName).get(frameNo + 1);
                    if (frameNo < 8) {
                        if (nextFrame.hasStrike()) {
                            frameTotal += nextFrame.getFirstShot();
                            Frame thirdFrame = players.get(playerName).get(frameNo + 2);
                            frameTotal += thirdFrame.getFirstShot();
                        } else {
                            frameTotal += (nextFrame.getFirstShot() + nextFrame.getSecondShot());
                        }
                    } else {
                        frameTotal += (nextFrame.getFirstShot() + nextFrame.getSecondShot());
                    }
                    frame.setTotalScore(frameTotal);
                } else if (frame.hasSpare()) {
                    Frame nextFrame = players.get(playerName).get(frameNo + 1);
                    frameTotal += nextFrame.getFirstShot();
                }
                frame.setTotalScore(frameTotal);
            }
        });
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", currentFrame=" + currentFrame +
                '}';
    }

}

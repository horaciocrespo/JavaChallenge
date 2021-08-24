package com.jobsity.tenpinbowlingscore.model;

import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> scoresList = new ArrayList<>();
    private int allocatedShots;
    private int remainingShots;
    private boolean isLastFrame;
    private int totalScore;

    void addScore(int score) throws IllegalStateException {
        if (isFirstShoot()) {
            if (SpecialShotsEnum.STRIKE.getScore() == score) {
                this.allocatedShots = 1;
                this.remainingShots = 1;
            } else if (SpecialShotsEnum.FOUL.getScore() == score) {
                this.allocatedShots = 2;
                this.remainingShots = 2;
            } else {
                this.allocatedShots = 2;
                this.remainingShots = 2;
            }
        }

        if (remainingShots == 0) {
            throw new IllegalStateException(ErrorMessagesEnum.EXTRA_SHOTS.getErrorMessage());
        }

        scoresList.add(score);
        this.remainingShots--;

        if (isLastFrame ) {
            if (getSumOfPins() == 10 && allocatedShots == 1 && scoresList.size() == 1) {
                addAdditionalSlot(2);
            } else if (getSumOfPins() == 10 && allocatedShots == 2 && scoresList.size() == 2) {
                addAdditionalSlot(1);
            }
        }
    }

    boolean isFirstShoot() {
        return scoresList.isEmpty();
    }

    public int getSumOfPins() {
        return scoresList.stream().reduce(0, Integer::sum);
    }

    public boolean hasStrike() {
        return scoresList.get(0) == 10;
    }

    public boolean hasSpare() {
        return (scoresList.get(0) + scoresList.get(1)) == 10;
    }

    public int getAllocatedShots() {
        return allocatedShots;
    }

    void addAdditionalSlot(int additionalSlot) {
        this.allocatedShots += additionalSlot;
        this.remainingShots += additionalSlot;
    }

    int getRemainingShots() {
        return remainingShots;
    }

    public int getFirstShot() {
        return scoresList.get(0);
    }

    /**
     * This is only valid for spare
     * @return
     */
    public int getSecondShot() {
        return scoresList.get(1);
    }

    /**
     * This is only valid for the last frame if the player has Strike
     * @return
     */
    public int getThirdShot() {
        return scoresList.get(2);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scoresList=" + scoresList +
                ", availableSlots=" + allocatedShots +
                ", remainingSlots=" + remainingShots +
                '}';
    }

    public boolean isLastFrame() {
        return isLastFrame;
    }

    public void setLastFrame(boolean lastFrame) {
        isLastFrame = lastFrame;
    }

    public List<Integer> getScoresList() {
        return scoresList;
    }
}

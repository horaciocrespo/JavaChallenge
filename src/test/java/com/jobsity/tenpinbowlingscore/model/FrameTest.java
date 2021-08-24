package com.jobsity.tenpinbowlingscore.model;

import com.jobsity.tenpinbowlingscore.validation.ErrorMessagesEnum;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {

    /* Slots allocation */

    @Test
    public void WhenScoringZero_TwoSlotsAreAllocated() {
        Frame frame = new Frame();
        frame.addScore(0);
        Assert.assertEquals(2, frame.getAllocatedShots());
    }

    @Test
    public void WhenScoringTwo_TwoSlotsAreAllocated() {
        Frame frame = new Frame();
        frame.addScore(5);
        Assert.assertEquals(2, frame.getAllocatedShots());
    }

    @Test
    public void WhenScoringTen_OneSlotIsAllocated() {
        Frame frame = new Frame();
        frame.addScore(10);
        Assert.assertEquals(1, frame.getAllocatedShots());
    }

    /* Remaining shots */

    @Test
    public void WhenScoringZero_OneShotRemaining() {
        Frame frame = new Frame();
        frame.addScore(0);
        Assert.assertEquals(1, frame.getRemainingShots());
    }

    @Test
    public void WhenScoringFive_OneShotRemaining() {
        Frame frame = new Frame();
        frame.addScore(5);
        Assert.assertEquals(1, frame.getRemainingShots());
    }

    @Test
    public void WhenScoringTen_ZeroShotsRemaining() {
        Frame frame = new Frame();
        frame.addScore(10);
        Assert.assertEquals(0, frame.getRemainingShots());
    }

    /* Sum of pins */

    @Test
    public void WhenScoringZero_TheSumOfPinsIsZero() {
        Frame frame = new Frame();
        frame.addScore(0);
        Assert.assertEquals(0, frame.getSumOfPins());
    }

    @Test
    public void WhenScoringTen_TheSumOfPinsIsTen() {
        Frame frame = new Frame();
        frame.addScore(10);
        Assert.assertEquals(10, frame.getSumOfPins());
    }

    @Test
    public void WhenScoringTwoAndFive_TheSumOfPinsIsSeven() {
        Frame frame = new Frame();
        frame.addScore(2);
        frame.addScore(5);
        Assert.assertEquals(7, frame.getSumOfPins());
    }

    /* Last Frame */

    @Test
    public void WhenScoringAStrikeInTheLastRound_PlayerGetsAnotherTwoShots() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addScore(10);
        Assert.assertEquals(2, frame.getRemainingShots());
    }

    @Test
    public void WhenScoringSpareInTheLastRound_PlayerGetsAnotherShot() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addScore(7);
        frame.addScore(3);
        Assert.assertEquals(1, frame.getRemainingShots());
    }

    @Test
    public void WhenAddingMoreShotsThanAllowed_IllegalStateExceptionIsThrown() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Frame frame = new Frame();
            frame.setLastFrame(true);
            frame.addScore(10);
            frame.addScore(10);
            frame.addScore(10);
            frame.addScore(5);
        });

        String actualMessage = exception.getMessage();
        assertEquals(ErrorMessagesEnum.EXTRA_SHOTS.getErrorMessage(), actualMessage);
    }

}

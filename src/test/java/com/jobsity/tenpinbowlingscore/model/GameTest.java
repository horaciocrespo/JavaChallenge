package com.jobsity.tenpinbowlingscore.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.play("John", "5");
        game.play("John", "3");
        game.play("Jeff", "10");
    }

    @Test
    public void WhenJohnShotsFirst_Then_HeIsTheFirstPlayer() {
        Assert.assertEquals("John", game.getFirstPlayer());
    }

    @Test
    public void WhenJeffShotsLast_Then_HeIsThePreviousPlayer() {
        Assert.assertEquals("Jeff", game.getPreviousPlayer());
    }

}

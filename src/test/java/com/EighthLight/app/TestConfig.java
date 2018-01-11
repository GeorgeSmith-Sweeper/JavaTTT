package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestConfig {

    @Test
    void gameMode1StartsWithTwoHumans() {

        MockUi ui = new MockUi(new ArrayList(Arrays.asList("1")));
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Player);
    }

    @Test
    void gameMode2StartsWithAHumanAndAi() {

        MockUi ui = new MockUi(new ArrayList(Arrays.asList("2")));
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Ai);
    }

    @Test
    void usersArePromptedOnceToSelectAGameModeWithCorrectInput() {
        MockUi ui = new MockUi(new ArrayList(Arrays.asList("1")));
        Config config = new Config(ui);
        ArrayList players = config.getPlayers();

        assertEquals(Constants.GAME_MODE_PROMPT, ui.getDisplayArgs().get(0));
        assertEquals(1, ui.getNumTimesGetInputCalled());
    }
}

// inputs: String gameMode selected
// output: ArrayList of player instances (HVH = Player, Player)
// HVC = Player, Ai
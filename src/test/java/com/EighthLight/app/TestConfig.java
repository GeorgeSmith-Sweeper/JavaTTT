package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestConfig {

    @Test
    void gameMode1StartsWithTwoHumans() {

        MockUi ui = new MockUi(new ArrayList(Arrays.asList("O", "X", "1")));
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Player);
    }

    @Test
    void gameMode2StartsWithAHumanAndAi() {

        MockUi ui = new MockUi(new ArrayList(Arrays.asList("O", "X", "2")));
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Ai);
    }

    @Test
    void usersArePromptedOnceToSelectAGameModeWithCorrectInput() {
        MockUi ui = new MockUi(new ArrayList(Arrays.asList("O", "X", "1")));
        Config config = new Config(ui);
        ArrayList players = config.getPlayers();

        assertEquals("Pick a symbol for player 1", ui.getDisplayArgs().get(0));
        assertEquals("Pick a symbol for player 2", ui.getDisplayArgs().get(1));
        assertEquals(Constants.GAME_MODE_PROMPT, ui.getDisplayArgs().get(2));
        assertEquals(3, ui.getNumTimesGetInputCalled());
    }

    @Test
    void usersCanSelectAnyTokenWhenSettingUpTheGameWithCorrectInput() {
        MockUi ui = new MockUi(new ArrayList(Arrays.asList("X", "O", "1")));
        Config config = new Config(ui);
        ArrayList symbols = config.getSymbols();

        assertEquals("Pick a symbol for player 1", ui.getDisplayArgs().get(0));
        assertEquals("Pick a symbol for player 2", ui.getDisplayArgs().get(1));
        assertEquals(3, ui.getNumTimesGetInputCalled());
        assertEquals("X", symbols.get(0));
        assertEquals("O", symbols.get(1));
    }

    @Test
    void usersCantSelectSameTokens() {
        MockUi ui = new MockUi(new ArrayList(Arrays.asList("X", "X", "O", "1")));
        Config config = new Config(ui);
        ArrayList symbols = config.getSymbols();

        assertEquals("Pick a symbol for player 1", ui.getDisplayArgs().get(0));
        assertEquals("Pick a symbol for player 2", ui.getDisplayArgs().get(1));
        assertEquals("Symbol already selected, please pick a different symbol.", ui.getDisplayArgs().get(2));

        // ui.display is called
        // ui.getInput is called
        // ui.getDisplayArgs = token prompt msg
    }
}
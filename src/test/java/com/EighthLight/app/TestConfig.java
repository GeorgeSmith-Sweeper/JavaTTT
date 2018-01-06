package com.EighthLight.app;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConfig {

    @Test
    void gameModeIs1IfUserInputIs1 () {
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("1");
        MockUi ui = new MockUi(providedInput);
        Config config = new Config(ui);
        String expectedGameMode = "1";

        config.setGameMode();

        assertEquals(expectedGameMode, config.getGameMode());
    }

    @Test
    void gameModeTo2IfUserInputIs2 () {
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("2");
        MockUi ui = new MockUi(providedInput);
        Config config = new Config(ui);
        String expectedGameMode = "2";

        config.setGameMode();

        assertEquals(expectedGameMode, config.getGameMode());
    }

    @Test
    void setGameModePomptsAgaingIfUserInputIsNot1Or2 () {
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("3");
        providedInput.add("2");

        MockUi ui = new MockUi(providedInput);
        Config config = new Config(ui);
        String expectedGameMode = "2";

        config.setGameMode();

        assertEquals(expectedGameMode, config.getGameMode());
        assertEquals(2, ui.getNumberOfTimesDisplayCalled());
        assertEquals(2, ui.getNumberOfTimesGetInputCalled());
    }

    @Test
    void playersAreCorrectlyMadeForSelectedGameMode() {
        ArrayList<IPlayer> expectedPlayers = new ArrayList<>();
        Player playerOne = new Player("X");
        Ai playerTwo = new Ai("O");
        expectedPlayers.add(playerOne);
        expectedPlayers.add(playerTwo);

        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("2");
        MockUi ui = new MockUi(providedInput);

        Config config = new Config(ui);

        config.setGameMode();
        config.makePlayers();


        assertEquals(expectedPlayers.size(), config.getPlayers().size());
    }
}

package com.EighthLight.app;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConfig {

    @Test
    void gameModePromptSetsGameModeTo1IfUserInputIs1 () {
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("1");
        MockUi ui = new MockUi(providedInput);
        Config config = new Config(ui);
        String expectedGameMode = "1";

        config.setGameMode();

        assertEquals(expectedGameMode, config.getGameMode());
    }

    @Test
    void gameModePromptSetsGameModeTo2IfUserInputIs2 () {
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("2");
        MockUi ui = new MockUi(providedInput);
        Config config = new Config(ui);
        String expectedGameMode = "2";

        config.setGameMode();

        assertEquals(expectedGameMode, config.getGameMode());
    }

//    @Test
//    void gameModePromptSetsGameModeTo2IfUserInputIs2 () {
//        String userInput = "2";
//        String expectedGameMode = "2";
//        MockUi ui = new MockUi(userInput);
//        Config config = new Config(ui);
//
//        config.setGameMode();
//
//        assertEquals(expectedGameMode, config.getGameMode());
//    }
}

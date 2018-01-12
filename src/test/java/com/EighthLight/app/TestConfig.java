package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestConfig {
    private ArrayList<String> defaultUserInputs;
    private ArrayList<String> duplicateSymbolUserInputs;
    private ArrayList<String> gameModeOneUserInputs;
    private ArrayList<String> gameModeTwoUserInputs;
    private ArrayList<String> gameModeIncorrectInput;

    @BeforeEach
    public void setUp() {
        defaultUserInputs = new ArrayList<>(Arrays.asList("X", "O", "1", "3", ""));
        duplicateSymbolUserInputs = new ArrayList<>(Arrays.asList("X", "X", "O", "1", "3"));
        gameModeOneUserInputs = new ArrayList<>(Arrays.asList("X", "O", "1", "3"));
        gameModeTwoUserInputs = new ArrayList<>(Arrays.asList("X", "O", "2", "3"));
        gameModeIncorrectInput = new ArrayList<>(Arrays.asList("X", "O", "4", "1", "3"));
    }

    @Test
    void promptsUserInCorrectOrder() {
        MockUi ui = new MockUi(defaultUserInputs);
        Config config = new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.BOARD_SIZE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
        assertEquals(prompts.size(), ui.getNumTimesGetInputCalled());
    }

    @Test
    void promptsUserInCorrectOrderIfSymbolsAreDuplicates() {
        MockUi ui = new MockUi(duplicateSymbolUserInputs);
        Config config = new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.DUPLICATE_SYMBOL_ERROR_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.BOARD_SIZE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void invalidGameModeSelectionDisplaysAnErrorMessage() {
        MockUi ui = new MockUi(gameModeIncorrectInput);
        Config config = new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.INVALID_GAME_MODE_MSG,
                Constants.BOARD_SIZE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void gameMode1StartsWithTwoHumans() {

        MockUi ui = new MockUi(gameModeOneUserInputs);
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Player);
    }

    @Test
    void gameMode2StartsWithAHumanAndAi() {

        MockUi ui = new MockUi(gameModeTwoUserInputs);
        Config config = new Config(ui);

        ArrayList players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Ai);
    }


    @Test
    void usersCanSelectAnyTokenWhenSettingUpTheGameWithCorrectInput() {
        MockUi ui = new MockUi(defaultUserInputs);
        Config config = new Config(ui);
        ArrayList symbols = config.getSymbols();

        assertEquals("X", symbols.get(0));
        assertEquals("O", symbols.get(1));
    }

    @Test
    void whenAUserSelectsABoardSizeANewBoardIsCreatedWithThatSize() {
        MockUi ui = new MockUi(defaultUserInputs);
        Config config = new Config(ui);
        IBoard board = config.getBoard();
        int boardSize = Integer.parseInt(defaultUserInputs.get(3));
        int boardArea = boardSize*boardSize;

        assertEquals(board.getSpaces().size(), boardArea);
    }
}
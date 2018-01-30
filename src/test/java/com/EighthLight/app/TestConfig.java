package com.EighthLight.app;

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
    private ArrayList<String> boardSizeIncorrectInput;
    private ArrayList<String> playerOrderingTwoUserInputs;
    private ArrayList<String> playerOrderingIncorrectInput;
    private ArrayList<String> aiDifficulty1Input;

    @Test
    void promptsUserInCorrectOrder() {
        defaultUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "1", "1"));
        MockUi ui = new MockUi(defaultUserInputs);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.AI_DIFFICULTY_PROMPT,
                Constants.PLAYER_ORDER_PROMPT
                ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
        assertEquals(prompts.size(), ui.getNumTimesGetInputCalled());
    }

    @Test
    void promptsUserInCorrectOrderIfSymbolsAreDuplicates() {
        duplicateSymbolUserInputs = new ArrayList<>(Arrays.asList("3", "X", "X", "O", "1"));
        MockUi ui = new MockUi(duplicateSymbolUserInputs);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.DUPLICATE_SYMBOL_ERROR_PROMPT,
                Constants.GAME_MODE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void invalidGameModeSelectionDisplaysAnErrorMessage() {
        gameModeIncorrectInput = new ArrayList<>(Arrays.asList("3", "X", "O", "5", "1"));
        MockUi ui = new MockUi(gameModeIncorrectInput);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.INVALID_GAME_MODE_MSG
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void invalidBoardSizeSelectionDisplaysAnErrorMessage() {
        boardSizeIncorrectInput = new ArrayList<>(Arrays.asList("Ten", "3", "X", "O", "1"));
        MockUi ui = new MockUi(boardSizeIncorrectInput);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.INVALID_BOARD_SIZE_MSG,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void invalidPlayerOrderSelectionDisplaysAWarningAndPrompt() {
        playerOrderingIncorrectInput = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "1", "7", "2"));
        MockUi ui = new MockUi(playerOrderingIncorrectInput);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.AI_DIFFICULTY_PROMPT,
                Constants.PLAYER_ORDER_PROMPT,
                Constants.INVALID_CHOICE_PROMPT
        ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void invalidDificultySelectionDisplaysAWarningAndPrompt() {
        playerOrderingIncorrectInput = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "wrong", "1", "1"));
        MockUi ui = new MockUi(playerOrderingIncorrectInput);
        new Config(ui);

        ArrayList<String> prompts = new ArrayList(Arrays.asList(
                Constants.BOARD_SIZE_PROMPT,
                Constants.PLAYER_ONE_SYMBOL_PROMPT,
                Constants.PLAYER_TWO_SYMBOL_PROMPT,
                Constants.GAME_MODE_PROMPT,
                Constants.AI_DIFFICULTY_PROMPT,
                Constants.INVALID_CHOICE_PROMPT,
                Constants.PLAYER_ORDER_PROMPT
                ));
        for (String prompt : prompts ) {
            assertEquals(prompt, ui.getDisplayArgs().get(prompts.indexOf(prompt)));
        }
    }

    @Test
    void usersCanSelectAnyTokenWhenSettingUpTheGameWithCorrectInput() {
        defaultUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "1", "1", "1"));
        MockUi ui = new MockUi(defaultUserInputs);
        Config config = new Config(ui);
        ArrayList symbols = config.getSymbols();

        assertEquals("X", symbols.get(0));
        assertEquals("O", symbols.get(1));
    }

    @Test
    void gameMode1StartsWithTwoHumans() {
        gameModeOneUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "1", "1"));
        MockUi ui = new MockUi(gameModeOneUserInputs);
        Config config = new Config(ui);

        ArrayList<IPlayer> players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Player);
    }

    @Test
    void gameMode2StartsWithAHumanAndAi() {
        gameModeTwoUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "1", "1"));
        MockUi ui = new MockUi(gameModeTwoUserInputs);
        Config config = new Config(ui);

        ArrayList<IPlayer> players = config.getPlayers();
        assertTrue(players.get(0) instanceof Player);
        assertTrue(players.get(1) instanceof Ai);
        assertEquals("X", players.get(0).getSymbol());
        assertEquals("O", players.get(1).getSymbol());
    }

    @Test
    void selecting2ForPlayerOrderLetsPlayerTwoGoFirst() {
        playerOrderingTwoUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "1", "2"));
        MockUi ui = new MockUi(playerOrderingTwoUserInputs);
        Config config = new Config(ui);
        ArrayList<IPlayer> players = config.getPlayers();

        assertTrue(players.get(0) instanceof Ai);
        assertTrue(players.get(1) instanceof Player);
    }

    @Test
    void selecting1ForPlayerDifficultySetsTheAiDifficultlyToEasy() {
        aiDifficulty1Input = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "1", "1"));
        MockUi ui = new MockUi(aiDifficulty1Input);
        Config config = new Config(ui);
        IStrategy difficulty = config.getDifficulty();

        assertEquals("Easy", difficulty);
    }

    @Test
    void selecting2ForPlayerDifficultySetsTheAiDifficultlyToMedium() {
        aiDifficulty1Input = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "2", "1"));
        MockUi ui = new MockUi(aiDifficulty1Input);
        Config config = new Config(ui);
        String difficulty = config.getDifficulty();

        assertEquals("Medium", difficulty);
    }

    @Test
    void selecting3ForPlayerDifficultySetsTheAiDifficultlyToHard() {
        aiDifficulty1Input = new ArrayList<>(Arrays.asList("3", "X", "O", "2", "3", "1"));
        MockUi ui = new MockUi(aiDifficulty1Input);
        Config config = new Config(ui);
        String difficulty = config.getDifficulty();

        assertEquals("Hard", difficulty);
    }

    @Test
    void whenAUserSelectsABoardSizeANewBoardIsCreatedWithThatSize() {
        defaultUserInputs = new ArrayList<>(Arrays.asList("3", "X", "O", "1", "1", "1", "3"));
        MockUi ui = new MockUi(defaultUserInputs);
        Config config = new Config(ui);
        IBoard board = config.getBoard();
        int boardSize = Integer.parseInt(defaultUserInputs.get(0));
        int boardArea = boardSize*boardSize;

        assertEquals(board.getSpaces().size(), boardArea);
    }
}
package com.EighthLight.app;

import com.EighthLight.app.Mocks.MockBoard;
import com.EighthLight.app.Mocks.MockUi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {
    private ArrayList gameIsTiedValues = new ArrayList();
    private ArrayList aPlayerWonValues = new ArrayList();
    private ArrayList boardState = new ArrayList();
    private String correctInput;
    private String incorrectInput;

    @BeforeEach
    private void setUp() {
        correctInput = Constants.CORRECT_INPUT;
        incorrectInput = Constants.INCORRECT_INPUT;
        gameIsTiedValues.add(false);
        aPlayerWonValues.add(false);
    }

    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        MockUi ui = new MockUi(new ArrayList());
        Player playerOne = new Player("X", ui);

        assertEquals("X", playerOne.getSymbol());
    }

    @Test
    void testExpectedMethodsAreCalledWhenMakeMoveIsExecutedWithCorrectInput() {
        ArrayList userInputs = new ArrayList(Arrays.asList(correctInput));
        MockUi ui = new MockUi(userInputs);
        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        Player playerOne = new Player("X", ui);

        playerOne.makeMove(board);

        assertEquals(1, ui.getNumTimesGetInputCalled());
        assertEquals(1, board.getSpaceWithinBoundsArgs().size());
        assertEquals(correctInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(correctInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne.getSymbol(), board.getUpdateSpaceArgs().get(1));
    }

    @Test
    void testExpectedMethodsAreCalledWhenMakeMoveIsExecutedWithIncorrectInput() {
        ArrayList userInputs = new ArrayList(Arrays.asList(incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        Player playerOne = new Player("X", ui);

        playerOne.makeMove(board);

        assertEquals(1, ui.getDisplayArgs().size());
        assertEquals(Constants.INVALID_SPOT_MSG, ui.getDisplayArgs().get(0));
        assertEquals(2, ui.getNumTimesGetInputCalled());
        assertEquals(2, board.getSpaceWithinBoundsArgs().size());
        assertEquals(incorrectInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(correctInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne.getSymbol(), board.getUpdateSpaceArgs().get(1));
    }
}

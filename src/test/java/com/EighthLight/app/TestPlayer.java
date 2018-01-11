package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        String correctInput = Constants.CORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(correctInput));
        MockUi ui = new MockUi(userInputs);

        Player playerOne = new Player("X", ui);

        assertEquals("X", playerOne.getSymbol());
    }

    @Test
    void makeMoveWithCorrectInput() {
        ArrayList gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        ArrayList aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);
        ArrayList boardState = new ArrayList();
        String correctInput = Constants.CORRECT_INPUT;
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
    void makeMoveWithIncorrectInput() {
        ArrayList gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        ArrayList aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);

        ArrayList boardState = new ArrayList();
        String correctInput = Constants.CORRECT_INPUT;
        String incorrectInput = Constants.INCORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(incorrectInput, correctInput));

        MockUi ui = new MockUi(userInputs);
        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        Player playerOne = new Player("X", ui);

        playerOne.makeMove(board);

        assertEquals(2, ui.getNumTimesGetInputCalled());
        assertEquals(2, board.getSpaceWithinBoundsArgs().size());
        assertEquals(incorrectInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(correctInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne.getSymbol(), board.getUpdateSpaceArgs().get(1));
    }
}

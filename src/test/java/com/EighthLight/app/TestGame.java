package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsTheCorrectMethods() {
        String gameMode = "1";
        int size = 3;
        boolean spaceInBounds = true;
        ArrayList<String> providedInput = new ArrayList<>();
        providedInput.add("1");
        ArrayList spaces = new ArrayList(Arrays.asList("O", "X", "X",
                                                       "O", "X", "O",
                                                       "X", "O", "X"));
        MockUi ui = new MockUi(providedInput);
        MockBoard board = new MockBoard(size, spaces, spaceInBounds);
        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start(gameMode);

        assertEquals(true, ui.displayWasCalled());
        assertEquals(true, ui.presentBoardCalled());
        assertEquals(true, ui.inputWasCalled());
        assertEquals(true, board.spaceWithinBoundsWasCalled());
        assertEquals(true, board.gameIsTieWasCalled());
        assertEquals(true, board.updateSpaceWasCalled());
        assertEquals(true, board.hasAPlayerWonWasCalled());
    }
}

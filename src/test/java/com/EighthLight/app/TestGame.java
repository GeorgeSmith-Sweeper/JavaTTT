package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsTheCorrectMethods() {
        int size = 3;
        String providedInput = "1";
        ArrayList spaces = new ArrayList(Arrays.asList("O", "X", "X",
                                                       "O", "X", "O",
                                                       "X", "O", "X"));
        MockUi ui = new MockUi(providedInput);
        MockBoard board = new MockBoard(size, spaces);
        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(true, board.gameIsTieWasCalled());
        assertEquals(true, ui.displayWasCalled());
        assertEquals(true, ui.presentBoardCalled());
        assertEquals(true, ui.inputWasCalled());

    }
}

// if game is tied we expect ui.display(no one wins), and game should end.
// test the game ends look up exit codes! What do they mean. how to mock and exit.
// multiple constructors
// mock board can be used with a passed in board state, assertions can be made about that passed in state!
// assertions on the number of times things are called gameIsTied, ui.
// integration test vs unit test
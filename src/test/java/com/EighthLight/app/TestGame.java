package com.EighthLight.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsTheCorrectMethods() {
        int size = 3;
        MockUi ui;
        ui = new MockUi();
        Board board = new Board(size);
        board.createBoard();
        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        MockGame game = new MockGame(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(true, ui.displayWasCalled());
        assertEquals(true, ui.inputWasCalled());
    }
}

// if game is tied we expect ui.display(no one wins), and game should end.
// test the game ends look up exit codes! What do they mean. how to mock and exit.
// multiple constructors
// mock board can be used with a passed in board state, assertions can be made about that passed in state!
// assertions on the number of times things are called gameIsTied, ui.
// integration test vs unit test
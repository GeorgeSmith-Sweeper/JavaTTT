package com.EighthLight.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsTheCorrectMethods() {
        int size = 3;
        MockUi ui;
        ui = new MockUi(System.in);
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

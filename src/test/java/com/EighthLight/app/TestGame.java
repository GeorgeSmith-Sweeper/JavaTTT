package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void whenStartIsCalledPresentBoardIsCalledWithCurrentBoardWhenGameIsTied() {
        String userInput = "8";
        MockUi ui = new MockUi(userInput);
        ArrayList currentBoard = new ArrayList(Arrays.asList("O", "X", "O",
                                                             "O", "X", "X",
                                                             "X", "O", 8));

        ArrayList updatedBoard = new ArrayList(Arrays.asList("O", "X", "O",
                                                             "O", "X", "X",
                                                             "X", "O", "X"));

        Player playerOne = new Player("X");
        Player playerTwo = new Player("O");
        // MockBoard board = new MockBoard(3, currentBoard, true);
        Board board = new Board(3);
        board.createBoard();
        board.setSpaces(currentBoard);

        Game game = new Game(ui, board, playerOne, playerTwo);

        game.start();

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(currentBoard, ui.getPresentBoardArgs().get(0));
        assertEquals(updatedBoard, ui.getPresentBoardArgs().get(1));

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(ui.presentBoard(currentBoard), ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(ui.presentBoard(updatedBoard), ui.getDisplayArgs().get(3));
    }


//    @Test
//    void whenStartIsCalledTheCurrentBoardIsDisplayed() {
//        String providedInput = "";
//        MockUi ui = new MockUi(providedInput);
//        ArrayList currentBoardState = new ArrayList(Arrays.asList("O", "X", "O",
//                                                                  "O", "X", "X",
//                                                                  "X", "O", "O"));
//
//        String expectedBoard = "The Board";
//        Player playerOne = new Player("X");
//        Player playerTwo = new Player("O");
//        MockBoard board = new MockBoard(3, currentBoardState, true);
//        board.createBoard();
//        Game game = new Game(ui, board, playerOne, playerTwo);
//        game.start();
//
//        assertEquals(expectedBoard, ui.getDisplayArgs());
//    }

}

// check if called with the correct arguments
// check that the arguments have changed when the loops have run more then once
// if game is tied we expect ui.display(no one wins), and game should end.
// test the game ends look up exit codes! What do they mean. how to mock and exit.
// multiple constructors
// mock board can be used with a passed in board state, assertions can be made about that passed in state!
// assertions on the number of times things are called gameIsTied, ui.
// integration test vs unit tests
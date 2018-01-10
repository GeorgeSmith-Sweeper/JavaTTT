package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {
    private MockPlayer playerOne;
    private MockPlayer playerTwo;
    private String correctInput;
    private String incorrectInput;
    private ArrayList boardState;
    private ArrayList gameIsTiedValues;

    @BeforeEach
    private void setUp() {
        playerOne = new MockPlayer("X");
        playerTwo = new MockPlayer("O");
        correctInput = Constants.CORRECT_INPUT;
        incorrectInput = Constants.INCORRECT_INPUT;
        boardState = new ArrayList();
    }

    @Test
    void startGameTiedWithCorrectInput() {

        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);
        MockBoard board = new MockBoard(boardState, false, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList(correctInput));
        MockUi ui = new MockUi(userInputs);
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(1));

        assertEquals(1, ui.getNumTimesGetInputCalled());

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
        assertEquals(correctInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(correctInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));
    }

    @Test
    void startGameTiedWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);

        MockBoard board = new MockBoard(boardState, false, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList(incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(5, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.INVALID_SPOT_MSG, ui.getDisplayArgs().get(2));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(3));
        assertEquals(null, ui.getDisplayArgs().get(4));

        assertEquals(2, ui.getNumTimesGetInputCalled());

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(2, board.getSpaceWithinBoundsArgs().size());
        assertEquals(userInputs.get(0), board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(userInputs.get(1), board.getSpaceWithinBoundsArgs().get(1));
        assertEquals(userInputs.get(1), board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));
    }

    @Test
    void startGameWithWinningStateAndWithCorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);

        MockBoard board = new MockBoard(boardState, true, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList(correctInput));
        MockUi ui = new MockUi(userInputs);
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals("X WINS!", ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(1));

        assertEquals(1, ui.getNumTimesGetInputCalled());

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(1, board.getSpaceWithinBoundsArgs().size());
        assertEquals(correctInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(correctInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));

        assertEquals(1, playerOne.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithWinningStateAndWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);

        MockBoard board = new MockBoard(boardState, true, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList(incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(5, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.INVALID_SPOT_MSG, ui.getDisplayArgs().get(2));
        assertEquals("X WINS!", ui.getDisplayArgs().get(3));
        assertEquals(null, ui.getDisplayArgs().get(4));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(2, ui.getNumTimesGetInputCalled());

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(2, board.getSpaceWithinBoundsArgs().size());
        assertEquals(userInputs.get(0), board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(userInputs.get(1), board.getSpaceWithinBoundsArgs().get(1));
        assertEquals(userInputs.get(1), board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));

        assertEquals(1, playerOne.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithAStateTwoAwayFromTiedAndIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(true);


        MockBoard board = new MockBoard(boardState, false, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList(incorrectInput, correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();

        assertEquals(7, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.INVALID_SPOT_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(4));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(5));
        assertEquals(null, ui.getDisplayArgs().get(6));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(3, ui.getNumTimesGetInputCalled());

        assertEquals(5, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(3, board.getSpaceWithinBoundsArgs().size());
        assertEquals(userInputs.get(0), board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(userInputs.get(1), board.getSpaceWithinBoundsArgs().get(1));
        assertEquals(userInputs.get(2), board.getSpaceWithinBoundsArgs().get(2));

        assertEquals(2, board.getNumTimesUpdateSpaceIsCalled());
        assertEquals(userInputs.get(1), board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));
        assertEquals(userInputs.get(2), board.getUpdateSpaceArgs().get(2));
        assertEquals(playerTwo, board.getUpdateSpaceArgs().get(3));
    }

//    @Test
//    void startGameWithAStateTwoAwayFromTiedAndCorrectInput() {
//        gameIsTiedValues = new ArrayList();
//        gameIsTiedValues.add(false);
//        gameIsTiedValues.add(true);
//
//
//        MockBoard board = new MockBoard(boardState, false, gameIsTiedValues);
//        ArrayList userInputs = new ArrayList(Arrays.asList(correctInput, correctInput));
//        MockUi ui = new MockUi(userInputs);
//        Game game = new Game(ui, board, playerOne, playerTwo);
//        game.start();
//
//        assertEquals(6, ui.getDisplayArgs().size());
//        assertEquals(null, ui.getDisplayArgs().get(0));
//        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
//        assertEquals(null, ui.getDisplayArgs().get(2));
//        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
//        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
//        assertEquals(null, ui.getDisplayArgs().get(5));
//
//        assertEquals(3, ui.getPresentBoardArgs().size());
//        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
//        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
//        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
//
//        assertEquals(2, ui.getNumTimesGetInputCalled());
//
//        assertEquals(5, board.getNumTimesGetSpacesCalled());
//        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
//        assertEquals(boardState, board.getGameTieArgs().get(0));
//
//        assertEquals(2, board.getSpaceWithinBoundsArgs().size());
//        assertEquals(userInputs.get(0), board.getSpaceWithinBoundsArgs().get(0));
//        assertEquals(userInputs.get(1), board.getSpaceWithinBoundsArgs().get(1));
//        assertEquals(userInputs.get(1), board.getUpdateSpaceArgs().get(0));
//        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));
//    }
}
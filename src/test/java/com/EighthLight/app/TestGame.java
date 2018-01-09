package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    public void setBoardState(IBoard board, ArrayList spaces) {
        ArrayList currentSpaces = board.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    @Test
    void startGameTiedWithCorrectInput() {

        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        ArrayList boardState = new ArrayList();
        MockBoard board = new MockBoard(boardState, false, true, true);
        String userInput = Constants.CORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(userInput));

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
        assertEquals(userInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(userInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));
    }

    @Test
    void startGameTiedWithIncorrectInput() {

        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        ArrayList boardState = new ArrayList();
        MockBoard board = new MockBoard(boardState, false, true, false);
        String userInput1 = Constants.INCORRECT_INPUT;
        String userInput2 = Constants.CORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(userInput1, userInput2));

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

        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        ArrayList boardState = new ArrayList();
        MockBoard board = new MockBoard(boardState, true, false, true);
        String userInput = Constants.CORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(userInput));

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
        assertEquals(userInput, board.getSpaceWithinBoundsArgs().get(0));
        assertEquals(userInput, board.getUpdateSpaceArgs().get(0));
        assertEquals(playerOne, board.getUpdateSpaceArgs().get(1));

        assertEquals(1, playerOne.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithWinningStateAndWithIncorrectInput() {

        MockPlayer playerOne = new MockPlayer("X");
        MockPlayer playerTwo = new MockPlayer("O");
        ArrayList boardState = new ArrayList();
        MockBoard board = new MockBoard(boardState, true, false, false);
        String userInput1 = Constants.INCORRECT_INPUT;
        String userInput2 = Constants.CORRECT_INPUT;
        ArrayList userInputs = new ArrayList(Arrays.asList(userInput1, userInput2));

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
}
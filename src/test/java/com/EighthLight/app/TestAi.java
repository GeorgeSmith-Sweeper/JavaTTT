package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAi {

    public void setBoardState(Board board, ArrayList spaces) {
        ArrayList currentSpaces = board.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());
        Ai computer = new Ai("X", "O", "Hard", board);

        assertEquals("X", computer.getSymbol());
    }

    @Test
    void findAllAvailableSpotsReturnsAllEmptySpots() {
        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(false));
        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
        ArrayList boardState = new ArrayList(Arrays.asList(1, "X", 3));
        MockBoard testBoard = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);

        Ai ai = new Ai("X", "O", "Hard", testBoard);

        ArrayList expectedSpaces = new ArrayList(Arrays.asList(1, 3));
        assertEquals(expectedSpaces , ai.findEmptySpaces(testBoard.getSpaces()));
    }

    @Test
    void aiMakesTheRightMoveOneSpotFromWin() {
        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(true));
        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
        ArrayList boardState = new ArrayList(Arrays.asList("X", 1));
        MockBoard testBoard = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);

        Ai ai = new Ai("X", "O", "Hard", testBoard);
        ai.makeMove(testBoard);

        assertEquals("1", testBoard.getUpdateSpaceArgs().get(0));
        assertEquals("X", testBoard.getUpdateSpaceArgs().get(1));
    }

    @Test
    void aiMakesTheRightMoveWithTwoSpotsLeft() {
        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(false, true));
        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
        ArrayList boardState = new ArrayList(Arrays.asList("X", 1, 2,
                                                           "O", "X", "O",
                                                           "X", "O", "O"));
        ArrayList expectedBoardState = new ArrayList(Arrays.asList("X", 1, "X",
                                                           "O", "X", "O",
                                                           "X", "O", "O"));
        Board board = new Board(3);

        Ai ai = new Ai("X", "O", "Hard", board);
        setBoardState(board, boardState);
        ai.makeMove(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

//    @Test
//    void makeMoveUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
//        String symbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Easy";
//        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
//        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());
//
//        Ai computer = new Ai(symbol, humanSymbol, difficulty, board);
//
//        computer.makeMove(board);
//
//        assertEquals(1, board.getNumTimesGetSpacesCalled());
//        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
//        assertEquals("1", board.getUpdateSpaceArgs().get(0));
//        assertEquals(symbol, board.getUpdateSpaceArgs().get(1));
//    }
}

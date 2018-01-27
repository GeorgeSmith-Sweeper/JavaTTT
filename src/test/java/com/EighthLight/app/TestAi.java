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
        assertEquals(expectedSpaces, ai.findEmptySpaces(testBoard.getSpaces()));
    }

    @Test
    void aiMakesTheRightMoveWithTwoSpotsLeft() {
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", 1, 2,
                "O", "X", "O",
                "X", "O", "O"));
        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "X", 1, "X",
                "O", "X", "O",
                "X", "O", "O"));
        Board board = new Board(3);

        Ai ai = new Ai("X", "O", "Hard", board);
        setBoardState(board, boardState);
        ai.makeMove(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiBlocksOpponentWhoIsAboutToWin() {
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", 1, 2,
                "X", "O", 5,
                6, 7, 8));

        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "X", 1, 2,
                "X", "O", 5,
                "O", 7, 8));
        Board board = new Board(3);

        Ai ai = new Ai("O", "X", "Hard", board);
        setBoardState(board, boardState);
        ai.makeMove(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiBlocksDiagonalHumanWin() {
        ArrayList boardState = new ArrayList(Arrays.asList(
                "O", "O", "X",
                  3, "X", 5,
                  6, 7, 8));
        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "O", "O", "X",
                  3, "X", 5,
                "O", 7, 8));
        Board board = new Board(3);

        Ai ai = new Ai("O", "X", "Hard", board);
        setBoardState(board, boardState);
        ai.makeMove(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiValuesAWinOverABlock() {
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                6, 7, 8));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                "X", 7, 8));
        Board board = new Board(3);

        Ai ai = new Ai("X", "O", "Hard", board);
        setBoardState(board, boardState);
        ai.makeMove(board);

        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board() {
        String aiSymbol = "O";
        String humanSymbol = "X";
        String difficulty = "Hard";
        Board board = new Board(4);

        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", "O", "O", "X",
                "O", "X", "O", "X",
                "X", "O", "X", "O",
                "O", "X", 14, 15));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "X", "O", "O", "X",
                "O", "X", "O", "X",
                "X", "O", "X", "O",
                "O", "X", 14, "O"));
        setBoardState(board, boardState);

        Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
        ai.makeMove(board);
        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board9SpotsLeft() {
        String aiSymbol = "O";
        String humanSymbol = "X";
        String difficulty = "Hard";
        Board board = new Board(4);

        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", "O", 2, 3,
                4, "X", "O", 7,
                8, 9, "X", "O",
                12, 13, 14, 15));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "X", "O", 2, 3,
                4, "X", "O", 7,
                8, 9, "X", "O",
                12, 13, 14, "O"));
        setBoardState(board, boardState);

        Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
        ai.makeMove(board);
        assertEquals(expectedBoard, board.getSpaces());
    }

//    @Test
//    void aiCanMakeMoveOnAnEmpty4x4Board() {
//        String aiSymbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Hard";
//        Board board = new Board(4);
//        ArrayList expectedBoard = new ArrayList(Arrays.asList("X",1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
//
//        Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
//        ai.makeMove(board);
//
//        assertEquals(expectedBoard, board.getSpaces());
//    }


    @Test
    void makeMoveUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
        String symbol = "X";
        String humanSymbol = "O";
        String difficulty = "Easy";
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());

        Ai ai = new Ai(symbol, humanSymbol, difficulty, board);

        ai.makeMove(board);

        assertEquals(1, board.getNumTimesGetSpacesCalled());
        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
        assertEquals("1", board.getUpdateSpaceArgs().get(0));
        assertEquals(symbol, board.getUpdateSpaceArgs().get(1));
    }

}

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

//    @Test
//    void getPlayerSymbolReturnsThePlayersSymbol() {
//        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
//        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());
//        Ai computer = new Ai("X", "O", "Hard", board);
//
//        assertEquals("X", computer.getSymbol());
//    }
//
//    @Test
//    void findAllAvailableSpotsReturnsAllEmptySpots() {
//        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(false));
//        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
//        ArrayList boardState = new ArrayList(Arrays.asList(1, "X", 3));
//        MockBoard testBoard = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
//
//        Ai ai = new Ai("X", "O", "Hard", testBoard);
//
//        ArrayList expectedSpaces = new ArrayList(Arrays.asList(1, 3));
//        assertEquals(expectedSpaces, ai.findEmptySpaces(testBoard.getSpaces()));
//    }

//    @Test
//    void aiMakesTheRightMoveOneSpotFromWin() {
//        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(true));
//        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
//        ArrayList boardState = new ArrayList(Arrays.asList("X", 1));
//        MockBoard testBoard = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
//
//        Ai ai = new Ai("X", "O", "Hard", testBoard);
//        ai.makeMove(testBoard);
//
//        assertEquals("1", testBoard.getUpdateSpaceArgs().get(0));
//        assertEquals("X", testBoard.getUpdateSpaceArgs().get(1));
//    }


//    @Test
//    void aiMakesTheRightMoveWithTwoSpotsLeft() {
//        ArrayList boardState = new ArrayList(Arrays.asList("X", 1, 2,
//                                                           "O", "X", "O",
//                                                           "X", "O", "O"));
//        ArrayList expectedBoardState = new ArrayList(Arrays.asList("X", 1, "X",
//                                                                  "O", "X", "O",
//                                                                  "X", "O", "O"));
//        Board board = new Board(3);
//
//        Ai ai = new Ai("X", "O", "Hard", board);
//        setBoardState(board, boardState);
//        ai.makeMove(board);
//
//        assertEquals(expectedBoardState, board.getSpaces());
//    }

    @Test
    void aiMakesTheRightMoveWith5SpotsLefts() {
        ArrayList boardState = new ArrayList(Arrays.asList("X", 1, 2,
                                                           "X", "O", 5,
                                                           6, 7, 8));

        ArrayList expectedBoardState = new ArrayList(Arrays.asList("X", 1, 2,
                                                                  "X", "O", 5,
                                                                  "O", 7, 8));
        Board board = new Board(3);

        Ai ai = new Ai("O", "X", "Hard", board);
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
//        Ai ai = new Ai(symbol, humanSymbol, difficulty, board);
//
//        ai.makeMove(board);
//
//        assertEquals(1, board.getNumTimesGetSpacesCalled());
//        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
//        assertEquals("1", board.getUpdateSpaceArgs().get(0));
//        assertEquals(symbol, board.getUpdateSpaceArgs().get(1));
//    }

//    @Test
//    void theHumanCanBeatTheAi() {
//        String symbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Hard";
//
//        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("X", 1 ,2, "X", "O", 5, "O", 7, 8));
//
//        Board board = new Board(3);
//        setBoardState(board, boardWithOpenSpace);
//
//        Ai ai = new Ai(symbol, humanSymbol, difficulty, board);
//        ai.miniMax(board.getSpaces(), 0, symbol);
//
//    }

//    @Test
//    void theAiIsAlwaysUnbeatableOnHardMode() {
//
//        // play a number of games to completion,
//        // assert that the ai wins each game that is played
//        // tally up the number of wins
//        // create every possible game stateS
//        String aiSymbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Hard";
//        IBoard board = new Board(3);
//
//        Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
//        StateGenerator generator = new StateGenerator(board, ai);
//        generator.generateAllGameStates();
//        generator.playAllStates();
//
//        assertEquals(generator.getNumGamesPlayed(), generator.getNumTimesAiWins());
//    }

 /*   @Test
    void generateStateCanMakeOneState() {

        // play a number of games to completion,
        // assert that the ai wins each game that is played
        // tally up the number of wins
        // create every possible game stateS
        String aiSymbol = "X";
        String humanSymbol = "O";
        String difficulty = "Hard";
        int boardSize = 3;
        IBoard board = new Board(boardSize);

        Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
        StateGenerator generator = new StateGenerator(board, ai, boardSize);
        generator.generateAllGameStates();
        generator.playAllStates();

        assertEquals(1, generator.getNumGamesPlayed());
        assertEquals(1, generator.getNumTimesAiWins());
    }*/

//    @Test
//    void aiMakesTheRightMoveOneSpotFromWin() {
//        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(true));
//        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
//        ArrayList boardState = new ArrayList(Arrays.asList('X','O','X','O','X','O',6,7,8));
//        Board testBoard = new Board(3);
//        setBoardState(testBoard, boardState);
//        Ai ai = new Ai("O", "X", "Hard", testBoard);
//        ai.makeMove(testBoard);
////
////        assertEquals("1", testBoard.getUpdateSpaceArgs().get(0));
////        assertEquals("X", testBoard.getUpdateSpaceArgs().get(1));
//    }
}

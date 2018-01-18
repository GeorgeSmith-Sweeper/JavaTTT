package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAi {

//    @Test
//    void getPlayerSymbolReturnsThePlayersSymbol() {
//        Ai computer = new Ai("X", "O", "Hard");
//
//        assertEquals("X", computer.getSymbol());
//    }
//
//
//    @Test
//    void miniMaxShowsReturnsAHashMap() {
//        String symbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Hard";
//        Ai ai = new Ai(symbol, humanSymbol, difficulty);
//        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
//        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());
//
//        ai.miniMax(boardWithOpenSpace, 1, symbol);
//    }
//
//
//    @Test
//    void findAllAvailableSpotsReturnsAllEmptySpots() {
//        Ai ai = new Ai("X", "O", "Hard");
//        ArrayList aPlayerWonValues = new ArrayList(Arrays.asList(false));
//        ArrayList gameIsTiedValues = new ArrayList(Arrays.asList(false));
//        ArrayList boardState = new ArrayList(Arrays.asList(1, "X", 3));
//        MockBoard testBoard = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
//
//        ArrayList expectedSpaces = new ArrayList(Arrays.asList(1, 3));
//        assertEquals(expectedSpaces , ai.findEmptySpaces(testBoard.getSpaces()));
//    }
//
//    @Test
//    void makeMoveUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
//        String symbol = "X";
//        String humanSymbol = "O";
//        String difficulty = "Easy";
//        Ai computer = new Ai(symbol, humanSymbol, difficulty);
//        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
//        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());
//
//        computer.makeMove(board);
//
//        assertEquals(1, board.getNumTimesGetSpacesCalled());
//        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
//        assertEquals("1", board.getUpdateSpaceArgs().get(0));
//        assertEquals(symbol, board.getUpdateSpaceArgs().get(1));
//    }
}

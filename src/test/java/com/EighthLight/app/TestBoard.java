package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestBoard {

    private Board testBoard;
    private int size;

    @BeforeEach
    public void setUp() {
        size = 3;
        testBoard = new Board(size);
    }

    public void setBoardState(Board testBoard, ArrayList spaces) {
        ArrayList currentSpaces = testBoard.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    @Test
    void createBoardMakesAnArrayFilledWithInts() {
        ArrayList<Integer> expectedBoard = new ArrayList<>(size * size);
        for (int i = 0; i < size*size; i++) {
            expectedBoard.add(i);
        }

        assertEquals(expectedBoard, testBoard.getSpaces());
    }

    @Test
    void spaceWithinBoundsReturnsTrueIfSpaceIsWithinBounds() {
        String userInput = "0";

        assertTrue(testBoard.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfSpaceIsNotWithinBounds() {
        String userInput = "13";

        assertFalse(testBoard.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfInputIsNotANumber() {
        String userInput = "S";

        assertFalse(testBoard.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfInputIsEmpty() {
        String userInput = "";

        assertFalse(testBoard.spaceWithinBounds(userInput));
    }

    @Test
    void updateSpaceUpdatesSpaceWithUserInput() {
        String userInput = "1";
        String userSymbol = "X";
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(0, "X", 2, 3, 4, 5, 6, 7, 8));
        testBoard.updateSpace(userInput, userSymbol);
        assertEquals(expectedBoard, testBoard.getSpaces());
    }

    @Test
    void gameIsTieReturnsTrueIfBoardIsFull() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X"));

        assertTrue(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void gameIsTieReturnsFalseIfBoardIsNotFull() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(0, "X", "X", "X", "X", "X", "X", "X", "X"));

        assertFalse(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void makeWinningRowsAddsAllSpotsToListFor3x3Board() {
        List<ArrayList<Integer>> expectedSpaces = new ArrayList<>();
        ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(0, 1, 2));
        ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(3, 4, 5));
        ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(6, 7, 8));
        expectedSpaces.add(firstRow);
        expectedSpaces.add(secondRow);
        expectedSpaces.add(thirdRow);

        assertEquals(expectedSpaces, testBoard.makeWinningRows());
    }

    @Test
    void makeWinningColumnsAddsAllSpotsToListFor3x3Board() {
        List<ArrayList<Integer>> expectedSpaces = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();
        ArrayList<Integer> firstColumn = new ArrayList<>(Arrays.asList(0, 3, 6));
        ArrayList<Integer> secondColumn = new ArrayList<>(Arrays.asList(1, 4, 7));
        ArrayList<Integer> thirdColumn = new ArrayList<>(Arrays.asList(2, 5, 8));
        expectedSpaces.add(firstColumn);
        expectedSpaces.add(secondColumn);
        expectedSpaces.add(thirdColumn);

        assertEquals(expectedSpaces, testBoard.makeWinningColumns(winningRows));
    }

    @Test
    void makeTopLeftDiagAddsAllSpotsToListFor3x3Board() {
        ArrayList<Integer> expectedSpaces = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();
        expectedSpaces.add(0);
        expectedSpaces.add(4);
        expectedSpaces.add(8);

        assertEquals(expectedSpaces, testBoard.makeTopLeftDiag(winningRows));
    }

    @Test
    void makeBottomLeftDiagAddsAllSpotsToListFor3x3Board() {
        ArrayList<Integer> expectedSpaces = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();
        expectedSpaces.add(6);
        expectedSpaces.add(4);
        expectedSpaces.add(2);

        assertEquals(expectedSpaces, testBoard.makeBottomLeftDiag(winningRows));
    }

    @Test
    void createWinningCombosWillGenerateAllWinStatesForAGiven3x3Board() {
        ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(0, 1, 2));
        ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(3, 4, 5));
        ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(6, 7, 8));
        ArrayList<Integer> firstColumn = new ArrayList<>(Arrays.asList(0, 3, 6));
        ArrayList<Integer> secondColumn = new ArrayList<>(Arrays.asList(1, 4, 7));
        ArrayList<Integer> thirdColumn = new ArrayList<>(Arrays.asList(2, 5, 8));
        ArrayList<Integer> firstDiagonal = new ArrayList<>(Arrays.asList(0, 4, 8));
        ArrayList<Integer> secondDiagonal = new ArrayList<>(Arrays.asList(6, 4, 2));
        ArrayList<ArrayList<Integer>> allWinningConditions = new ArrayList<ArrayList<Integer>>(Arrays.asList(firstRow,
                secondRow,
                thirdRow,
                firstColumn,
                secondColumn,
                thirdColumn,
                firstDiagonal,
                secondDiagonal));


        assertEquals(allWinningConditions, testBoard.getWinningCombos());
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByRow3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X",
                                                                "O", "O", 5,
                                                                 6, 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByColumn3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "O",
                                                                "X", 4, 5,
                                                                "X", 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByDiagonal3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "O",
                                                                3, "X", 5,
                                                                6, 7, "X"));
        MockPlayer playerOne = new MockPlayer("X");
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByAlternateDiagonal3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("0", "O", "X",
                                                                 3, "X", 5,
                                                                "X", 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsFalseWhenAPlayerDoesntWin3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "X",
                                                                "O", "O", 5,
                                                                 6, 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        setBoardState(testBoard, expectedBoard);

        assertEquals(false, testBoard.hasAPlayerWon(testBoard.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByRow4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X", "X",
                                                                "O", "O", 6, 7,
                                                                 8, 9, 10, 11,
                                                                 12, 13, 14, 15));
        MockPlayer playerOne = new MockPlayer("X");
        Board board = new Board(4);
        setBoardState(board, expectedBoard);

        assertEquals(true, board.hasAPlayerWon(board.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByColumn4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", 1, 2, 3,
                                                                "X", "O", 6, 7,
                                                                 "X", 9, 10, 11,
                                                                 "X", 13, 14, 15));
        MockPlayer playerOne = new MockPlayer("X");
        Board board = new Board(4);
        setBoardState(board, expectedBoard);

        assertEquals(true, board.hasAPlayerWon(board.getSpaces(), playerOne.getSymbol()));
    }

    @Test
    void hasAPlayerOneReturnsTrueWhenAPlayerWinsByDiag4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", 1, 2, 3,
                                                                4, "X", 6, 7,
                                                                 8, 9, "X", 11,
                                                                 12, 13, 14, "X"));
        MockPlayer playerOne = new MockPlayer("X");
        Board board = new Board(4);
        setBoardState(board, expectedBoard);

        assertEquals(true, board.hasAPlayerWon(board.getSpaces(), playerOne.getSymbol()));
    }
}

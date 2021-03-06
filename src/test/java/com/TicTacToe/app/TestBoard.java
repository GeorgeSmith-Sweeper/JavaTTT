package com.TicTacToe.app;

import com.TicTacToe.app.Mocks.MockPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestBoard {

    private Board testBoard;
    private int size;
    private MockPlayer player1 = new MockPlayer("X");
    private Board board4x4 = new Board(4);
    private ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(0, 1, 2));
    private ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(3, 4, 5));
    private ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(6, 7, 8));
    private ArrayList<Integer> firstColumn = new ArrayList<>(Arrays.asList(0, 3, 6));
    private ArrayList<Integer> secondColumn = new ArrayList<>(Arrays.asList(1, 4, 7));
    private ArrayList<Integer> thirdColumn = new ArrayList<>(Arrays.asList(2, 5, 8));
    private ArrayList<Integer> firstDiagonal = new ArrayList<>(Arrays.asList(0, 4, 8));
    private ArrayList<Integer> secondDiagonal = new ArrayList<>(Arrays.asList(6, 4, 2));


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
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                0, "X", 2,
                3, 4, 5,
                6, 7, 8));
        testBoard.updateSpace(userInput, userSymbol);
        assertEquals(expectedBoard, testBoard.getSpaces());
    }

    @Test
    void gameIsTieReturnsTrueIfBoardIsFull() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                "X", "O", "X",
                "O", "X", "O",
                "O", "X", "O"));

        assertTrue(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void gameIsTieReturnsFalseIfBoardIsNotFull() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                0, "O", "X",
                "O", "X", "O",
                "O", "X", "O"));

        assertFalse(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void findAllAvailableSpotsReturnsAllEmptySpots() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(1, "X", 3));
        setBoardState(testBoard, boardState);
        ArrayList expectedSpaces = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(expectedSpaces, testBoard.findEmptySpaces(testBoard.getSpaces()));
    }

    @Test
    void makeWinningRowsAddsAllSpotsToListFor3x3Board() {
        List<ArrayList<Integer>> expectedSpaces = new ArrayList<>();
        expectedSpaces.add(firstRow);
        expectedSpaces.add(secondRow);
        expectedSpaces.add(thirdRow);

        assertEquals(expectedSpaces, testBoard.makeWinningRows());
    }

    @Test
    void makeWinningColumnsAddsAllSpotsToListFor3x3Board() {
        List<ArrayList<Integer>> expectedSpaces = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();
        expectedSpaces.add(firstColumn);
        expectedSpaces.add(secondColumn);
        expectedSpaces.add(thirdColumn);

        assertEquals(expectedSpaces, testBoard.makeWinningColumns(winningRows));
    }

    @Test
    void makeTopLeftDiagAddsAllSpotsToListFor3x3Board() {
        ArrayList<Integer> expectedSpaces = new ArrayList<>(Arrays.asList(0, 4, 8));
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();

        assertEquals(expectedSpaces, testBoard.makeTopLeftDiag(winningRows));
    }

    @Test
    void makeBottomLeftDiagAddsAllSpotsToListFor3x3Board() {
        ArrayList<Integer> expectedSpaces = new ArrayList<>(Arrays.asList(6, 4, 2));
        List<ArrayList<Integer>> winningRows = testBoard.makeWinningRows();

        assertEquals(expectedSpaces, testBoard.makeBottomLeftDiag(winningRows));
    }

    @Test
    void createWinningCombosWillGenerateAllWinStatesForAGiven3x3Board() {
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
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByRow3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X",
                                                                "O", "O", 5,
                                                                 6, 7, 8));
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByColumn3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "O",
                                                                "X", 4, 5,
                                                                "X", 7, 8));
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByDiagonal3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "O",
                                                                3, "X", 5,
                                                                6, 7, "X"));
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByAlternateDiagonal3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("0", "O", "X",
                                                                 3, "X", 5,
                                                                "X", 7, 8));
        setBoardState(testBoard, expectedBoard);

        assertEquals(true, testBoard.hasAPlayerWon(testBoard.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsFalseWhenAPlayerDoesntWin3x3() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "O", "X",
                                                                "O", "O", 5,
                                                                 6, 7, 8));
        setBoardState(testBoard, expectedBoard);

        assertEquals(false, testBoard.hasAPlayerWon(testBoard.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByRow4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X", "X",
                                                                "O", "O", 6, 7,
                                                                 8, 9, 10, 11,
                                                                 12, 13, 14, 15));
        setBoardState(board4x4, expectedBoard);

        assertEquals(true, board4x4.hasAPlayerWon(board4x4.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByColumn4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", 1, 2, 3,
                                                                "X", "O", 6, 7,
                                                                 "X", 9, 10, 11,
                                                                 "X", 13, 14, 15));
        setBoardState(board4x4, expectedBoard);

        assertEquals(true, board4x4.hasAPlayerWon(board4x4.getSpaces(), player1.getSymbol()));
    }

    @Test
    void hasAPlayerWonReturnsTrueWhenAPlayerWinsByDiag4x4() {
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("X", 1, 2, 3,
                                                                4, "X", 6, 7,
                                                                 8, 9, "X", 11,
                                                                 12, 13, 14, "X"));
        setBoardState(board4x4, expectedBoard);

        assertEquals(true, board4x4.hasAPlayerWon(board4x4.getSpaces(), player1.getSymbol()));
    }
}

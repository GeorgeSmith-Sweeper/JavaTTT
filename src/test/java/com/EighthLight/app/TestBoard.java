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
        testBoard.createBoard();
    }

    @Test
    void createBoardMakesAnArrayFilledWithInts() {
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
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
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(Arrays.asList(0, "X", 2, 3, 4, 5, 6, 7, 8));
        MockPlayer playerOne = new MockPlayer(userSymbol);
        testBoard.updateSpace(userInput, playerOne);
        assertEquals(expectedBoard, testBoard.getSpaces());
    }

    @Test
    void gameIsTieReturnsTrueIfBoardIsFull() {
        ArrayList<String> expectedBoard = new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X"));

        assertTrue(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void gameIsTieReturnsFalseIfBoardIsNotFull() {
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(Arrays.asList(0, "X", "X", "X", "X", "X", "X", "X", "X"));

        assertFalse(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void winningRowsAddsAllSpotsToListFor3x3Board() {
        List<ArrayList<Integer>> expectedSpaces = new ArrayList<>();
        ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(0, 1, 2));
        ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(3, 4, 5));
        ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(6, 7, 8));
        expectedSpaces.add(firstRow);
        expectedSpaces.add(secondRow);
        expectedSpaces.add(thirdRow);

        assertEquals(expectedSpaces, testBoard.makeWinningRows());
    }

//    @Test
//    void winningColumnsAddsAllSpotsToListFor3x3Board() {
//        List<Integer> expectedSpaces = new ArrayList<>();
//        for (int space = 0; space < size; space++) {
//            int secondNum = space + size;
//            int thirdNum = secondNum + size;
//            expectedSpaces.add(space);
//            expectedSpaces.add(secondNum);
//            expectedSpaces.add(thirdNum);
//        }
//        assertEquals(expectedSpaces, testBoard.makeWinningColumns());
//    }

//    @Test
//    void winningColumnsAddsAllSpotsToListFor4x4Board() {
//        List<Integer> expectedSpaces = new ArrayList<>();
//        int size = 4;
//        Board fourByFour = new Board(size);
//        fourByFour.createBoard();
//
//        for (int space = 0; space < size; space++) {
//            int secondNum = space + size;
//            int thirdNum = secondNum + size;
//            int fourthNum = thirdNum + size;
//            expectedSpaces.add(space);
//            expectedSpaces.add(secondNum);
//            expectedSpaces.add(thirdNum);
//            expectedSpaces.add(fourthNum);
//        }
//        assertEquals(expectedSpaces, fourByFour.makeWinningColumns());
//    }
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

        testBoard.setWinningCombos();

        assertEquals(allWinningConditions, testBoard.getWinningCombos());
    }

//    @Test
//    void createWinningCombosWillGenerateAllWinStatesForAGiven4x4Board() {
//        int size = 4;
//        Board fourByFour = new Board(size);
//        fourByFour.createBoard();
//
//
//        ArrayList allWinningConditions = new ArrayList(Arrays.asList(firstRow,
//                secondRow,
//                thirdRow,
//                fourthRow,
//                firstColumn,
//                secondColumn,
//                thirdColumn,
//                fourthColumn,
//                firstDiagonal,
//                secondDiagonal));
//
//        fourByFour.setWinningCombos();
//
//        assertEquals(allWinningConditions, fourByFour.getWinningCombos());
//    }
}

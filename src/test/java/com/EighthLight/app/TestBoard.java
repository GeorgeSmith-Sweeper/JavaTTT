package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList expectedBoard = new ArrayList(Arrays.asList(0, "X", 2, 3, 4, 5, 6, 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        testBoard.updateSpace(userInput, playerOne);
        assertEquals(expectedBoard, testBoard.getSpaces());
    }

    @Test
    void gameIsTieReturnsTrueIfBoardIsFull() {
        ArrayList expectedBoard = new ArrayList(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X"));

        assertTrue(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void gameIsTieReturnsFalseIfBoardIsNotFull() {
        ArrayList expectedBoard = new ArrayList(Arrays.asList(0, "X", "X", "X", "X", "X", "X", "X", "X"));

        assertFalse(testBoard.gameIsTie(expectedBoard));
    }

    @Test
    void createWinningCombosWillGenerateAllWinStatesForAGiven3x3Board() {
        ArrayList firstRow = new ArrayList(Arrays.asList(0, 1, 2));
        ArrayList secondRow = new ArrayList(Arrays.asList(3, 4, 5));
        ArrayList thirdRow = new ArrayList(Arrays.asList(6, 7, 8));
        ArrayList firstColumn = new ArrayList(Arrays.asList(0, 3, 6));
        ArrayList secondColumn = new ArrayList(Arrays.asList(1, 4, 7));
        ArrayList thirdColumn = new ArrayList(Arrays.asList(2, 5, 8));
        ArrayList firstDiagonal = new ArrayList(Arrays.asList(0, 4, 8));
        ArrayList secondDiagonal = new ArrayList(Arrays.asList(2, 4, 6));
        ArrayList allWinningConditions = new ArrayList(Arrays.asList(firstRow,
                secondRow,
                thirdRow,
                firstColumn,
                secondColumn,
                thirdColumn,
                firstDiagonal,
                secondDiagonal));

        assertEquals(allWinningConditions, testBoard.createWinningCombos());
    }

}

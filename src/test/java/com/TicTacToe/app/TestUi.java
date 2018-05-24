package com.TicTacToe.app;

import com.TicTacToe.app.Ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestUi {

    private Ui testUi;

    @BeforeEach
    public void setUp() {
        testUi = new Ui(System.in);
    }

    @Test
    void display() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String message = "Hello";
        testUi.display(message);

        assertEquals(message + "\n", outputStream.toString());
    }

    @Test
    void getInput() throws IOException {
        String userInput = "Hello";
        byte[] userInputBytes = userInput.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInputBytes);
        Ui ui = new Ui(inputStream);

        assertEquals(userInput, ui.getInput());
    }

    @Test
    void whenAPlayerHasWonTheirSymbolIsDisplayedInAWinningMessage() {
        String winningPlayerSymbol = "X";
        String expectedMsg = "X WINS!";

        assertEquals(expectedMsg, testUi.displayWinningMsg(winningPlayerSymbol));
    }

    @Test
    void aDividerMatchesTheWidthOfTheBoard4x4() {
        String expectedDivider = "---------------";
        String rowString = " 0 | 1 | 2 | 3 \n";

        assertEquals(expectedDivider, testUi.makeDivider(rowString));
    }

    @Test
    void aDividerMatchesTheWidthOfTheBoard3x3() {
        String expectedDivider = "-----------";
        String rowString = " 0 | 1 | 2 \n";

        assertEquals(expectedDivider, testUi.makeDivider(rowString));
    }

    @Test
    void spacesLongerThen1CharLoseRightPadding() {
        int space = 0;
        String expectedSpace = " 10";
        ArrayList spaces = new ArrayList<>(Arrays.asList("10"));

        assertEquals(expectedSpace, testUi.formatSpace(spaces, space));
    }

    @Test
    void spacesShorterThen2CharArePaddedWithSpaces() {
        int space = 0;
        String expectedSpace = " 9 ";
        ArrayList spaces = new ArrayList<>(Arrays.asList("9"));

        assertEquals(expectedSpace, testUi.formatSpace(spaces, space));
    }

    @Test
    void presentBoardDisplaysABoardWithPlaceholderNumbers() {
        int size = 4;
        Board testBoard = new Board(size);
        String expectedBoard =
                               " 0 | 1 | 2 | 3 \n" +
                               "---------------\n" +
                               " 4 | 5 | 6 | 7 \n" +
                               "---------------\n" +
                               " 8 | 9 | 10| 11\n" +
                               "---------------\n" +
                               " 12| 13| 14| 15\n";


        assertEquals(expectedBoard, testUi.presentBoard(testBoard.getSpaces()));
    }

    @Test
    void presentBoardDisplaysAPlayersMove() {
        int size = 4;
        Board testBoard = new Board(size);
        testBoard.updateSpace("1", "X");
        String expectedBoard =
                        " 0 | X | 2 | 3 \n" +
                        "---------------\n" +
                        " 4 | 5 | 6 | 7 \n" +
                        "---------------\n" +
                        " 8 | 9 | 10| 11\n" +
                        "---------------\n" +
                        " 12| 13| 14| 15\n";

        assertEquals(expectedBoard, testUi.presentBoard(testBoard.getSpaces()));
    }
}
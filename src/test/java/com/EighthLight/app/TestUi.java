package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TestUi {

    private Board testBoard;
    private int size;
    private Ui testUi;

    @BeforeEach
    public void setUp() {
        size = 3;
        testUi = new Ui(System.in);
        testBoard = new Board(size);
        testBoard.createBoard();
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
    void presentBoardConvertsTheBoardSpacesToAString() {
        String expectedBoard = "0 | 1 | 2\n" +
                               "=========\n" +
                               "3 | 4 | 5\n" +
                               "=========\n" +
                               "6 | 7 | 8\n";

        assertEquals(expectedBoard, testUi.presentBoard(testBoard.getSpaces()));
    }

    @Test
    void presentBoardDisplaysAPlayersMove() {
        testBoard.updateSpace("1", "X");

        String expectedBoard = "0 | X | 2\n" +
                               "=========\n" +
                               "3 | 4 | 5\n" +
                               "=========\n" +
                               "6 | 7 | 8\n";

        assertEquals(expectedBoard, testUi.presentBoard(testBoard.getSpaces()));
    }
}
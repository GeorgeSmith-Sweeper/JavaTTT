package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAi {
    private Board testBoard;
    private int size;

    @BeforeEach
    public void setUp() {
        size = 3;
        testBoard = new Board(size);
        testBoard.createBoard();
    }

    @Test
    void pickSpotRandomlySelectsASpotFromAvailableBoardSpaces() {
        String playerSymbol = "X";
        Ai ai = new Ai(playerSymbol);

        String selectedSpot = ai.pickSpotRandomly(testBoard);
        int expectedSpot = Integer.parseInt(selectedSpot);
        assertTrue(testBoard.getSpaces().contains(expectedSpot));
    }
}

package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHardAi {

    @Test
    void getSymbolReturnsTheAiSymbol() {
        HardAi hardAi = new HardAi("X", "O");

        assertEquals("X", hardAi.getSymbol());
    }

    @Test
    void getHumanSymbolReturnsTheHumansSymbol() {
        HardAi hardAi = new HardAi("X", "O");

        assertEquals("O", hardAi.getHumanSymbol());
    }

    @Test
    void makeMoveUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        HardAi hardAi = new HardAi(aiSymbol, humanSymbol);
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());

        hardAi.makeMove(board);

        assertEquals(1, board.getNumTimesGetSpacesCalled());
        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
        assertEquals("1", board.getUpdateSpaceArgs().get(0));
        assertEquals(aiSymbol, board.getUpdateSpaceArgs().get(1));
    }

}

package com.EighthLight.app;

import com.EighthLight.app.Mocks.MockDifficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAi {

    @Test
    void getSymbolReturnsTheAiSymbol() {
        String aiSymbol = "X";
        EasyDifficulty easy = new EasyDifficulty(aiSymbol);
        Ai ai = new Ai(aiSymbol, easy);

        assertEquals("X", ai.getSymbol());
    }

    @Test
    void makeMoveCallsMarkBoard() {
        String aiSymbol = "X";
        Board board = new Board(3);
        MockDifficulty easyMock = new MockDifficulty();
        Ai ai = new Ai(aiSymbol, easyMock);

        ai.makeMove(board);

        assertEquals(1, easyMock.getNumTimesMarkBoardCalled());
    }
}

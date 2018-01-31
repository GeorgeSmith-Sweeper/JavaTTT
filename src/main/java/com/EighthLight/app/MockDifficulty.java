package com.EighthLight.app;

import com.EighthLight.app.Interfaces.IBoard;
import com.EighthLight.app.Interfaces.IStrategy;

public class MockDifficulty implements IStrategy {
    private int numTimesMarkBoardCalled = 0;

    @Override
    public void markBoard(IBoard board) {
        numTimesMarkBoardCalled++;
    }

    public int getNumTimesMarkBoardCalled() {
        return numTimesMarkBoardCalled;
    }
}

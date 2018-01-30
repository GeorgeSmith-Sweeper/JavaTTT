package com.EighthLight.app;

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

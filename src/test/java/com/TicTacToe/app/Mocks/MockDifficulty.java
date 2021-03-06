package com.TicTacToe.app.Mocks;

import com.TicTacToe.app.Interfaces.IBoard;
import com.TicTacToe.app.Interfaces.IStrategy;

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

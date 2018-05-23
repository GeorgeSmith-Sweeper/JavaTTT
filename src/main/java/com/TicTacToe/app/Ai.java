package com.TicTacToe.app;

import com.TicTacToe.app.Interfaces.IBoard;
import com.TicTacToe.app.Interfaces.IPlayer;
import com.TicTacToe.app.Interfaces.IStrategy;

public class Ai implements IPlayer {
    private String aiSymbol;
    private IStrategy difficulty;

    public Ai(String aiSymbol, IStrategy difficulty) {
        this.aiSymbol = aiSymbol;
        this.difficulty = difficulty;
    }

    public String getSymbol() {
        return this.aiSymbol;
    }

    public void makeMove(IBoard board) {
        difficulty.markBoard(board);
    }
}


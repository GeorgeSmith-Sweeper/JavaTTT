package com.EighthLight.app;

import com.EighthLight.app.Interfaces.IBoard;
import com.EighthLight.app.Interfaces.IPlayer;
import com.EighthLight.app.Interfaces.IStrategy;

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


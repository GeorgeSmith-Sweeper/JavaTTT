package com.EighthLight.app;

import java.util.*;

public class Ai implements IPlayer{
    private String aiSymbol;
    private String humanSymbol;
    private IStrategy difficulty;
    private IBoard ourBoard;

    public Ai(String aiSymbol, String humanSymbol, IStrategy difficulty, IBoard ourBoard) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
        this.difficulty = difficulty;
        this.ourBoard = ourBoard;
    }

    public String getSymbol() {
        return this.aiSymbol;
    }

    public void makeMove(IBoard board) {
        difficulty.markBoard(board);
    }
}


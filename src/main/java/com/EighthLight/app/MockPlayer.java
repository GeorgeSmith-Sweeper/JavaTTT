package com.EighthLight.app;

import java.util.ArrayList;

public class MockPlayer implements IPlayer {
    private String symbol;
    private int numTimesGetSymbolCalled;
    private ArrayList makeMoveArgs = new ArrayList();

    public MockPlayer(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        numTimesGetSymbolCalled++;
        return symbol;
    }

    public void makeMove(IBoard board) {
        makeMoveArgs.add(board);
    }

    public int getNumTimesGetSymbolCalled() {
        return numTimesGetSymbolCalled;
    }

    public ArrayList getMakeMoveArgs() {
        return makeMoveArgs;
    }
}

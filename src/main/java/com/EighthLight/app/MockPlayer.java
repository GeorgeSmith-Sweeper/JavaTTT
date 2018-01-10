package com.EighthLight.app;

public class MockPlayer implements IPlayer {
    private String symbol;
    private int numTimesGetSymbolCalled;

    public MockPlayer(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        numTimesGetSymbolCalled++;
        return symbol;
    }

    public int getNumTimesGetSymbolCalled() {
        return numTimesGetSymbolCalled;
    }
}

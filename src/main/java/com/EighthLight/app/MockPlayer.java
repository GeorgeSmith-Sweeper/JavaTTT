package com.EighthLight.app;

public class MockPlayer implements IPlayer {
    private String symbol;
    private boolean pickSpotsRandomlyCalled = false;
    private int numTimesPickSpotsRandomlyCalled = 0;

    public MockPlayer(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String pickSpotRandomly(IBoard board) {
        pickSpotsRandomlyCalled = true;
        numTimesPickSpotsRandomlyCalled++;
        return null;
    }

    public boolean pickSpotsRandomlyWasCalled() {
        return pickSpotsRandomlyCalled;
    }

    public int getNumTimesPickSpotsRandomlyCalled() {
        return numTimesPickSpotsRandomlyCalled;
    }
}

package com.EighthLight.app;

public class Player implements IPlayer {
    private String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String pickSpotRandomly(IBoard board) {
        return null;
    }
}

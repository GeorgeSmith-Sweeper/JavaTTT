package com.EighthLight.app;

public class Ai implements IPlayer{

    private String symbol;

    public Ai(String symbol) {
        this.symbol = symbol;
    }

    public int pickSpotRandomly(IBoard board) {
        int min = 0;
        int max = board.getSpaces().size();
        int range = (max - min);
        int randomChoice = (int)(Math.random() * range) + min;

        while(!(board.getSpaces().get(randomChoice) instanceof Integer)) {
            randomChoice = (int)(Math.random() * range) + min;
        }
        return randomChoice;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void makeMove(IBoard board) {

    }
}

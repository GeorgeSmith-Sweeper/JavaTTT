package com.EighthLight.app;

public class Ai extends Player{

    public Ai(String symbol) {
        super(symbol);
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
}

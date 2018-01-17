package com.EighthLight.app;

import java.util.ArrayList;

public class Ai implements IPlayer{
    private String symbol;
    private String humanSymbol;
    private String difficulty;

    public Ai(String symbol, String humanSymbol, String difficulty) {
        this.symbol = symbol;
        this.humanSymbol = humanSymbol;
        this.difficulty = difficulty;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public ArrayList findEmptySpaces(IBoard board) {
        ArrayList availableSpaces = new ArrayList();
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                availableSpaces.add(space);
            }
        }
        return availableSpaces;
    }


    public void makeMove(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), this.symbol);
                break;
            }
        }
    }


}

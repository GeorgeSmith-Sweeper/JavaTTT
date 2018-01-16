package com.EighthLight.app;

public class Ai implements IPlayer{
    private String symbol;

    public Ai(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
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

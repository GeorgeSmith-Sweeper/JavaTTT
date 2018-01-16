package com.EighthLight.app;

public class HardAi implements IPlayer{
    private String symbol;

    public HardAi(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

//    public int miniMax(IBoard board) {
//        return 2;
//    }

    public void makeMove(IBoard board) {

//        int space = miniMax(board);
//        board.updateSpace(space.toString(), this.symbol);
    }
}

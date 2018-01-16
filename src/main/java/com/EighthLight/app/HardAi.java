package com.EighthLight.app;

import java.util.ArrayList;

public class HardAi implements IPlayer{
    private String symbol;
    private String humanSymbol;

    public HardAi(String symbol, String humanSymbol) {

        this.symbol = symbol;
        this.humanSymbol = humanSymbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getHumanSymbol() {
        return this.humanSymbol;
    }

    public int miniMax(ArrayList spaces) {

        Object boardState = spaces.clone();

        // find all of the open spaces on the board
//        ArrayList emptySpaces = new ArrayList();
//        for (Object space : board.getSpaces()) {
//            if (space instanceof Integer) {
//                emptySpaces.add(space);
//            }
//        }

        // make a move on the board for the empty space
//        for (space : emptySpaces) {
//
//        }


        // base case
//        if (ai has won) {
//            +10 to the move
//        }
//        if (human has won) {
//            -10 to the move
//        }
//        else {
//            +0 to the move
//        }

        // store the move and it's value in a collection

        // return the move.get(bestMove);
        return 1;
    }

    public void makeMove(IBoard board) {

        int space = miniMax(board.getSpaces());
        board.updateSpace(Integer.toString(space), this.symbol);
    }
}

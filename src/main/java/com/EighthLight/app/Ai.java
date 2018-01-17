package com.EighthLight.app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList findEmptySpaces(ArrayList board) {
        ArrayList availableSpaces = new ArrayList();
        for (Object space : board) {
            if (space instanceof Integer) {
                availableSpaces.add(space);
            }
        }
        return availableSpaces;
    }

//    public int miniMax(IBoard board, String currentPlayer) {
//        // create a copy of the board to work with inside of minimax
//        ArrayList boardCopy = new ArrayList();
//        boardCopy = board.getSpaces();
//
//        ArrayList avalibleSpaces = findEmptySpaces(boardCopy);
//        ArrayList moves = new ArrayList();
//        HashMap move = new HashMap();
//        move.put("score", -10);
//        move.put("score", +10);
//        move.put("score", 0);
//
//
//        for (int space = 0; space < avalibleSpaces.size(); space++) {
//
//        }
//
//        // find the best move
//        // base cases
//        if (currentPlayer.equals(humanSymbol) && board.hasAPlayerWon(currentPlayer)) {
//            return -10;
//        } else if (currentPlayer.equals(symbol) && board.hasAPlayerWon(currentPlayer)) {
//            return 10;
//        } else {
//            return 0;
//        }
//
//        //
//
//    }


    public void makeMove(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), this.symbol);
                break;
            }
        }
    }


}

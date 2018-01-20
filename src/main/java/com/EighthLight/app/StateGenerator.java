package com.EighthLight.app;

import java.util.ArrayList;
import java.util.Arrays;

public class StateGenerator {

    private IBoard board;
    private Ai ai;
    private ArrayList<ArrayList> allBoardStates = new ArrayList<>();
    private int numGamesPlayed;
    private int numTimesAiWins;
    private int boardSize;


    public StateGenerator(IBoard board, Ai ai, int boardSize) {
        this.board = board;
        this.ai = ai;
        this.boardSize = boardSize;
    }

//    public void generateAllGameStates() {
////        ArrayList state = new ArrayList(Arrays.asList(0,1,2,3,4,5,6,7,8));
//        // loop
//        // boardSize
//
////        for (int state = 0; state < (int) Math.pow(boardSize, boardSize*boardSize); state++) {
////            int position = state;
////            for (int space = 0; space < boardSize*boardSize; j++) {
////
////            }
////        }
//        allBoardStates.add(state);
//    }


    public void setBoardState(IBoard testBoard, ArrayList spaces) {
        ArrayList currentSpaces = testBoard.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    public void playAllStates() {
        for (ArrayList state : allBoardStates) {
            numGamesPlayed++;

            // play?
            String aiSymbol = "X";
            String humanSymbol = "O";
            String difficulty = "Hard";
            IBoard board = new Board(boardSize);
            Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
            setBoardState(board, state);

            ai.makeMove(board);
        }
    }


    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }

    public int getNumTimesAiWins() {
        return numTimesAiWins;
    }
}

//        for(int i = 0; i < 19683; i++) {
////            int c = i;
////            for (int j = 0; j < 9; j++) {
////                System.out.print((c % 3));
////                c /= 3;
////            }
////        }
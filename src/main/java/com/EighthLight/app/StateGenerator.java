//package com.EighthLight.app;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class StateGenerator {
//
//    private IBoard board;
//    private Ai ai;
//    private ArrayList<ArrayList> allBoardStates = new ArrayList<>();
//    private int numGamesPlayed;
//    private int numTimesAiWins;
//    private int boardSize;
//
//
//    public StateGenerator(IBoard board, Ai ai, int boardSize) {
//        this.board = board;
//        this.ai = ai;
//        this.boardSize = boardSize;
//    }
//
//    public void generateAllGameStates() {
//        allBoardStates.add(state);
//    }
//
//
//
//
//    public void playAllStates() {
//        for (ArrayList state : allBoardStates) {
//            numGamesPlayed++;
//
//            // play?
//            String aiSymbol = "X";
//            String humanSymbol = "O";
//            String difficulty = "Hard";
//            IBoard board = new Board(boardSize);
//            Ai ai = new Ai(aiSymbol, humanSymbol, difficulty, board);
//            setBoardState(board, state);
//
//            ai.makeMove(board);
//        }
//    }
//
//
//    public int getNumGamesPlayed() {
//        return numGamesPlayed;
//    }
//
//    public int getNumTimesAiWins() {
//        return numTimesAiWins;
//    }
//}

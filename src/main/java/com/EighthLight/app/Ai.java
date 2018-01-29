package com.EighthLight.app;

import java.util.*;

public class Ai implements IPlayer{
    private String aiSymbol;
    private String humanSymbol;
    private String difficulty;
    private IBoard ourBoard;

    public Ai(String aiSymbol, String humanSymbol, String difficulty, IBoard ourBoard) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
        this.difficulty = difficulty;
        this.ourBoard = ourBoard;
    }

    public String getSymbol() {
        return this.aiSymbol;
    }

    public int findBestMove(Map<Integer, Integer>scoredSpaces)  {
        int bestSpace = 0;
        int highestScore = -1000;

        for (Map.Entry<Integer, Integer> entry : scoredSpaces.entrySet()) {
            int space = entry.getKey();
            int score = entry.getValue();
            if (score >= highestScore) {
                highestScore = score;
                bestSpace = space;
            }
        }
        return bestSpace;
    }

    public int heuristicValue(ArrayList boardState, int depth) {
        if (ourBoard.hasAPlayerWon(boardState, aiSymbol)) {
            return 1000 / depth;
        } else if (ourBoard.hasAPlayerWon(boardState, humanSymbol)) {
            return -1000 / depth;
        } else {
            return 0;
        }
    }

    private int miniMax(ArrayList boardState, int depth, int pointOfView, int alpha, int beta) {
        String playerWhoMovesNow = pointOfView == 1 ? humanSymbol : aiSymbol;
        ArrayList newBoard = new ArrayList();
        newBoard.addAll(boardState);
        ArrayList<Integer> emptySpaces = ourBoard.findEmptySpaces(boardState);
        if (gameIsOver(boardState) || depth == 0) {
            return pointOfView * heuristicValue(boardState, depth);
        }

        int bestValue = 1000;

        for (int space : emptySpaces) {
            newBoard.set(space, playerWhoMovesNow);
            int value = -miniMax(newBoard, depth - 1, -pointOfView, -beta, -alpha);
            newBoard.set(space, space);
            bestValue = Integer.min(bestValue, value);
        }
        return bestValue;
    }

    private boolean gameIsOver(ArrayList boardState) {
        if (ourBoard.gameIsTie(boardState)) {
            return true;
        }
        if (ourBoard.hasAPlayerWon(boardState, aiSymbol)) {
            return true;
        }
        if (ourBoard.hasAPlayerWon(boardState, humanSymbol)) {
            return true;
        }
        return false;
    }

    private void easyMode(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), this.aiSymbol);
                break;
            }
        }
    }

    private void hardMode(IBoard board) {
        ArrayList<Integer> initialSpaces = ourBoard.findEmptySpaces(board.getSpaces());
        Map<Integer, Integer> scoredSpaces = new HashMap<>();
        int depth = board.getSpaces().size();
        int pointOfView = 1;
        int alpha = -1000;
        int beta = 1000;

        for (int space : initialSpaces) {
            ArrayList newBoard = new ArrayList();
            newBoard.addAll(board.getSpaces());
            newBoard.set(space, aiSymbol);
            int value = miniMax(newBoard, depth, pointOfView, alpha, beta);
            scoredSpaces.put(space, value);
        }
        int bestMove = findBestMove(scoredSpaces);
        board.updateSpace(Integer.toString(bestMove), this.aiSymbol);
    }

    public void makeMove(IBoard board) {
        if (difficulty.equals("Easy")) {
            easyMode(board);
        }
        if (difficulty.equals("Medium")) {
            easyMode(board);
        }
        if (difficulty.equals("Hard")) {
            hardMode(board);
        }
    }
}


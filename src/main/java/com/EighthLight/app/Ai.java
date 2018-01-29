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

    public ArrayList<Integer> findEmptySpaces(ArrayList board) {
        ArrayList<Integer> emptySpaces = new ArrayList();
        for (Object space : board) {
            if (space instanceof Integer) {
                emptySpaces.add((Integer) space);
            }
        }
        return emptySpaces;
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

    public int heuristicValue(ArrayList boardState, int depth, String player) {
        if (ourBoard.hasAPlayerWon(boardState, player) && player.equals(aiSymbol)) {
            return 1000 / depth;
        } else if (ourBoard.gameIsTie(boardState)) {
            return 0;
        } else {
            return -1000 / depth;
        }
    }

    public int miniMax(ArrayList boardState, int depth, int pointOfView, String playerWhoJustMoved, int alpha, int beta) {
        String playerWhoMovesNow = playerWhoJustMoved.equals(aiSymbol) ? humanSymbol : aiSymbol;
        ArrayList<Integer> emptySpaces = findEmptySpaces(boardState);

        if (gameIsOver(boardState) || depth == 0) {
            return pointOfView * heuristicValue(boardState, depth, playerWhoJustMoved);
        }

        int bestValue = 0;

        for (int space : emptySpaces) {
            ArrayList newBoard = new ArrayList();
            newBoard.addAll(boardState);
            newBoard.set(space, playerWhoMovesNow);
            int value = -miniMax(newBoard, depth - 1, -pointOfView, playerWhoMovesNow, -beta, -alpha);
//            newBoard.set(space, space);
            bestValue = Integer.min(bestValue, value);
            alpha = Integer.min(alpha, value);
            if (alpha <= beta) {
                return alpha;
            }
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

    public void easyMode(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), this.aiSymbol);
                break;
            }
        }
    }

    public void makeMove(IBoard board) {
        if (difficulty.equals("Easy")) {
            easyMode(board);
        }
        if (difficulty.equals("Medium")) {
            easyMode(board);
        }
        if (difficulty.equals("Hard")) {
            ArrayList<Integer> initialSpaces = findEmptySpaces(board.getSpaces());
            Map<Integer, Integer> scoredSpaces = new HashMap<>();
            int depth = initialSpaces.size();
            int pointOfView = 1;
            int alpha = 1000;
            int beta = -1000;

            for (int space : initialSpaces) {
                ArrayList newBoard = new ArrayList();
                newBoard.addAll(board.getSpaces());
                newBoard.set(space, aiSymbol);
                int value = miniMax(newBoard, depth, pointOfView, aiSymbol, alpha, beta);
                scoredSpaces.put(space, value);
            }
            System.out.println(scoredSpaces);
            int bestMove = findBestMove(scoredSpaces);
            board.updateSpace(Integer.toString(bestMove), this.aiSymbol);
        }
    }
}


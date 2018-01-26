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
            if (score > highestScore) {
                highestScore = score;
                bestSpace = space;
            }
        }
        return bestSpace;
    }

    public int heuristicValue(ArrayList boardState, int depth) {
        if (ourBoard.hasAPlayerWon(boardState, aiSymbol)) {
            return 1000 + depth;
        } else {
            return -1000 + depth;
        }
    }

    public int miniMax(ArrayList boardState, int depth, int pointOfView) {
        String currentPlayer = pointOfView == 1 ? humanSymbol : aiSymbol;
        ArrayList<Integer> emptySpaces = findEmptySpaces(boardState);

        if (depth == 0 || ourBoard.hasAPlayerWon(boardState, currentPlayer)) {
            return pointOfView * heuristicValue(boardState, depth);
        } else if (emptySpaces.size() == 0) {
            return 0;
        }

        int bestValue = -1000;

        for (int space : emptySpaces) {
            ArrayList newBoard = new ArrayList();
            newBoard.addAll(boardState);
            newBoard.set(space, currentPlayer);
            int value = -miniMax(newBoard, depth - 1, -pointOfView);
//            newBoard.set(space, space);
            bestValue = Integer.max(bestValue, value);
        }
        return bestValue;


//        ArrayList newBoard = new ArrayList();
//        newBoard.addAll(boardState);
//        ArrayList<Integer> emptySpaces = findEmptySpaces(newBoard);
//        String playerWhoIsMoving = playerWhoMadeLastMove.equals(aiSymbol) ? humanSymbol : aiSymbol;
//
//        if (ourBoard.hasAPlayerWon(newBoard, playerWhoIsMoving)) {
//            return (-1000 + depth);
//        } else if (ourBoard.hasAPlayerWon(newBoard, playerWhoMadeLastMove)) {
//            return (1000 - depth);
//        } else if (emptySpaces.size() == 0) {
//            return 0;
//        }
//
//        int max = -1000;
//
//        for (int space : emptySpaces) {
//            newBoard.set(space, playerWhoIsMoving);
//            int score = -miniMax(newBoard, playerWhoIsMoving, depth - 1, -beta, -alpha, -pointOfView);
//            newBoard.set(space, space);
//
//            if (score > max) {
//                max = score;
//            }
//            if (score > alpha) {
//                alpha = score;
//            }
//            if (alpha >= beta) {
//                break;
//            }
//        }
//        return max;
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

            for (int space : initialSpaces) {
                ArrayList newBoard = new ArrayList();
                newBoard.addAll(board.getSpaces());
                newBoard.set(space, this.aiSymbol);
                int value = miniMax(newBoard, depth, pointOfView);
                scoredSpaces.put(space, value);
            }
            System.out.println(scoredSpaces);
            int bestMove = findBestMove(scoredSpaces);
            board.updateSpace(Integer.toString(bestMove), this.aiSymbol);
        }
    }
}


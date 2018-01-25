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


    public int miniMax(ArrayList boardState, String playerWhoMadeLastMove, int depth, int alpha, int beta) {
        ArrayList newBoard = new ArrayList();
        newBoard.addAll(boardState);
        ArrayList<Integer> emptySpaces = findEmptySpaces(newBoard);
        String playerWhoIsMoving = playerWhoMadeLastMove.equals(aiSymbol) ? humanSymbol : aiSymbol;
        ArrayList<Integer> scores = new ArrayList<>();

        if (ourBoard.hasAPlayerWon(newBoard, playerWhoIsMoving)) {
            return -1000 + depth;
        } else if (ourBoard.hasAPlayerWon(newBoard, playerWhoMadeLastMove)) {
            return 1000 - depth;
        } else if (emptySpaces.size() == 0) {
            return 0;
        }

        int max = -1000;

        for (int space : emptySpaces) {
            newBoard.set(space, playerWhoIsMoving);
            int score = miniMax(newBoard, playerWhoIsMoving, depth+1, -beta, -alpha);
            scores.add(score);
            newBoard.set(space, space);

            if (score > max) {
                max = score;
            }
////            if (depth == 0) {
////                scores.add(max);
////            }
            if (max > alpha) {
                alpha = max;
            }
            if (alpha >= beta) {
                return alpha;
            }
        }
        return -max;
    }

    public int getMaxScore (ArrayList<Integer> scores) {
        int maxScore = Collections.max(scores);
        scores.clear();
        return -maxScore;
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
            int depth = 0;
            int alpha = -1000;
            int beta = 1000;

            for (int space : initialSpaces) {
                ArrayList newBoard = new ArrayList();
                newBoard.addAll(board.getSpaces());
                newBoard.set(space, this.aiSymbol);
                int score = miniMax(newBoard, this.aiSymbol, depth, alpha, beta);
                scoredSpaces.put(space, score);
            }
            System.out.println(scoredSpaces);
            int bestMove = findBestMove(scoredSpaces);
            board.updateSpace(Integer.toString(bestMove), this.aiSymbol);
        }
    }
}


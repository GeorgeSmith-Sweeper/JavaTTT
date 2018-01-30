package com.EighthLight.app;

import com.EighthLight.app.Interfaces.IBoard;
import com.EighthLight.app.Interfaces.IStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HardDifficulty implements IStrategy {

    private String aiSymbol;
    private String humanSymbol;

    public HardDifficulty(String aiSymbol, String humanSymbol) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
    }

    public void markBoard(IBoard board) {
        ArrayList<Integer> initialSpaces = board.findEmptySpaces(board.getSpaces());
        Map<Integer, Integer> scoredSpaces = new HashMap<>();
        int depth = board.getSpaces().size();
        int pointOfView = 1;

        for (int space : initialSpaces) {
            ArrayList newBoard = new ArrayList();
            newBoard.addAll(board.getSpaces());
            newBoard.set(space, aiSymbol);
            int value = miniMax(newBoard, depth, pointOfView, board);
            scoredSpaces.put(space, value);
        }
        int bestMove = findBestMove(scoredSpaces);
        board.updateSpace(Integer.toString(bestMove), aiSymbol);
    }

    private int miniMax(ArrayList boardState, int depth, int pointOfView, IBoard board) {
        String playerWhoMovesNow = pointOfView == 1 ? humanSymbol : aiSymbol;
        ArrayList newBoard = new ArrayList();
        newBoard.addAll(boardState);
        ArrayList<Integer> emptySpaces = board.findEmptySpaces(boardState);
        if (gameIsOver(boardState, board) || depth == 0) {
            return pointOfView * heuristicValue(boardState, depth, board);
        }

        int bestValue = 1000;

        for (int space : emptySpaces) {
            newBoard.set(space, playerWhoMovesNow);
            int value = -miniMax(newBoard, depth - 1, -pointOfView, board);
            newBoard.set(space, space);
            bestValue = Integer.min(bestValue, value);
        }
        return bestValue;
    }

    private boolean gameIsOver(ArrayList boardState, IBoard board) {
        if (board.gameIsTie(boardState)) {
            return true;
        }
        if (board.hasAPlayerWon(boardState, aiSymbol)) {
            return true;
        }
        if (board.hasAPlayerWon(boardState, humanSymbol)) {
            return true;
        }
        return false;
    }

    private int heuristicValue(ArrayList boardState, int depth, IBoard board) {
        if (board.hasAPlayerWon(boardState, aiSymbol)) {
            return 1000 / depth;
        } else if (board.hasAPlayerWon(boardState, humanSymbol)) {
            return -1000 / depth;
        } else {
            return 0;
        }
    }

    private int findBestMove(Map<Integer, Integer>scoredSpaces)  {
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


}

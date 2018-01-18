package com.EighthLight.app;

import java.util.ArrayList;
import java.util.HashMap;

public class Ai implements IPlayer{
    private String aiSymbol;
    private String humanSymbol;
    private String difficulty;
    private IBoard board;

    public Ai(String aiSymbol, String humanSymbol, String difficulty, IBoard board) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
        this.difficulty = difficulty;
        this.board = board;
    }

    //sets the score for the game
    public int setScore(String player, String computer) {
        return player.equals(computer) ? -1000 : 1000;
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

    public int findBestMove(ArrayList<HashMap<String, Integer>> moves, String currentPlayer)  {
        int bestMove = 0;
        int bestScore = setScore(currentPlayer, aiSymbol);

        for (int move = 0; move < moves.size(); move++) {
            int currentScore = moves.get(move).get("score");
            if (currentPlayer.equals(aiSymbol)) {
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestMove = move;
                }
            } else {
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    public HashMap miniMax(ArrayList boardState, int depth, String currentPlayer) {
        ArrayList newBoard = new ArrayList();
        newBoard.addAll(boardState);

        ArrayList<Integer> emptySpaces = findEmptySpaces(newBoard);
        depth++;

        // base cases [EndStates]
        HashMap<String, Integer> scoreKeeper = new HashMap();
        System.out.print(newBoard);
        System.out.print(this.board);
        System.out.print(currentPlayer);
        if (currentPlayer.equals(humanSymbol) && board.hasAPlayerWon(currentPlayer)) {
            scoreKeeper.put("score", -10 + depth);
            return scoreKeeper;
        } else if (currentPlayer.equals(aiSymbol) && board.hasAPlayerWon(currentPlayer)) {
            scoreKeeper.put("score", 10 - depth);
            return scoreKeeper;
        } else if (emptySpaces.size() == 0){
            scoreKeeper.put("score", 0);
            return scoreKeeper;
        }

        ArrayList<HashMap<String, Integer>> moves = new ArrayList<>();

        // recursively call minimax on the empty spaces
        for (int space = 0; space < emptySpaces.size(); space++) {
            HashMap<String, Integer> singleMove = new HashMap();
            singleMove.put("index", emptySpaces.get(space));
            newBoard.set(emptySpaces.get(space), currentPlayer);

            if (currentPlayer.equals(aiSymbol)) {
                HashMap<String, Integer> result = miniMax(newBoard, depth, humanSymbol);
                singleMove.put("score", result.get("score"));
            } else {
                HashMap<String, Integer> result = miniMax(newBoard, depth, aiSymbol);
                singleMove.put("score", result.get("score"));
            }
            // reset the board
            newBoard.set(emptySpaces.get(space), singleMove.get("index"));
            moves.add(singleMove);
        }

        return moves.get(findBestMove(moves, aiSymbol));
    }

    public void makeMove(IBoard board) {
        Object bestMove = miniMax(board.getSpaces(), 0, aiSymbol).get("index");
        board.updateSpace(bestMove.toString(), this.aiSymbol);
    }


//    public void makeMove(IBoard board) {
//        for (Object space : board.getSpaces()) {
//            if (space instanceof Integer) {
//                board.updateSpace(space.toString(), this.aiSymbol);
//                break;
//            }
//        }
//    }
}

package com.EighthLight.app;

import java.util.ArrayList;
import java.util.HashMap;

public class Ai implements IPlayer{
    private String aiSymbol;
    private String humanSymbol;
    private String difficulty;

    public Ai(String aiSymbol, String humanSymbol, String difficulty) {
        this.aiSymbol = aiSymbol;
        this.humanSymbol = humanSymbol;
        this.difficulty = difficulty;
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
        ArrayList<Integer> emptySpaces = findEmptySpaces(boardState);
        depth++;

        // base cases [EndStates]
        HashMap<String, Integer> scoreKeeper = new HashMap();
        if (currentPlayer.equals(humanSymbol) && board.hasAPlayerWon(currentPlayer)) {
            scoreKeeper.put("score", -10 + depth);
            return scoreKeeper;
        } else if (currentPlayer.equals(aiSymbol) && board.hasAPlayerWon(currentPlayer)) {
            scoreKeeper.put("score", 10 - depth);
            return scoreKeeper;
        } else {
            scoreKeeper.put("score", 0);
            return scoreKeeper;
        }

        ArrayList moves = new ArrayList();

        // recursively call minimax on the empty spaces
        for (int space = 0; space < emptySpaces.size(); space++) {
            HashMap<String, Integer> singleMove = new HashMap();
            singleMove.put("index", emptySpaces.get(space));
            boardState.set(emptySpaces.get(space), currentPlayer);

            if (currentPlayer.equals(aiSymbol)) {
                HashMap<String, Integer> result = miniMax(boardState, depth, humanSymbol);
                singleMove.put("score", result.get("score"));
            } else {
                HashMap<String, Integer> result = miniMax(boardState, depth, aiSymbol);
                singleMove.put("score", result.get("score"));
            }
            // reset the board
            boardState.set(emptySpaces.get(space), singleMove.get("index"));
            moves.add(singleMove);
        }



        // creates a copy of the passed in boardState
//        ArrayList newBoard = new ArrayList();
//        newBoard.addAll(board.getSpaces());
//        newBoard.set(0, "HELLO");
//        System.out.print(newBoard);

//
//        miniMax();
//
//        // create a copy of the board to work with inside of minimax
//
//
//        System.out.print(board.getSpaces());
//        ArrayList<Integer> availableSpaces = findEmptySpaces((ArrayList) boardCopy);
//
//        System.out.print(availableSpaces);
//
//
//        int depth;
//
//
//        for (int index = 0; index < availableSpaces.size(); index++) {
//            boardCopy.set(availableSpaces.get(index), currentPlayer);
//        }
//
//        for (Object space : availableSpaces) {
//            boardCopy.set(space, currentPlayer);
//
//        }
//
//        return 1;
    }


    public void makeMove(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), this.aiSymbol);
                break;
            }
        }
    }


}

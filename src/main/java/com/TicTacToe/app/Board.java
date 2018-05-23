package com.TicTacToe.app;

import com.TicTacToe.app.Interfaces.IBoard;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    private int size;
    private ArrayList spaces = new ArrayList(size*size);
    private List<ArrayList<Integer>> winningCombos = new ArrayList<>();

    public Board(int size) {
        this.size = size;
        createBoard();
        setWinningCombos();
    }

    private void createBoard() {
        for (int i = 0; i < size*size; i++) {
            spaces.add(i);
        }
    }

    public boolean spaceWithinBounds(String userInput) {
        int convertedInput;
        try {
            convertedInput = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        }
        ArrayList spaces = getSpaces();
        return spaces.contains(convertedInput);
    }

    public void updateSpace(String userInput, String playerSymbol) {
        ArrayList boardSpaces = getSpaces();
        int boardIndex = Integer.parseInt(userInput);
        boardSpaces.set(boardIndex, playerSymbol);
    }

    public boolean gameIsTie(ArrayList spaces) {
        for (Object space : spaces) {
            if (space instanceof Integer) {
                return false;
            }
        }
        return true;
    }

    public ArrayList getSpaces() {
        return spaces;
    }

    public ArrayList<Integer> findEmptySpaces(ArrayList spaces) {
        ArrayList<Integer> emptySpaces = new ArrayList();
        for (Object space : spaces) {
            if (space instanceof Integer) {
                emptySpaces.add((Integer) space);
            }
        }
        return emptySpaces;
    }

    public void setWinningCombos() {
        List<ArrayList<Integer>> allWinningConditions = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = makeWinningRows();
        List<ArrayList<Integer>> winningColumns = makeWinningColumns(winningRows);
        ArrayList<Integer> winningTopLeftDiag = makeTopLeftDiag(winningRows);
        ArrayList<Integer> winningBottomLeftDiag = makeBottomLeftDiag(winningRows);
        allWinningConditions.addAll(winningRows);
        allWinningConditions.addAll(winningColumns);
        allWinningConditions.add(winningTopLeftDiag);
        allWinningConditions.add(winningBottomLeftDiag);
        this.winningCombos = allWinningConditions;
    }

    public List<ArrayList<Integer>> getWinningCombos() {
        return winningCombos;
    }

    public List<ArrayList<Integer>> makeWinningRows() {
        List<ArrayList<Integer>> winningRows = new ArrayList<>();
        ArrayList<Integer> singleRow = new ArrayList<>();
        for (int space = 0; space < (this.size*this.size); space++) {
            singleRow.add(space);
            ArrayList singleClone = (ArrayList) singleRow.clone();
            if (singleRow.size() == this.size) {
                winningRows.add(singleClone);
                singleRow.clear();
            }
        }
        return winningRows;
    }

    public List<ArrayList<Integer>> makeWinningColumns(List<ArrayList<Integer>> winningRows) {
        List<ArrayList<Integer>> winningColumns = new ArrayList<>();
        for (int outer = 0; outer < this.size; outer++) {
            ArrayList<Integer> singleColumn = new ArrayList<>();
            for (int inner = 0; inner < this.size; inner++) {
                singleColumn.add(winningRows.get(inner).get(outer));
            }
            winningColumns.add(singleColumn);
        }
        return winningColumns;
    }

    public ArrayList<Integer> makeTopLeftDiag(List<ArrayList<Integer>> winningRows) {
        ArrayList<Integer> leftTopDiagonal = new ArrayList<>();
        for (int space = 0; space < this.size; space++) {
            leftTopDiagonal.add(winningRows.get(space).get(space));
        }
        return leftTopDiagonal;
    }

    public ArrayList<Integer> makeBottomLeftDiag(List<ArrayList<Integer>> winningRows) {
        ArrayList<Integer> bottomLeftDiagonal = new ArrayList<>();
        int inner = 0;
        int outer = this.size - 1;
        while (inner < this.size) {
            bottomLeftDiagonal.add(winningRows.get(outer).get(inner));
            inner += 1;
            outer -= 1;
        }
        return bottomLeftDiagonal;
    }

    public boolean hasAPlayerWon(ArrayList boardState, String playerSymbol) {
        boolean playerWon;
        for (ArrayList<Integer> winningCombo : winningCombos) {
            playerWon = occupiesWinningSpots(boardState, playerSymbol, winningCombo);
            if (playerWon) { return true; }
        }
        return false;
    }

    private boolean occupiesWinningSpots(ArrayList boardState, String playerSymbol, ArrayList<Integer> winningCombo) {
        for (int spot = 0; spot < size; spot++) {
            if (boardState.get(winningCombo.get(spot)) != playerSymbol) {
                return false;
            }
        }
        return true;
    }
}

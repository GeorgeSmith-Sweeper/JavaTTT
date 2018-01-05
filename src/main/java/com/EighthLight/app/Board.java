package com.EighthLight.app;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    private int size;
    private ArrayList spaces = new ArrayList(size*size);
    private List<ArrayList<Integer>> winningCombos = new ArrayList<>();

    public Board(int size) {
        this.size = size;
    }

    public ArrayList createBoard() {
        for (int i = 0; i < size*size; i++) {
            spaces.add(i);
        }
        return spaces;
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

    public void updateSpace(String userInput, IPlayer player) {
        ArrayList boardSpaces = getSpaces();
        int boardIndex = Integer.parseInt(userInput);
        boardSpaces.set(boardIndex, player.getSymbol());
    }

    public boolean gameIsTie(ArrayList spaces) {
        boolean isTied = true;
        for (Object space : spaces) {
            if (space instanceof Integer) {
                isTied = false;
            }
        }
        return isTied;
    }

    public ArrayList getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList passedInState) {
        this.spaces = passedInState;
    }

//    public void setWinningCombos() {
//        List<Integer> allWinConditions = new ArrayList();
//
//        winningCombos = allWinConditions;
//    }

//    public List<Integer> makeWinningRows() {
//        List<Integer> allRows = new ArrayList<>();
//        for (int start = 0; start < size*size; start++) {
//            allRows.add(start);
//        }
//        return allRows;
//    }
//
//    public List<Integer> makeWinningColumns() {
//        List<Integer> allRows = makeWinningRows();
//        List<Integer> singleColumn = new ArrayList<>();
//
//        for (int space = 0; space < size; space++) {
//            List<Integer> allColumns = new ArrayList<>();
//            for (int colSpace = 0; colSpace < size; colSpace++) {
//                allColumns.add(allRows.get(colSpace));
//            }
//            for () {
//
//            }
//        }
//        System.out.println(allColumns);
//        return allColumns;
//    }


    public void setWinningCombos() {
        List<ArrayList<Integer>> allWinningConditions = new ArrayList<>();
        List<ArrayList<Integer>> winningRows = makeWinningRows();
        
        for (ArrayList row: winningRows) {
            allWinningConditions.add(row);
        }

        // Calculate all winning Columns
        List<ArrayList> winningColumns = new ArrayList<>();
        for (int outer = 0; outer < this.size; outer++) {
            ArrayList<Integer> singleColumn = new ArrayList<>();
            for (int inner = 0; inner < this.size; inner++) {
                singleColumn.add((Integer) winningRows.get(inner).get(outer));
            }
            winningColumns.add(singleColumn);
        }
        for(ArrayList column: winningColumns) {
            allWinningConditions.add(column);
        }

        // Calculate all left Top winning diagonals
        ArrayList<Integer> leftTopDiagonal = new ArrayList<>();
        for (int space = 0; space < this.size; space++) {
            leftTopDiagonal.add((Integer) winningRows.get(space).get(space));
        }
        allWinningConditions.add(leftTopDiagonal);

        // Calculate all left Bottom winning diagonals
        ArrayList<Integer> leftBottomDiagonal = new ArrayList<>();
        int inner = 0;
        int outer = this.size - 1;
        while (inner < this.size) {
            leftBottomDiagonal.add((Integer) winningRows.get(outer).get(inner));
            inner += 1;
            outer -= 1;
        }
        allWinningConditions.add(leftBottomDiagonal);
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
}

// make a list, and populate it with the winning spots (Integers). In order to check
// the winning conditions, create a loop that calls sublist on the winning spots list,
// each sublist can be the size of the width of the board.
// if any of the sublists are completely filled with the user symbol then return true
// otherwise return false
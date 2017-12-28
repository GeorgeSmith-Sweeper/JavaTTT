package com.EighthLight.app;

import java.util.ArrayList;

public class Board {
    private int size;
    private ArrayList<Integer> spaces = new ArrayList<Integer>(size*size);

    public Board(int size) {
        this.size = size;
    }

    public ArrayList<Integer> createBoard() {
        for (int i = 0; i < size*size; i++) {
            spaces.add(i);
        }
        return spaces;
    }

    public ArrayList getSpaces() {
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
}

package com.EighthLight.app;

import java.util.ArrayList;

public class MockBoard implements IBoard{
    private int size;

    public MockBoard(int size) {
        this.size = size;
    }

    @Override
    public boolean spaceWithinBounds(String userInput) {
        return false;
    }

    @Override
    public void updateSpace(String userInput, IPlayer player) {

    }

    @Override
    public boolean gameIsTie(ArrayList spaces) {
        return false;
    }

    @Override
    public void setWinningCombos() {

    }

    @Override
    public ArrayList createBoard() {
        return null;
    }

    @Override
    public ArrayList getSpaces() {
        return null;
    }
}

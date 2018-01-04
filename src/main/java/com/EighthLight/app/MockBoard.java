package com.EighthLight.app;

import java.util.ArrayList;

public class MockBoard implements IBoard {
    private int size;
    private boolean gameTieCalled;
    private ArrayList spaces;

    public MockBoard(int size, ArrayList spaces) {
        this.size = size;
        this.spaces = spaces;
    }

    @Override
    public boolean spaceWithinBounds(String userInput) {
        return false;
    }

    public void updateSpace(String userInput, IPlayer player) {

    }

    public boolean gameIsTie(ArrayList spaces) {
        gameTieCalled = true;
        return true;
    }

    public void setWinningCombos() {

    }

    public ArrayList createBoard() {
        return null;
    }

    public ArrayList getSpaces() {
        return this.spaces;
    }

    public boolean gameIsTieWasCalled() {
        return gameTieCalled;
    }
}

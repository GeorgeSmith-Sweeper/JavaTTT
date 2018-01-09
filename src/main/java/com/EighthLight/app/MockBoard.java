package com.EighthLight.app;

import java.util.ArrayList;

public class MockBoard implements IBoard {
    private int size;
    private ArrayList spaces;
    private boolean spaceInBounds;
    private boolean gameTieCalled;
    private boolean spaceInBoundsCalled = false;
    private boolean updateSpaceCalled = false;
    private boolean playerWonWasCalled = false;

    public MockBoard(int size, ArrayList spaces, boolean spaceInBounds) {
        this.size = size;
        this.spaces = spaces;
        this.spaceInBounds = spaceInBounds;
    }

    public boolean spaceWithinBounds(String userInput) {
        spaceInBoundsCalled = true;
        return this.spaceInBounds;
    }

    public void updateSpace(String userInput, IPlayer player) {
        updateSpaceCalled = true;
    }

    public boolean gameIsTie(ArrayList spaces) {
        gameTieCalled = true;
        return true;
    }

    public boolean hasAPlayerWon(IPlayer currentPlayer) {
        playerWonWasCalled = true;
        return false;
    }

    public void setWinningCombos() { }

    public ArrayList createBoard() {
        return null;
    }

    public ArrayList getSpaces() {
        return this.spaces;
    }

    public boolean gameIsTieWasCalled() {
        return gameTieCalled;
    }

    public boolean spaceWithinBoundsWasCalled() {
        return spaceInBoundsCalled;
    }

    public boolean updateSpaceWasCalled() {
        return updateSpaceCalled;
    }

    public boolean hasAPlayerWonWasCalled() {
        return playerWonWasCalled;
    }
}

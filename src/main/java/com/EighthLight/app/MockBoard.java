package com.EighthLight.app;

import java.util.ArrayList;

public class MockBoard implements IBoard {
    private boolean spaceInBounds;
    private int numTimesGetSpacesCalled;
    private boolean didAPlayerWin;
    private boolean gameTied;
    private ArrayList spaces;
    private ArrayList hasPlayerWonArgs = new ArrayList();
    private ArrayList gameTieArgs = new ArrayList();
    private ArrayList spaceWithinBoundsArgs = new ArrayList();
    private ArrayList updateSpaceArgs= new ArrayList();

    public MockBoard(ArrayList spaces, boolean didAPlayerWin, boolean gameTied, boolean spaceInBounds) {
        this.spaces = spaces;
        this.spaceInBounds = spaceInBounds;
        this.didAPlayerWin = didAPlayerWin;
        this.gameTied = gameTied;
    }

    public boolean spaceWithinBounds(String userInput) {
        spaceWithinBoundsArgs.add(userInput);
        return userInput.equals(Constants.CORRECT_INPUT);
    }

    public void updateSpace(String userInput, IPlayer player) {
        updateSpaceArgs.add(userInput);
        updateSpaceArgs.add(player);
    }

    public boolean gameIsTie(ArrayList spaces) {
        gameTieArgs.add(spaces);
        return this.gameTied;
    }

    public boolean hasAPlayerWon(IPlayer currentPlayer) {
        hasPlayerWonArgs.add(currentPlayer);
        return this.didAPlayerWin;
    }

    public ArrayList getSpaces() {
        numTimesGetSpacesCalled++;
        return this.spaces;
    }

    public int getNumTimesGetSpacesCalled() {
        return numTimesGetSpacesCalled;
    }

    public ArrayList getHasPlayerWonArgs() {
        return hasPlayerWonArgs;
    }

    public ArrayList getGameTieArgs() {
        return gameTieArgs;
    }

    public ArrayList getSpaceWithinBoundsArgs() {
        return spaceWithinBoundsArgs;
    }

    public ArrayList getUpdateSpaceArgs() {
        return updateSpaceArgs;
    }
}

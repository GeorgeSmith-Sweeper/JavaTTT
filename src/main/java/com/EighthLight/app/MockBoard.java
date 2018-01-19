package com.EighthLight.app;

import java.util.ArrayList;

public class MockBoard implements IBoard {
    private int numTimesGetSpacesCalled;
    private ArrayList didAPlayerWin;
    private ArrayList gameTied;
    private ArrayList spaces;
    private ArrayList hasPlayerWonArgs = new ArrayList();
    private ArrayList gameTieArgs = new ArrayList();
    private ArrayList spaceWithinBoundsArgs = new ArrayList();
    private ArrayList updateSpaceArgs = new ArrayList();
    private int numTimesGameIsTiedCalled = 0;
    private int numTimesUpdateSpaceIsCalled = 0;
    private int numTimesAPlayerWonCalled = 0;

    public MockBoard(ArrayList spaces, ArrayList didAPlayerWin, ArrayList gameTied) {
        this.spaces = spaces;
        this.didAPlayerWin = didAPlayerWin;
        this.gameTied = gameTied;
    }

    public boolean spaceWithinBounds(String userInput) {
        spaceWithinBoundsArgs.add(userInput);
        return userInput.equals(Constants.CORRECT_INPUT);
    }

    public void updateSpace(String userInput, String playerSymbol) {
        numTimesUpdateSpaceIsCalled++;
        updateSpaceArgs.add(userInput);
        updateSpaceArgs.add(playerSymbol);
    }

    public boolean gameIsTie(ArrayList spaces) {
        boolean tieValue = (boolean) this.gameTied.get(numTimesGameIsTiedCalled);
        numTimesGameIsTiedCalled++;
        gameTieArgs.add(spaces);
        return tieValue;
    }

    public boolean hasAPlayerWon(ArrayList newBoard, String currentPlayer) {
        boolean winValue = (boolean) this.didAPlayerWin.get(numTimesAPlayerWonCalled);
        numTimesAPlayerWonCalled++;
        hasPlayerWonArgs.add(currentPlayer);
        return winValue;
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

    public int getNumTimesUpdateSpaceIsCalled() {
        return numTimesUpdateSpaceIsCalled;
    }
}

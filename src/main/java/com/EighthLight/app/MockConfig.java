package com.EighthLight.app;

import java.util.ArrayList;

public class MockConfig implements IConfig {
    private IUserInterface ui;
    private IBoard board;
    private int numTimesGetBoardCalled = 0;
    private int numTimesGetPlayersCalled = 0;
    ArrayList<IPlayer> players;

    public MockConfig(IUserInterface ui, ArrayList<IPlayer> players, IBoard board) {
        this.ui = ui;
        this.players = players;
        this.board = board;
    }

    public ArrayList<IPlayer> getPlayers() {
        numTimesGetPlayersCalled++;
        return players;
    }

    public IBoard getBoard() {
        numTimesGetBoardCalled++;
        return board;
    }

    public int getNumTimesGetBoardCalled() {
        return numTimesGetBoardCalled;
    }

    public int getNumTimesGetPlayersCalled() {
        return numTimesGetPlayersCalled;
    }
}

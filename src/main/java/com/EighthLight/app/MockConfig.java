package com.EighthLight.app;

import java.util.ArrayList;

public class MockConfig implements IConfig {
    private IUserInterface ui;
    private IBoard board;
    ArrayList<IPlayer> players;

    public MockConfig(IUserInterface ui, ArrayList<IPlayer> players, IBoard board) {
        this.ui = ui;
        this.players = players;
        this.board = board;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public IBoard getBoard() {
        return board;
    }
}

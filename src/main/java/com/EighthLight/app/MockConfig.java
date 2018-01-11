package com.EighthLight.app;

import java.util.ArrayList;

public class MockConfig implements IConfig {
    private IUserInterface ui;
    ArrayList<IPlayer> players;

    public MockConfig(IUserInterface ui, ArrayList<IPlayer> players) {
        this.ui = ui;
        this.players = players;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }
}

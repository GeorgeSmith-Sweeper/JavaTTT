package com.EighthLight.app;

import java.util.ArrayList;

public class Config {
    private IUserInterface ui;
    private String gameMode;
    private ArrayList<IPlayer> players = new ArrayList<>();

    public Config(IUserInterface ui) {
        this.ui = ui;
    }

    public String getGameMode () {
        return gameMode;
    }

    public void setGameMode() {
        ui.display("type '1' for Human vs Human, type 2 for Human vs Computer");
        String userInput = ui.getInput();

        while ((userInput != "1") && (userInput != "2")) {
            ui.display("Invalid input, try again.");
            userInput = ui.getInput();
        }
        if (userInput == "1") {
            gameMode = "1";
        } else if (userInput == "2") {
            gameMode = "2";
        }
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public void makePlayers() {
        if (gameMode == "1") {
            Player playerOne = new Player("X");
            Player playerTwo = new Player("O");
            players.add(playerOne);
            players.add(playerTwo);
        }
        if (gameMode == "2") {
            Player playerOne = new Player("X");
            Ai playerTwo = new Ai("O");
            players.add(playerOne);
            players.add(playerTwo);
        }
    }
}

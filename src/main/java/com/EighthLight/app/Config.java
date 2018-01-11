package com.EighthLight.app;

import java.util.ArrayList;

public class Config implements IConfig{

    private IUserInterface ui;
    private ArrayList<IPlayer> players = new ArrayList();

    public Config(IUserInterface ui) {
        this.ui = ui;
    }

    public ArrayList<IPlayer> getPlayers() {

        ui.display(Constants.GAME_MODE_PROMPT);
        String userInput = ui.getInput();

        if (userInput.equals("1")) {
            Player playerOne = new Player("X", ui);
            Player playerTwo = new Player("O", ui);
            players.add(playerOne);
            players.add(playerTwo);
        }

        if (userInput.equals("2")) {
            Player playerOne = new Player("X", ui);
            Ai playerTwo = new Ai("O");
            players.add(playerOne);
            players.add(playerTwo);
        }
        return players;
    }
}

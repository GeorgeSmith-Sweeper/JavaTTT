package com.EighthLight.app;

public class Config {
    private IUserInterface ui;
    private String gameMode;

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
}

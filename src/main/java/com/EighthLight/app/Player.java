package com.EighthLight.app;

public class Player implements IPlayer {
    private String symbol;
    private IUserInterface ui;

    public Player(String symbol, IUserInterface ui) {
        this.symbol = symbol;
        this.ui = ui;
    }

    public String getSymbol() {
        return symbol;
    }

    public void makeMove(IBoard board) {
        String userInput = ui.getInput();
        while (!board.spaceWithinBounds(userInput)) {
            ui.display(Constants.INVALID_SPOT_MSG);
            userInput = ui.getInput();
        }
        board.updateSpace(userInput, this.symbol);
    }
}

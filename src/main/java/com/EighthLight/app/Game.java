package com.EighthLight.app;


import java.util.ArrayList;

public class Game implements IGame {
    private IConfig config;
    private IUserInterface ui;

    public Game(IUserInterface ui, IConfig config) {
        this.ui = ui;
        this.config = config;
    }

    public void start() {
        IBoard board = config.getBoard();
        ArrayList<IPlayer> players = config.getPlayers();
        IPlayer playerOne = players.get(0);
        IPlayer playerTwo = players.get(1);
        IPlayer currentPlayer = playerOne;
        boolean gameIsTie = false;
        boolean aPlayerWon = false;

        ui.display(ui.presentBoard(board.getSpaces()));

        while (!gameIsTie && !aPlayerWon) {
            ui.display(Constants.PICK_A_SPOT_MSG);
            currentPlayer.makeMove(board);
            aPlayerWon = board.hasAPlayerWon(currentPlayer);
            gameIsTie = board.gameIsTie(board.getSpaces());
            if (aPlayerWon) {
                ui.display(currentPlayer.getSymbol() + " WINS!");
            } else if (gameIsTie) {
                ui.display(Constants.TIE_GAME_MSG);
            }
            ui.display(ui.presentBoard(board.getSpaces()));
            currentPlayer = playerOne.equals(currentPlayer) ? playerTwo : playerOne;
        }
    }
}

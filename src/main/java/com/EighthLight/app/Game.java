package com.EighthLight.app;


import java.util.ArrayList;

public class Game implements IGame {
    private IConfig config;
    private IUserInterface ui;
    private IBoard board;
    private ArrayList<IPlayer> players;
    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IPlayer currentPlayer;

    public Game(IUserInterface ui, IConfig config) {
        this.ui = ui;
        this.config = config;
        this.board = config.getBoard();
        this.players = config.getPlayers();
        this.playerOne = players.get(0);
        this.playerTwo = players.get(1);
        this.currentPlayer = playerOne;
    }

    public void start() {
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

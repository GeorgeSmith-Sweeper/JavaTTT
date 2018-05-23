package com.TicTacToe.app;


import com.EighthLight.app.Interfaces.*;
import com.TicTacToe.app.Interfaces.*;

import java.util.ArrayList;

public class Game implements IGame {
    private IConfig config;
    private IUserInterface ui;
    private IBoard board;
    private ArrayList<IPlayer> players;
    private IPlayer player1;
    private IPlayer player2;
    private IPlayer currentPlayer;

    public Game(IUserInterface ui, IConfig config) {
        this.ui = ui;
        this.config = config;
        this.board = config.getBoard();
        this.players = config.getPlayers();
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.currentPlayer = player1;
    }

    public void start() {
        boolean gameIsTie = false;
        boolean aPlayerWon = false;
        ui.display(ui.presentBoard(board.getSpaces()));

        while (!gameIsTie && !aPlayerWon) {
            ui.display(Constants.PICK_A_SPOT_MSG);
            currentPlayer.makeMove(board);
            aPlayerWon = board.hasAPlayerWon(board.getSpaces(), currentPlayer.getSymbol());
            gameIsTie = board.gameIsTie(board.getSpaces());
            if (aPlayerWon) {
                ui.display(currentPlayer.getSymbol() + " WINS!");
            } else if (gameIsTie) {
                ui.display(Constants.TIE_GAME_MSG);
            }
            ui.display(ui.presentBoard(board.getSpaces()));
            currentPlayer = player1.equals(currentPlayer) ? player2 : player1;
        }
    }
}

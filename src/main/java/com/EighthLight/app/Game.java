package com.EighthLight.app;

public class Game implements IGame {
    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IUserInterface ui;
    private IBoard board;

    public Game(IUserInterface ui, IBoard board, IPlayer playerOne, IPlayer playerTwo) {
        this.ui = ui;
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void start() {
        boolean gameIsTie = false;
        boolean aPlayerWon = false;

        ui.display(ui.presentBoard(board.getSpaces()));
        IPlayer currentPlayer = playerOne;

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

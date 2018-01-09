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
            inputValidation(currentPlayer);
            gameIsTie = board.gameIsTie(board.getSpaces());
            aPlayerWon = board.hasAPlayerWon(currentPlayer);
            if (gameIsTie) {
                ui.display(Constants.TIE_GAME_MSG);
            }
            if (aPlayerWon) {
                ui.display(currentPlayer.getSymbol() + " WINS!");
            }
            ui.display(ui.presentBoard(board.getSpaces()));
            currentPlayer = playerOne.equals(currentPlayer) ? playerTwo : playerOne;
        }
    }

    private void inputValidation(IPlayer currentPlayer) {
        String userInput = ui.getInput();

        while (!board.spaceWithinBounds(userInput)) {
            ui.display(Constants.INVALID_SPOT_MSG);
            userInput = ui.getInput();
        }
        board.updateSpace(userInput, currentPlayer);
    }
}

package com.EighthLight.app;

public class Game {
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

    public void start(String gameMode) {
        if (gameMode.equals("1")) {
            humanVsHuman();
        }
        if (gameMode.equals("2")) {
            humanVsComputer();
        }
    }

    private void inputValidation(IPlayer currentPlayer) {
        String userInput = ui.getInput();

        while (!board.spaceWithinBounds(userInput)) {
            ui.display("Invalid spot, pick again!");
            userInput = ui.getInput();
        }
        board.updateSpace(userInput, currentPlayer);
    }

    private void humanVsHuman() {
        boolean gameIsTie = false;
        boolean aPlayerWon = false;
        ui.display(ui.presentBoard(board.getSpaces()));
        IPlayer currentPlayer = playerOne;

        while (!gameIsTie && !aPlayerWon) {
            ui.display("Please pick a spot!");
            inputValidation(currentPlayer);
            gameIsTie = board.gameIsTie(board.getSpaces());
            aPlayerWon = board.hasAPlayerWon(currentPlayer);
            if (gameIsTie) {
                ui.display("No one wins! Game over!");
            }
            if (aPlayerWon) {
                ui.display(currentPlayer.getSymbol() + " WINS!");
            }
            currentPlayer = playerOne.equals(currentPlayer) ? playerTwo : playerOne;
            ui.display(ui.presentBoard(board.getSpaces()));
        }
    }

    private void humanVsComputer() {
        boolean gameIsTie = false;
        boolean aPlayerWon = false;
        ui.display(ui.presentBoard(board.getSpaces()));
        IPlayer currentPlayer = playerOne;

        while (!gameIsTie && !aPlayerWon) {
            if (currentPlayer.equals(playerOne)) {
                ui.display("Please pick a spot!");
                inputValidation(playerOne);
            } else {
                String selectedSpot = currentPlayer.pickSpotRandomly(board);
                board.updateSpace(selectedSpot, currentPlayer);
                ui.display(ui.presentBoard(board.getSpaces()));
            }
            gameIsTie = board.gameIsTie(board.getSpaces());
            aPlayerWon = board.hasAPlayerWon(currentPlayer);
            if (aPlayerWon) {
                ui.display(currentPlayer.getSymbol() + " WINS!");
            }
            currentPlayer = playerOne.equals(currentPlayer) ? playerTwo : playerOne;
        }
        if (gameIsTie) {
            ui.display("No one wins! Game over!");
        }
        ui.display(ui.presentBoard(board.getSpaces()));
    }
}



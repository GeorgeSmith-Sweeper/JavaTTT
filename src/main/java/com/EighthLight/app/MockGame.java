package com.EighthLight.app;

public class MockGame implements IGame {

    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IUserInterface ui;
    private Board board;

    public MockGame(IUserInterface ui, Board board, IPlayer playerOne, IPlayer playerTwo) {
        this.ui = ui;
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void start() {
        String hello = "Hello";
        boolean gameIsTied = false;
        while (!gameIsTied) {
            ui.display(hello);
            ui.getInput();
            gameIsTied = true;
        }
    }
}

package com.EighthLight.app;

public class App {
    public static void main (String[] args) {
        Player playerOne = new Player("X");
        Player playerTwo = new Player("O");
        Ui ui = new Ui(System.in);
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        board.setWinningCombos();
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();
    }
}

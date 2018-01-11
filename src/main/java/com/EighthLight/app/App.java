package com.EighthLight.app;

public class App {
    public static void main (String[] args) {
        Ui ui = new Ui(System.in);
        Player playerOne = new Player("X",ui);
        Player playerTwo = new Player("O",ui);
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        board.setWinningCombos();
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();
    }
}

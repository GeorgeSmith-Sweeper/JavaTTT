package com.EighthLight.app;

public class App {
    public static void main (String[] args) {
        Ui ui = new Ui(System.in);
        Config config = new Config(ui);
        config.setGameMode();
        config.makePlayers();
        IPlayer playerOne = config.getPlayers().get(0);
        IPlayer playerTwo = config.getPlayers().get(1);
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        board.setWinningCombos();
        Game game = new Game(ui, board, playerOne, playerTwo);
        game.start();
    }
}
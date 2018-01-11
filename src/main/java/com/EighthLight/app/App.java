package com.EighthLight.app;

public class App {
    public static void main (String[] args) {
//
//        SetUp setUp = new SetUp(ui);
//        Game game = new Game(setUp);
//        game.start();
//
        Ui ui = new Ui(System.in);
        Config config = new Config(ui);
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        board.setWinningCombos();
        Game game = new Game(ui, board, config);
        game.start();
    }
}

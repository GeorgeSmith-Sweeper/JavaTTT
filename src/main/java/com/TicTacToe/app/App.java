package com.TicTacToe.app;

public class App {
    public static void main (String[] args) {
        Ui ui = new Ui(System.in);
        Config config = new Config(ui);
        Game game = new Game(ui, config);
        game.start();
    }
}

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


// setup should run and prompt the user for a size, symbols for the players, gameMode
// setup(ui) takes ui
// setup makes Player Instances, boardInstance with the size, stores game Mode
// setup returns

// don't forget the config for user symbol and board size
// idea
// commandline args (/m or flags)
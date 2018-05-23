package com.TicTacToe.app.Mocks;

import com.TicTacToe.app.Interfaces.IBoard;
import com.TicTacToe.app.Interfaces.IConfig;
import com.TicTacToe.app.Interfaces.IPlayer;
import com.TicTacToe.app.Ui.IUserInterface;

import java.util.ArrayList;

public class MockConfig implements IConfig {
    private IUserInterface ui;
    private IBoard board;
    private int numTimesGetBoardCalled = 0;
    private int numTimesGetPlayersCalled = 0;
    private ArrayList<IPlayer> players;
    private IPlayer currentPlayer;


    public MockConfig(IUserInterface ui, ArrayList<IPlayer> players, IBoard board) {
        this.ui = ui;
        this.players = players;
        this.board = board;
        this.currentPlayer = players.get(0);
    }

    public ArrayList<IPlayer> getPlayers() {
        numTimesGetPlayersCalled++;
        return players;
    }

    public IBoard getBoard() {
        numTimesGetBoardCalled++;
        return board;
    }

    public int getNumTimesGetBoardCalled() {
        return numTimesGetBoardCalled;
    }

    public int getNumTimesGetPlayersCalled() {
        return numTimesGetPlayersCalled;
    }

    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}

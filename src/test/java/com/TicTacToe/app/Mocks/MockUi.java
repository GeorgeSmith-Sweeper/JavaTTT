package com.TicTacToe.app.Mocks;

import com.TicTacToe.app.Constants;
import com.TicTacToe.app.Ui.IUserInterface;

import java.util.ArrayList;

public class MockUi implements IUserInterface {

    private ArrayList presentBoardArgs = new ArrayList();
    private ArrayList<String> displayArgs = new ArrayList<>();
    private ArrayList<String> userInputs;
    private int numTimesGetInputCalled = 0;

    public MockUi(ArrayList userInputs) {
        this.userInputs = userInputs;
    }

    public void display(String message) {
        displayArgs.add(message);
    }

    public String getInput() {
        String userInput = this.userInputs.get(numTimesGetInputCalled);
        numTimesGetInputCalled++;
        return userInput;
    }

    @Override
    public String displayWinningMsg(String winningPlayerSymbol) {
        return winningPlayerSymbol + " WINS!";
    }

    public String presentBoard(ArrayList spaces) {
        Object spacesCopy = spaces.clone();
        presentBoardArgs.add(spacesCopy);
        return Constants.DISPLAYED_STRING;
    }

    public ArrayList getPresentBoardArgs() {
        return presentBoardArgs;
    }

    public ArrayList<String> getDisplayArgs() {
        return displayArgs;
    }

    public int getNumTimesGetInputCalled() {
        return numTimesGetInputCalled;
    }
}

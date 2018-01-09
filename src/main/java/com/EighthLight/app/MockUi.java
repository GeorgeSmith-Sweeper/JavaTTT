package com.EighthLight.app;

import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean displayCalled = false;
    private boolean inputCalled = false;
    private boolean presentCalled = false;
    private ArrayList presentBoardArgs = new ArrayList();
    private ArrayList<String> displayArgs = new ArrayList<>();
    private String userInput;

    public MockUi(String userInput) {
        this.userInput = userInput;
    }
    public boolean displayWasCalled() {
        return displayCalled;
    }

    public void display(String message) {
        displayArgs.add(message);
        displayCalled = true;
    }

    public String getInput() {
        inputCalled = true;
        return this.userInput;
    }

    public boolean inputWasCalled() {
        return inputCalled;
    }

    public String presentBoard(ArrayList spaces) {
        presentBoardArgs.add(spaces);
        presentCalled = true;
        return null;
    }

    public boolean presentBoardCalled() {
        return presentCalled;
    }


    public ArrayList getPresentBoardArgs() {
        return presentBoardArgs;
    }

    public ArrayList<String> getDisplayArgs() {
        return displayArgs;
    }
}

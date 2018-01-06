package com.EighthLight.app;

import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean displayCalled = false;
    private boolean inputCalled = false;
    private boolean presentCalled = false;
    private int numberOfTimesGetInputCalled = 0;
    private int numberOfTimesDisplayCalled = 0;
    private int numberOfTimesPresentCalled = 0;

    private ArrayList<String> providedInput;

    public MockUi(ArrayList<String> providedInput) {
        this.providedInput = providedInput;
    }
    public boolean displayWasCalled() {
        return displayCalled;
    }

    public void display(String message) {
        numberOfTimesDisplayCalled++;
        displayCalled = true;
    }

    public String getInput() {
        numberOfTimesGetInputCalled++;
        inputCalled = true;
        return this.providedInput.remove(0);
    }

    public boolean inputWasCalled() {
        return inputCalled;
    }

    public String presentBoard(ArrayList spaces) {
        numberOfTimesPresentCalled++;
        presentCalled = true;
        return null;
    }

    public boolean presentBoardCalled() {
        return presentCalled;
    }

    public int getNumberOfTimesGetInputCalled() {
        return numberOfTimesGetInputCalled;
    }

    public int getNumberOfTimesDisplayCalled() {
        return numberOfTimesDisplayCalled;
    }

    public int getNumberOfTimesPresentCalled() {
        return  numberOfTimesPresentCalled;
    }
}

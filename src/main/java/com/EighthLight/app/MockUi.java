package com.EighthLight.app;

import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean displayCalled = false;
    private boolean inputCalled = false;
    private boolean presentCalled = false;
    private String providedInput;


    public MockUi(String providedInput) {
        this.providedInput = providedInput;
    }
    public boolean displayWasCalled() {
        return displayCalled;
    }

    public void display(String message) {
        displayCalled = true;
    }

    public String getInput() {
        inputCalled = true;
        return this.providedInput;
    }

    public boolean inputWasCalled() {
        return inputCalled;
    }

    public String presentBoard(ArrayList spaces) {
        presentCalled = true;
        return null;
    }

    public boolean presentBoardCalled() {
        return presentCalled;
    }
}

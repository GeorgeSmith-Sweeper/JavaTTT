package com.EighthLight.app;

import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean displayCalled = false;
    private boolean inputCalled = false;

    public boolean displayWasCalled() {
        return displayCalled;
    }

    public void display(String message) {
        displayCalled = true;
    }

    public String getInput() {
        inputCalled = true;
        return "0";
    }

    public boolean inputWasCalled() {
        return inputCalled;
    }

    public String presentBoard(ArrayList spaces) {
        return null;
    }
}

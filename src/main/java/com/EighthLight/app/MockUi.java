package com.EighthLight.app;

import java.io.InputStream;
import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private final InputStream in;
    private boolean displayCalled = false;
    private boolean inputCalled = false;

    public MockUi(InputStream in) {
        this.in = in;
    }

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

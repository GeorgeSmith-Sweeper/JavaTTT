package com.EighthLight.app;

import java.util.ArrayList;

public interface IUserInterface {
    void display(String message);
    String presentBoard(ArrayList spaces);
    String getInput();
}
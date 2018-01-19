package com.EighthLight.app;

import java.util.ArrayList;

public interface IBoard {
    boolean spaceWithinBounds(String userInput);
    void updateSpace(String userInput, String playerSymbol);
    boolean gameIsTie(ArrayList spaces);
    boolean hasAPlayerWon(ArrayList newBoard, String currentPlayer);
    ArrayList getSpaces();
}

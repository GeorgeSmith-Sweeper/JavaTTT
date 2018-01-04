package com.EighthLight.app;

import java.util.ArrayList;

public interface IBoard {
    boolean spaceWithinBounds(String userInput);
    void updateSpace(String userInput, IPlayer player);
    boolean gameIsTie(ArrayList spaces);
    void setWinningCombos();
    ArrayList createBoard();
    ArrayList getSpaces();
}
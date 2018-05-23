package com.TicTacToe.app.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui implements IUserInterface {
    private InputStream in;

    public Ui(InputStream in) {
        this.in = in;
    }

    public void display(String message) {
        System.out.println(message);
    }

    public String getInput() {
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufReader = new BufferedReader(reader);
        try {
            return bufReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String makeDivider(String rowString) {
        StringBuilder divider = new StringBuilder();
        for (int space = 1; space < rowString.length(); space++) {
            divider.append("-");
        }
        return divider.toString();
    }

    public String presentBoard(ArrayList spaces) {
        int numRows = (int) Math.sqrt(spaces.size());
        ArrayList row = new ArrayList();
        ArrayList fullBoard = new ArrayList();

        for (int space = 0; space < spaces.size(); space++) {
            row.add(formatSpace(spaces, space));
            row.add("|");
            fullBoard = addRow(row, fullBoard, numRows);
        }
        fullBoard.remove(fullBoard.size() - 1);
        return String.join("", fullBoard);
    }

    private ArrayList addRow(ArrayList rowHolder, ArrayList fullBoard, int numRows) {

        if (rowHolder.size() == (numRows * 2)) {
            rowHolder.remove(rowHolder.size() - 1);
            String rowString = String.format("%s\n", String.join("", rowHolder));
            String divider = makeDivider(rowString);
            fullBoard.add(rowString);
            fullBoard.add(divider + "\n");
            rowHolder.clear();
        }
        return fullBoard;
    }

    public String formatSpace(ArrayList spaces, int space) {
        String convertedSpace = spaces.get(space).toString();
        return (convertedSpace.length() > 1) ? (" " + convertedSpace) : (" " + convertedSpace + " ");
    }
}

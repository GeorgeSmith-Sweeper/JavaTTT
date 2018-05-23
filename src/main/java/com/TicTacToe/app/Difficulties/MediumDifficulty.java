package com.TicTacToe.app.Difficulties;

import com.TicTacToe.app.Interfaces.IBoard;
import com.TicTacToe.app.Interfaces.IStrategy;

public class MediumDifficulty implements IStrategy {
    private String aiSymbol;

    public MediumDifficulty(String aiSymbol) {
        this.aiSymbol = aiSymbol;
    }

    public void markBoard(IBoard board) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), aiSymbol);
                break;
            }
        }
    }
}

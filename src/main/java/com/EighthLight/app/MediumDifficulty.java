package com.EighthLight.app;

import com.EighthLight.app.Interfaces.IBoard;
import com.EighthLight.app.Interfaces.IStrategy;

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

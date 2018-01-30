package com.EighthLight.app;

public class easyDifficulty implements IStrategy {

    public void markBoard(IBoard board, String aiSymbol, String humanSymbol) {
        for (Object space : board.getSpaces()) {
            if (space instanceof Integer) {
                board.updateSpace(space.toString(), aiSymbol);
                break;
            }
        }
    }
}

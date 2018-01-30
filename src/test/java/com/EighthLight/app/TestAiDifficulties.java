package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAiDifficulties {

    public void setBoardState(IBoard board, ArrayList spaces) {
        ArrayList currentSpaces = board.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    @Test
    void markBoardUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        ArrayList expectedBoard = new ArrayList(Arrays.asList("O", "X"));
        IBoard board = new Board(3);
        EasyDifficulty easy = new EasyDifficulty(aiSymbol);
        setBoardState(board, boardWithOpenSpace);
        easy.markBoard(board);

        assertEquals(expectedBoard, board.getSpaces());

    }
}

package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAi {

    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        Ai computer = new Ai("X");

        assertEquals("X", computer.getSymbol());
    }

    @Test
    void makeMoveUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
        String playerSymbol = "X";
        Ai computer = new Ai(playerSymbol);
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        MockBoard board = new MockBoard(boardWithOpenSpace, new ArrayList(), new ArrayList());

        computer.makeMove(board);

        assertEquals(1, board.getNumTimesGetSpacesCalled());
        assertEquals(1, board.getNumTimesUpdateSpaceIsCalled());
        assertEquals("1", board.getUpdateSpaceArgs().get(0));
        assertEquals(playerSymbol, board.getUpdateSpaceArgs().get(1));
    }

}

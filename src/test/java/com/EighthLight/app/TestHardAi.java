package com.EighthLight.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHardAi {
    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        IPlayer hardAi = new HardAi("X");

        assertEquals("X", hardAi.getSymbol());
    }

}

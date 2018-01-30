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
        ArrayList boardWithOpenSpace = new ArrayList(Arrays.asList("O", 1));
        ArrayList expectedBoard = new ArrayList(Arrays.asList("O", "X"));
        IBoard board = new Board(3);
        EasyDifficulty easy = new EasyDifficulty(aiSymbol);
        setBoardState(board, boardWithOpenSpace);
        easy.markBoard(board);

        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void aiMakesTheRightMoveWithTwoSpotsLeft() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", 1, 2,
                "O", "X", "O",
                "X", "O", "O"));
        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "X", 1, "X",
                "O", "X", "O",
                "X", "O", "O"));
        Board board = new Board(3);

        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiBlocksOpponentWhoIsAboutToWin() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        ArrayList boardState = new ArrayList(Arrays.asList(
                "O", 1, 2,
                "O", "X", 5,
                6, 7, 8));

        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "O", 1, 2,
                "O", "X", 5,
                "X", 7, 8));
        Board board = new Board(3);
        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiBlocksDiagonalHumanWin() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", "X", "O",
                3, "O", 5,
                6, 7, 8));
        ArrayList expectedBoardState = new ArrayList(Arrays.asList(
                "X", "X", "O",
                3, "O", 5,
                "X", 7, 8));
        Board board = new Board(3);
        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoardState, board.getSpaces());
    }

    @Test
    void aiValuesAWinOverABlock() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        ArrayList boardState = new ArrayList(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                6, 7, 8));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                "X", 7, 8));

        Board board = new Board(3);
        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        Board board = new Board(4);

        ArrayList boardState = new ArrayList(Arrays.asList(
                "O", "X", "X", "O",
                "X", "O", "X", "O",
                "O", "X", "O", "X",
                "X", "O", 14, 15));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "O", "X", "X", "O",
                "X", "O", "X", "O",
                "O", "X", "O", "X",
                "X", "O", 14, "X"));
        setBoardState(board, boardState);
        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board9SpotsLeft() {
        String aiSymbol = "X";
        String humanSymbol = "O";
        Board board = new Board(4);

        ArrayList boardState = new ArrayList(Arrays.asList(
                "O", "X", 2, 3,
                4, "O", "X", 7,
                8, 9, "O", "X",
                12, 13, 14, 15));
        ArrayList expectedBoard = new ArrayList(Arrays.asList(
                "O", "X", 2, 3,
                4, "O", "X", 7,
                8, 9, "O", "X",
                12, 13, 14, "X"));

        setBoardState(board, boardState);
        HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);
        setBoardState(board, boardState);
        hard.markBoard(board);

        assertEquals(expectedBoard, board.getSpaces());
    }
}

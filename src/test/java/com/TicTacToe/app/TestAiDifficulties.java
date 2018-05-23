package com.TicTacToe.app;

import com.TicTacToe.app.Interfaces.IBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAiDifficulties {
    private String aiSymbol = "X";
    private String humanSymbol = "O";
    private Board board3x3 = new Board(3);
    private Board board4x4 = new Board(4);
    private EasyDifficulty easy = new EasyDifficulty(aiSymbol);
    private HardDifficulty hard = new HardDifficulty(aiSymbol, humanSymbol);


    public void setBoardState(IBoard board, ArrayList spaces) {
        ArrayList currentSpaces = board.getSpaces();
        currentSpaces.clear();
        currentSpaces.addAll(spaces);
    }

    @Test
    void markBoardUpdatesTheBoardWithARandomMoveIfGameHasNotEnded() {
        ArrayList boardWithOpenSpace = new ArrayList<>(Arrays.asList("O", 1));
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList("O", "X"));
        setBoardState(board3x3, boardWithOpenSpace);

        easy.markBoard(board3x3);

        assertEquals(expectedBoard, board3x3.getSpaces());
    }

    @Test
    void aiMakesTheRightMoveWithTwoSpotsLeft() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "X", 1, 2,
                "O", "X", "O",
                "X", "O", "O"));
        ArrayList expectedBoardState = new ArrayList<>(Arrays.asList(
                "X", 1, "X",
                "O", "X", "O",
                "X", "O", "O"));
        setBoardState(board3x3, boardState);

        hard.markBoard(board3x3);

        assertEquals(expectedBoardState, board3x3.getSpaces());
    }

    @Test
    void aiBlocksOpponentWhoIsAboutToWin() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "O", 1, 2,
                "O", "X", 5,
                6, 7, 8));

        ArrayList expectedBoardState = new ArrayList<>(Arrays.asList(
                "O", 1, 2,
                "O", "X", 5,
                "X", 7, 8));
        setBoardState(board3x3, boardState);

        hard.markBoard(board3x3);

        assertEquals(expectedBoardState, board3x3.getSpaces());
    }

    @Test
    void aiBlocksDiagonalHumanWin() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "X", "X", "O",
                3, "O", 5,
                6, 7, 8));
        ArrayList expectedBoardState = new ArrayList<>(Arrays.asList(
                "X", "X", "O",
                3, "O", 5,
                "X", 7, 8));
        setBoardState(board3x3, boardState);

        hard.markBoard(board3x3);

        assertEquals(expectedBoardState, board3x3.getSpaces());
    }

    @Test
    void aiValuesAWinOverABlock() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                6, 7, 8));
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                "X", "O", 2,
                "X", "O", 5,
                "X", 7, 8));
        setBoardState(board3x3, boardState);

        hard.markBoard(board3x3);

        assertEquals(expectedBoard, board3x3.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "O", "X", "X", "O",
                "X", "O", "X", "O",
                "O", "X", "O", "X",
                "X", "O", 14, 15));
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                "O", "X", "X", "O",
                "X", "O", "X", "O",
                "O", "X", "O", "X",
                "X", "O", 14, "X"));
        setBoardState(board4x4, boardState);

        hard.markBoard(board4x4);

        assertEquals(expectedBoard, board4x4.getSpaces());
    }

    @Test
    void aiBlocksPlayerOnA4x4Board9SpotsLeft() {
        ArrayList boardState = new ArrayList<>(Arrays.asList(
                "O", "X", 2, 3,
                4, "O", "X", 7,
                8, 9, "O", "X",
                12, 13, 14, 15));
        ArrayList expectedBoard = new ArrayList<>(Arrays.asList(
                "O", "X", 2, 3,
                4, "O", "X", 7,
                8, 9, "O", "X",
                12, 13, 14, "X"));
        setBoardState(board4x4, boardState);

        hard.markBoard(board4x4);

        assertEquals(expectedBoard, board4x4.getSpaces());
    }
}

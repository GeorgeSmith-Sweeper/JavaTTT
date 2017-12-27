import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class TestBoard {

    @Test
    void createBoard() {
        int size = 3;
        Board board = new Board(size);
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
        for (int i = 0; i < size*size; i++) {
            expectedBoard.add(i);
        }

        assertEquals(board.createBoard(), expectedBoard);
    }

    @Test
    void getSpacesReturnsTheCurrentStateOfTheBoard() {
        int size = 3;
        Board board = new Board(size);
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
        for (int i = 0; i < size*size; i++) {
            expectedBoard.add(i);
        }
        board.createBoard();

        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void spaceWithinBoundsReturnsTrueIfSpaceIsWithinBounds() {
        String userInput = "0";
        int size = 3;
        Board board = new Board(size);
        board.createBoard();

        assertTrue(board.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfSpaceIsNotWithinBounds() {
        String userInput = "13";
        int size = 3;
        Board board = new Board(size);
        board.createBoard();

        assertFalse(board.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfInputIsNotANumber() {
        String userInput = "S";
        int size = 3;
        Board board = new Board(size);
        board.createBoard();

        assertFalse(board.spaceWithinBounds(userInput));
    }

    @Test
    void spaceWithinBoundsReturnsFalseIfInputIsEmpty() {
        String userInput = "";
        int size = 3;
        Board board = new Board(size);
        board.createBoard();

        assertFalse(board.spaceWithinBounds(userInput));
    }

    @Test
    void updateSpace() {
        String userInput = "1";
        String userSymbol = "X";
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        ArrayList expectedBoard = new ArrayList(Arrays.asList(0, "X", 2, 3, 4, 5, 6, 7, 8));
        MockPlayer playerOne = new MockPlayer("X");
        board.updateSpace(userInput, playerOne);
        assertEquals(expectedBoard, board.getSpaces());
    }

    @Test
    void gameIsTieReturnsTrueIfBoardIsFull() {
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        ArrayList expectedBoard = new ArrayList(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X"));

        assertTrue(board.gameIsTie(expectedBoard));
    }

    @Test
    void gameIsTieReturnsFalseIfBoardIsNotFull() {
        int size = 3;
        Board board = new Board(size);
        board.createBoard();
        ArrayList expectedBoard = new ArrayList(Arrays.asList(0, "X", "X", "X", "X", "X", "X", "X", "X"));

        assertFalse(board.gameIsTie(expectedBoard));
    }
}

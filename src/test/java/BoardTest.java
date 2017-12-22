import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class BoardTest {

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
    void spaceWithinBoundsReturnsFalseIfSpaceIsWithinBounds() {
        String userInput = "13";
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
        board.updateSpace(userInput, userSymbol);
        assertEquals(expectedBoard, board.getSpaces());
    }
}

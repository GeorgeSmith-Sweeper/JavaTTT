import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
}

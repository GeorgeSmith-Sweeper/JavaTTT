import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class BoardTest {

    @Test
    void createBoard() {
        int size = 3;
        Board board = new Board(size);
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
        for (int i = 0; i < expectedBoard.size(); i++) {
            expectedBoard.add(i);
        }

        assertEquals(board.createBoard(), expectedBoard);
    }

    @Test
    void getSpacesReturnsTheCurrentStateOfTheBoard() {
        int size = 3;
        Board board = new Board(size);
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
        board.createBoard();

        assertEquals(expectedBoard, board.getSpaces());
    }


}

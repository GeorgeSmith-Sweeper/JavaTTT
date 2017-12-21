import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BoardTest {

    @Test
    void createBoard() {
        int size = 3;
        Board board = new Board(size);
        ArrayList<Integer> expectedBoard = new ArrayList<Integer>(size * size);
        for (int i = 0; i < size * size; i++) {
            expectedBoard.add(i);
        }

    }
}

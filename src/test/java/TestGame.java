import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsTheCorrectMethods() {
        int size = 3;
        MockUi ui = new MockUi(System.in);
        Board board = new Board(size);
        board.createBoard();
        Game game = new Game(ui, board);
        game.start();

        assertEquals(true, ui.displayWasCalled());
        assertEquals(true, ui.inputWasCalled());
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {

    @Test
    void testStartCallsDisplay() {
        MockUi ui = new MockUi();
        Game game = new Game(ui);
        game.start();

        assertEquals(true, ui.displayWasCalled());
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    @Test
    void getPlayerSymbolReturnsThePlayersSymbol() {
        Player playerOne = new Player("X");

        assertEquals("X", playerOne.getSymbol());
    }
}

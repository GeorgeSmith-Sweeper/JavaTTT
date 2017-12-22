import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void display() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Ui ui = new Ui(System.in);
        String message = "Hello";
        ui.display(message);

        assertEquals(message, outputStream.toString());
    }

    @Test
    void presentBoardConvertsTheBoardSpacesToAString() {
        int size = 3;
        Ui ui = new Ui(System.in);
        Board board = new Board(size);
        board.createBoard();

        String expectedBoard = "0 | 1 | 2\n" +
                               "=========\n" +
                               "3 | 4 | 5\n" +
                               "=========\n" +
                               "6 | 7 | 8\n";

        assertEquals(expectedBoard, ui.presentBoard(board.getSpaces()));
    }

}
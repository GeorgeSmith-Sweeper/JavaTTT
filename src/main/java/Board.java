import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Board {
    private int size;
    private ArrayList<Integer> spaces = new ArrayList<Integer>(size*size);

    public Board(int size) {
        this.size = size;
    }

    public ArrayList<Integer> createBoard() {
        for (int i = 0; i < size*size; i++) {
            this.spaces.add(i);
        }
        return this.spaces;
    }

    public ArrayList getSpaces() {
        return spaces;
    }

    public boolean spaceWithinBounds(String userInput) {
        int convertedInput = Integer.parseInt(userInput);
        spaces = getSpaces();
        return spaces.contains(convertedInput);
    }

    public void updateSpace(String userInput, String userSymbol) {
        ArrayList boardSpaces = getSpaces();
        int boardIndex = Integer.parseInt(userInput);
        boardSpaces.set(boardIndex, userSymbol);
    }
}

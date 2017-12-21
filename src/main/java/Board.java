import java.util.ArrayList;


public class Board {
    private int size;
    private ArrayList<Integer> spaces = new ArrayList<Integer>(size*size);

    public Board(int size) {
        this.size = size;
    }

    public ArrayList<Integer> createBoard() {
        for (int i = 0; i < this.spaces.size(); i++) {
            this.spaces.add(i);
        }
        return this.spaces;
    }
}

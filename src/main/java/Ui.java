import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ui implements IUserInterface {
    private InputStream in;
    public Ui(InputStream in) {
        this.in = in;
    }

    public void display(String message) {
        System.out.print(message);
    }

    public String presentBoard(ArrayList spaces) {
        int numRows = (int) Math.sqrt(spaces.size());
        String pipe = " | ";
        String divider = "=========";
        ArrayList rowHolder = new ArrayList();
        ArrayList fullBoard = new ArrayList();
        StringBuilder sb = new StringBuilder();

        for (int space = 0; space < spaces.size(); space++) {
            if (spaces.get(space) instanceof Integer) {
                String convertedSpace = spaces.get(space).toString();
                rowHolder.add(convertedSpace);
            } else {
                rowHolder.add(spaces.get(space));
            }
            rowHolder.add(pipe);
            if (rowHolder.size() == (numRows * 2)) {
                rowHolder.remove(rowHolder.size() - 1);
                String rowString = String.join("", rowHolder) + "\n";
                fullBoard.add(rowString);
                fullBoard.add(divider + "\n");
                rowHolder.clear();
            }
        }
        fullBoard.remove(fullBoard.size() - 1);
        String finalBoard = String.join("", fullBoard);

        return finalBoard;
    }
}

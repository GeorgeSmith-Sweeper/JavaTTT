public class Main {
    public static void main (String[] args) {
        int size = 3;
        Ui ui = new Ui(System.in);
        Board board = new Board(size);
        Game game = new Game(ui, board);
        game.start();
    }
}

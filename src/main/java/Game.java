public class Game {
    private IUserInterface ui;
    private Board board;

    public Game(IUserInterface ui, Board board) {
        this.ui = ui;
        this.board = board;
    }

    public void start() {
        ui.display("Hello");
        ui.display(ui.presentBoard(board.getSpaces()));
    }
}

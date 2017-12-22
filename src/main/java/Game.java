public class Game {
    private IUserInterface ui;
    private Board board;

    public Game(IUserInterface ui, Board board) {
        this.ui = ui;
        this.board = board;
    }

    public void start() {
        boolean gameIsTie = false;
        ui.display(ui.presentBoard(board.getSpaces()));
        String userSymbol = "X";
        while (!gameIsTie) {
            ui.display("Please pick a spot!");
            String userInput = ui.getInput();

            if (!board.spaceWithinBounds(userInput)) {
                ui.display("Invalid spot, pick again!");
            } else {
                board.updateSpace(userInput, userSymbol);
            }

            gameIsTie = board.gameIsTie(board.getSpaces());
            ui.display(ui.presentBoard(board.getSpaces()));
        }
    }
}

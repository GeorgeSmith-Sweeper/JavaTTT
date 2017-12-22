public class Game implements IGame {
    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IUserInterface ui;
    private Board board;

    public Game(IUserInterface ui, Board board, IPlayer playerOne, IPlayer playerTwo) {
        this.ui = ui;
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    public void start() {
        boolean gameIsTie = false;
        ui.display(ui.presentBoard(board.getSpaces()));
        IPlayer currentPlayer = this.playerOne;


        while (!gameIsTie) {
            ui.display("Please pick a spot!");
            String userInput = ui.getInput();

            if (!board.spaceWithinBounds(userInput)) {
                ui.display("Invalid spot, pick again!");
            } else {
                board.updateSpace(userInput, currentPlayer);
            }

            gameIsTie = board.gameIsTie(board.getSpaces());

            if (gameIsTie) {
                ui.display("No one wins! Game over!");
            }

            ui.display(ui.presentBoard(board.getSpaces()));

            // switch turns
            if (this.playerOne.equals(currentPlayer)) {
                currentPlayer = this.playerTwo;
            } else {
                currentPlayer = this.playerOne;
            }
        }

    }
}

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
        IPlayer currentPlayer = playerOne;

        while (!gameIsTie) {
            ui.display("Please pick a spot!");
            inputValidation(currentPlayer);
            gameIsTie = board.gameIsTie(board.getSpaces());
            if (gameIsTie) {
                ui.display("No one wins! Game over!");
            }
            ui.display(ui.presentBoard(board.getSpaces()));
            currentPlayer = playerOne.equals(currentPlayer) ? playerTwo : playerOne;
        }
    }

    private void inputValidation(IPlayer currentPlayer) {
        String userInput = ui.getInput();

        while (!board.spaceWithinBounds(userInput)) {
            ui.display("Invalid spot, pick again!");
            userInput = ui.getInput();
        }
        board.updateSpace(userInput, currentPlayer);
    }
}

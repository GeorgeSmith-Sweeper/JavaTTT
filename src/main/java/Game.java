public class Game {
    private IUserInterface ui;

    public Game(IUserInterface ui) {
        this.ui = ui;
    }

    public void start() {
        ui.display();
    }
}

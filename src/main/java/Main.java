public class Main {
    public static void main (String[] args) {
        Ui ui = new Ui();
        Game game = new Game(ui);
        game.start();
    }
}

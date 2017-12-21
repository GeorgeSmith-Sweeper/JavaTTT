import java.io.InputStream;

public class Ui implements IUserInterface {
    private InputStream in;
    public Ui(InputStream in) {
        this.in = in;
    }

    public void display(String message) {
        System.out.print(message);
    }
}

import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean wasDisplayCalled = false;

    public boolean displayWasCalled() {
        return wasDisplayCalled;
    }

    public void display(String message) {
        wasDisplayCalled = true;
    }

    public String presentBoard(ArrayList spaces) {
        return "";
    }
}

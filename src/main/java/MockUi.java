import java.io.InputStream;
import java.util.ArrayList;

public class MockUi implements IUserInterface {
    private boolean wasDisplayCalled = false;
    private boolean getInputWasCalled = false;
    private String ourMessage;

    public MockUi(InputStream in) {

    }

    public boolean displayWasCalled() {
        return wasDisplayCalled;
    }

    public void display(String message) {
        wasDisplayCalled = true;
    }

    public String getInput() {
        getInputWasCalled = true;
        return "";
    }

    public boolean inputWasCalled() {
        return getInputWasCalled;
    }

    public String presentBoard(ArrayList spaces) {
        return "";
    }
}

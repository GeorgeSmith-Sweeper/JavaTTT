public class MockUi implements IUserInterface {
    private boolean wasDisplayCalled = false;

    public boolean displayWasCalled() {
        return wasDisplayCalled;
    }

    public void display() {
        wasDisplayCalled = true;
    }
}

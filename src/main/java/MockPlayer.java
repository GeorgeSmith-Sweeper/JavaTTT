public class MockPlayer implements IPlayer{
    private String symbol;

    public MockPlayer(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}

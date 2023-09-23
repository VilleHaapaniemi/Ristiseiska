package Cards;

public enum Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES;

    private final Character[] suitAsciiCodes = {'♥', '♦', '♣', '♠'};

    public Character getAsciiCode() {
        return suitAsciiCodes[this.ordinal()];
    }
}

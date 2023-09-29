package Cards;

public enum Suit {
    HEARTS,
    CLUBS,
    DIAMONDS,
    SPADES;

    private final Character[] suitAsciiCodes = {'♥', '♣', '♦', '♠'};

    public Character getAsciiCode() {
        return suitAsciiCodes[this.ordinal()];
    }
}

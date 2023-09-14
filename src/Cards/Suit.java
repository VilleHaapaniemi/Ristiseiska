package Cards;

public enum Suit {
    Hearts,
    Diamonds,
    Clubs,
    Spades;

    private final Character[] suitAsciiCodes = {'♥', '♦', '♣', '♠'};

    public Character getAsciiCode() {
        return suitAsciiCodes[this.ordinal()];
    }
}

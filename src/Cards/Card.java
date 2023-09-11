package Cards;

public class Card {
    private final Suit suit;
    private final String faceValue;
    private final Integer rank;

    public Card(Suit suit, String faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
        this.rank = getRankFromFaceValue(faceValue);
    }
    @Override
    public String toString() {
        return getAsciiCodeForSuit(this.suit) + this.faceValue;
    }
    private Integer getRankFromFaceValue(String faceValue) {
        return switch (faceValue) {
            case "A" -> 0;
            case "2" -> 1;
            case "3" -> 2;
            case "4" -> 3;
            case "5" -> 4;
            case "6" -> 5;
            case "7" -> 6;
            case "8" -> 7;
            case "9" -> 8;
            case "10" -> 9;
            case "J" -> 10;
            case "Q" -> 11;
            case "K" -> 12;
            default -> throw new IllegalStateException("Unexpected value: " + faceValue);
        };
    }
    private Character getAsciiCodeForSuit(Suit suit) {
        return switch (suit) {
            case Hearts -> '♥';
            case Diamonds -> '♦';
            case Clubs -> '♣';
            case Spades -> '♠';
        };
    }
}

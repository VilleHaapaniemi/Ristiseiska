package Cards;

public class Card {
    private final Enum<Suit> suit;
    private final String faceValue;
    private final Integer rank;

    public Card(Enum<Suit> suit, String faceValue, Integer rank) {
        this.suit = suit;
        this.faceValue = faceValue;
        this.rank = rank;
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
}

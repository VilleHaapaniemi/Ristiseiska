package Cards;

import Util.TextColor;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final String faceValue;
    private final Integer rank;

    public Card(Suit suit, String faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
        this.rank = getRankFromFaceValue(faceValue);
    }

    public Suit getSuit() {
        return suit;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public String toString() {
        if (this.suit.equals(Suit.Hearts) || this.suit.equals(Suit.Diamonds))
            return TextColor.ANSI_RED + this.suit.getAsciiCode() + this.faceValue + TextColor.ANSI_RESET;
        else
            return TextColor.ANSI_MAGENTA + this.suit.getAsciiCode() + this.faceValue + TextColor.ANSI_RESET;
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
    @Override
    public int compareTo(Card o) {
        return this.rank - o.rank;
    }
    public static Comparator<Card> getPlayerHandComparator() {
        // Player hand to display is ordered first by Suit and then by Rank
        return Comparator.comparing(Card::getSuit)
                .thenComparingInt(Card::getRank);
    }
}

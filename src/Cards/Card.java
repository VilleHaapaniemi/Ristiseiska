package Cards;

import Util.TextColor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final String faceValue;
    private final Integer rank;
    private final static Set<String> faceValueLookUpSet = new HashSet<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));

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
        if (this.suit.equals(Suit.HEARTS) || this.suit.equals(Suit.DIAMONDS))
            return TextColor.ANSI_RED + this.suit.getAsciiCode() +  " " + this.faceValue + TextColor.ANSI_RESET;
        else
            return TextColor.ANSI_BLUE + this.suit.getAsciiCode() + " " +  this.faceValue + TextColor.ANSI_RESET;
    }

    private Integer getRankFromFaceValue(String faceValue) {
        return switch (faceValue.toUpperCase()) {
            case "A" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "10" -> 10;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
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
    public static boolean checkFaceValueInput(String input) {
        return faceValueLookUpSet.contains(input);
    }
}

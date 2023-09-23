package Game;

import Cards.Card;
import Cards.Suit;

import java.util.HashMap;

public final class Table {
    private static Card highestHeart;
    private static Card lowestHeart;
    private static Card highestDiamond;
    private static Card lowestDiamond;
    private static Card highestClub;
    private static Card lowestClub;
    private static Card highestSpade;
    private static Card lowestSpade;
    private static HashMap<Suit, Card[]> tableCardMap = new HashMap<>();

    private Table() {
        tableCardMap.put(Suit.HEARTS, null);
        tableCardMap.put(Suit.DIAMONDS, null);
        tableCardMap.put(Suit.CLUBS, null);
        tableCardMap.put(Suit.SPADES, null);
    }

    public static Card getHighestHeart() {
        return highestHeart;
    }

    public static void setHighestHeart(Card highestHeart) {
        Table.highestHeart = highestHeart;
    }

    public static Card getLowestHeart() {
        return lowestHeart;
    }

    public static void setLowestHeart(Card lowestHeart) {
        Table.lowestHeart = lowestHeart;
    }

    public static Card getHighestDiamond() {
        return highestDiamond;
    }

    public static void setHighestDiamond(Card highestDiamond) {
        Table.highestDiamond = highestDiamond;
    }

    public static Card getLowestDiamond() {
        return lowestDiamond;
    }

    public static void setLowestDiamond(Card lowestDiamond) {
        Table.lowestDiamond = lowestDiamond;
    }

    public static Card getHighestClub() {
        return highestClub;
    }

    public static void setHighestClub(Card highestClub) {
        Table.highestClub = highestClub;
    }

    public static Card getLowestClub() {
        return lowestClub;
    }

    public static void setLowestClub(Card lowestClub) {
        Table.lowestClub = lowestClub;
    }

    public static Card getHighestSpade() {
        return highestSpade;
    }

    public static void setHighestSpade(Card highestSpade) {
        Table.highestSpade = highestSpade;
    }

    public static Card getLowestSpade() {
        return lowestSpade;
    }

    public static void setLowestSpade(Card lowestSpade) {
        Table.lowestSpade = lowestSpade;
    }
    public static void addCardToTable(Card card) {
        InsertCardStatus insertResult = checkInsertion(card);

        switch (card.getSuit()) {
            case HEARTS:
                break;
            case DIAMONDS:
                break;
            case CLUBS:
                break;
            case SPADES:
                break;
        }
    }
    private static InsertCardStatus checkInsertion(Card card) {
        int insertedCardRank = card.getRank();

        // Get inserted Suit of inserted Card lowest and highest values in table
        Card[] lowestAndHighest = tableCardMap.get(card.getSuit());
        // Check if there are any Cards in table of inserted Card Suit
        if (lowestAndHighest == null) {
            if (insertedCardRank == 7) {
                return InsertCardStatus.SEVEN;
            } else {
                return InsertCardStatus.ILLEGAL;
            }
        }

        int lowestRankInTable = lowestAndHighest[0].getRank();
        int highestRankInTable = lowestAndHighest[1].getRank();

        // Check if inserted Card rank is one higher than the highest on table
        // Lowest rank in Table also must be 6 or lower before inserting higher Cards
        if (insertedCardRank > 7) {
            if ((insertedCardRank - highestRankInTable) == 1 && lowestRankInTable <= 6) {
                return InsertCardStatus.HIGHEST;
            } else
                return InsertCardStatus.ILLEGAL;
        }
        // Check if inserted Card rank is one lower than the lowest on table
        if (insertedCardRank < 7) {
            if ((lowestRankInTable - insertedCardRank) == 1) {
                return InsertCardStatus.LOWEST;
            } else
                return InsertCardStatus.ILLEGAL;
        }

        return InsertCardStatus.ILLEGAL;
    }
}


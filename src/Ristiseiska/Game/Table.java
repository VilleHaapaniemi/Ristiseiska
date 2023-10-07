package Ristiseiska.Game;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Suit;

import java.util.HashMap;
import java.util.Objects;

public final class Table {
    private static Card highestHeart;
    private static Card lowestHeart;
    private static Card heart7;
    private static Card highestDiamond;
    private static Card lowestDiamond;
    private static Card diamond7;
    private static Card highestClub;
    private static Card lowestClub;
    private static Card club7;
    private static Card highestSpade;
    private static Card lowestSpade;
    private static Card spade7;

    private Table() {}

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

    public static Card getHeart7() {
        return heart7;
    }

    public static void setHeart7(Card heart7) {
        Table.heart7 = heart7;
    }

    public static Card getDiamond7() {
        return diamond7;
    }

    public static void setDiamond7(Card diamond7) {
        Table.diamond7 = diamond7;
    }

    public static Card getClub7() {
        return club7;
    }

    public static void setClub7(Card club7) {
        Table.club7 = club7;
    }

    public static Card getSpade7() {
        return spade7;
    }

    public static void setSpade7(Card spade7) {
        Table.spade7 = spade7;
    }

    public static InsertCardStatus addCardToTable(Card card) {
        InsertCardStatus insertResult = checkInsertion(card);

        if (insertResult == InsertCardStatus.ILLEGAL) {
            return insertResult;
        }

        Suit addedCardSuit = card.getSuit();
        if (insertResult == InsertCardStatus.HIGHEST) {
            switch (addedCardSuit) {
                case HEARTS:
                    highestHeart = card;
                    break;
                case DIAMONDS:
                    highestDiamond = card;
                    break;
                case CLUBS:
                    highestClub = card;
                    break;
                case SPADES:
                    highestSpade = card;
                    break;
            }
        } else if (insertResult == InsertCardStatus.LOWEST) {
            switch (addedCardSuit) {
                case HEARTS:
                    lowestHeart = card;
                    break;
                case DIAMONDS:
                    lowestDiamond = card;
                    break;
                case CLUBS:
                    lowestClub = card;
                    break;
                case SPADES:
                    lowestSpade = card;
                    break;
            }
        } else if (insertResult == InsertCardStatus.SEVEN) {
            switch (addedCardSuit) {
                case HEARTS:
                    heart7 = card;
                    break;
                case DIAMONDS:
                    diamond7 = card;
                    break;
                case CLUBS:
                    club7 = card;
                    break;
                case SPADES:
                    spade7 = card;
                    break;
            }
        }
        return InsertCardStatus.LEGAL;
    }
    private static InsertCardStatus checkInsertion(Card card) {
        int insertedCardRank = card.getRank();
        Suit insertedCardSuit = card.getSuit();

        // Get inserted Suit of inserted Card lowest and highest values in table
        HashMap<String, Card> suitCards = getSuitLowestHighestAndSeven(insertedCardSuit);
        Card lowestCard = suitCards.get("lowest");
        Card highestCard = suitCards.get("highest");
        Card sevenCard = suitCards.get("seven");

        if (sevenCard == null) {
            if (insertedCardRank == 7) {
                return InsertCardStatus.SEVEN;
            } else {
                return InsertCardStatus.ILLEGAL;
            }
        }

        int lowestRankInTable = Objects.requireNonNullElse(lowestCard, sevenCard).getRank();
        int highestRankInTable = Objects.requireNonNullElse(highestCard, sevenCard).getRank();

        if (insertedCardRank > 7) {
            return handleHighCard(insertedCardRank, lowestRankInTable, highestRankInTable);
        }
        if (insertedCardRank < 7) {
            return handleLowCard(insertedCardRank, lowestRankInTable, highestRankInTable);
        }
        return InsertCardStatus.ILLEGAL;
    }
    private static InsertCardStatus handleHighCard(int insertedCardRank, int lowestRankInTable, int highestRankInTable) {
        // Check if inserted Card rank is one higher than the highest on table
        // Lowest rank in Table also must be 6 or lower before inserting higher Cards
        if ((insertedCardRank - highestRankInTable) == 1 && lowestRankInTable <= 6) {
            return InsertCardStatus.HIGHEST;
        } else
            return InsertCardStatus.ILLEGAL;
    }
    private static InsertCardStatus handleLowCard(int insertedCardRank, int lowestRankInTable, int highestRankInTable) {
        // Check if inserted Card rank is one lower than the lowest on table
        if ((lowestRankInTable - insertedCardRank) == 1) {
            if (insertedCardRank != 6) { // 6 can be added before higher than 7 have added
                if (highestRankInTable >= 8) { // Lower than 6 can be added after 8 have added
                    return InsertCardStatus.LOWEST;
                } else {
                    return InsertCardStatus.ILLEGAL;
                }
            } else {
                return InsertCardStatus.LOWEST;
            }
        } else {
            return InsertCardStatus.ILLEGAL;
        }
    }
    private static HashMap<String, Card> getSuitLowestHighestAndSeven(Suit suit) {
        HashMap<String, Card> suitCardsMap = new HashMap<>();
        switch (suit) {
            case HEARTS:
                suitCardsMap.put("lowest", lowestHeart);
                suitCardsMap.put("highest", highestHeart);
                suitCardsMap.put("seven", heart7);
                break;
            case DIAMONDS:
                suitCardsMap.put("lowest", lowestDiamond);
                suitCardsMap.put("highest", highestDiamond);
                suitCardsMap.put("seven", diamond7);
                break;
            case CLUBS:
                suitCardsMap.put("lowest", lowestClub);
                suitCardsMap.put("highest", highestClub);
                suitCardsMap.put("seven", club7);
                break;
            case SPADES:
                suitCardsMap.put("lowest", lowestSpade);
                suitCardsMap.put("highest", highestSpade);
                suitCardsMap.put("seven", spade7);
                break;
        }
        return suitCardsMap;
    }
    public static void displayTableCards() {
        String str = "\t" + (highestHeart != null ? highestHeart : "[ ]") + " " +
                "\t" + (highestClub != null ? highestClub : "[ ]") + " " +
                "\t" + (highestDiamond != null ? highestDiamond : "[ ]") + " " +
                "\t" + (highestSpade != null ? highestSpade : "[ ]") +
                "\n" +
                "\t" + (heart7 != null ? heart7 : "[ ]") + " " +
                "\t" + (club7 != null ? club7 : "[ ]") + " " +
                "\t" + (diamond7 != null ? diamond7 : "[ ]") + " " +
                "\t" + (spade7 != null ? spade7 : "[ ]") +
                "\n" +
                "\t" + (lowestHeart != null ? lowestHeart : "[ ]") + " " +
                "\t" + (lowestClub != null ? lowestClub : "[ ]") + " " +
                "\t" + (lowestDiamond != null ? lowestDiamond : "[ ]") + " " +
                "\t" + (lowestSpade != null ? lowestSpade : "[ ]");
        System.out.println("\n\t*** Table ***\n");
        System.out.println(str);
        System.out.println();
    }
}


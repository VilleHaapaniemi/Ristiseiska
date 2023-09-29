package Game;

import Cards.Card;
import Cards.Suit;

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

    public static void addCardToTable(Card card) {
        InsertCardStatus insertResult = checkInsertion(card);

        if (insertResult == InsertCardStatus.ILLEGAL) {
            System.out.println("Illegal");
            //Todo: Exception
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
    }
    private static InsertCardStatus checkInsertion(Card card) {
        int insertedCardRank = card.getRank();
        Suit insertedCardSuit = card.getSuit();

        // Get inserted Suit of inserted Card lowest and highest values in table
        Card[] lowestAndHighest = getSuitLowestAndHighest(insertedCardSuit);

        // Check if there are any Cards in table of inserted Card Suit
        if (lowestAndHighest[0] == null || lowestAndHighest[1] == null) {
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
    private static Card[] getSuitLowestAndHighest(Suit suit) {
        Card[] result = new Card[2];
        switch (suit) {
            case HEARTS:
                result[0] = lowestHeart;
                result[1] = highestHeart;
                break;
            case DIAMONDS:
                result[0] = lowestDiamond;
                result[1] = highestDiamond;
                break;
            case CLUBS:
                result[0] = lowestClub;
                result[1] = highestClub;
                break;
            case SPADES:
                result[0] = lowestSpade;
                result[1] = highestSpade;
                break;
        }
        return result;
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


package Ristiseiska.Cards;

import Ristiseiska.Exceptions.IllegalCardInputException;

public enum Suit {
    HEARTS,
    CLUBS,
    DIAMONDS,
    SPADES;

    private final Character[] suitAsciiCodes = {'♥', '♣', '♦', '♠'};

    public Character getAsciiCode() {
        return suitAsciiCodes[this.ordinal()];
    }

    public static Suit getSuitByChar(Character c) {
        return switch (c) {
            case 'h' -> HEARTS;
            case 'c' -> CLUBS;
            case 'd' -> DIAMONDS;
            case 's' -> SPADES;
            default -> throw new IllegalCardInputException("Input character did not match any Suit");
        };
    }
}
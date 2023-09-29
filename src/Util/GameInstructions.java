package Util;

import Cards.Card;
import Cards.Suit;
import Exceptions.IllegalCardInputException;
import Exceptions.IllegalCardInputLengthException;
import Game.Game;
import Player.Player;
import Game.Table;

import java.util.Scanner;
import java.util.Set;

public final class GameInstructions {
    private GameInstructions() {}

    public static void start() {
        String str = TextColor.ANSI_YELLOW + """
                Welcome to play the Ristiseiska!
                """ + TextColor.ANSI_RESET;
        System.out.println(str);
    }
    public static void introduceStartingPlayer() {
        Card clubs7 = new Card(Suit.CLUBS, "7");
        System.out.println("Player who got " + clubs7 +  " will start the game and insert that card in a table");
        System.out.println("Player to start is: " + Game.getCurrentTurnPlayer().getName());
        System.out.println(clubs7 + " starting card is added on table");
    }
    public static void introduceCurrentPlayer() {
        System.out.println("Next player: " + Game.getCurrentTurnPlayer().getName());
        System.out.println("Press enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearConsole();
    }
    public static void displayCurrentPlayerHand() {
        Table.displayTableCards(); // Display table to current Player

        Player currentPlayer = Game.getCurrentTurnPlayer();
        Set<Card> hand = currentPlayer.getHand();
        Card previousCard = null;
        // Display current Player hand
        System.out.println("Your hand:");
        for (Card card : hand) {
            if (previousCard != null && !previousCard.getSuit().equals(card.getSuit())) {
                System.out.println();
            }
            System.out.print(card + " ");
            previousCard = card;
        }
    }
    public static Card askPlayerToAddCard() {
        System.out.println("\nSelect which card add to table");
        System.out.println("h = heart, c = club, d = diamond, s = spade");
        System.out.println("a = ace, j = jack, q = queen, k = king");
        System.out.println("Type suit and card face value (example: s7 or hk)");
        Scanner scanner = new Scanner(System.in);
        String cardInput = scanner.nextLine();

        Card addedCard = null;
        try {
            addedCard = checkCardUserInput(cardInput);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return addedCard;
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static Card checkCardUserInput(String cardInput) {
        if (!(cardInput.length() == 2 || cardInput.length() == 3)) {
            throw new IllegalCardInputLengthException("Card input is not 2 or 3 characters long");
        }

        Character suitCharacter = cardInput.charAt(0);
        String faceValueString;
        if (cardInput.length() == 3) {
            if (cardInput.charAt(1) == '1' && cardInput.charAt(2) == '0') {
                faceValueString = "10";
            } else {
                throw new IllegalCardInputException("Illegal input");
            }
        } else {
            faceValueString = String.valueOf(cardInput.charAt(1)).toUpperCase();
        }

        Suit suit = Suit.getSuitByChar(suitCharacter);
        boolean faceValueInputValid = Card.checkFaceValueInput(faceValueString);
        if (!faceValueInputValid) {
            return null;
        }
        return new Card(suit, faceValueString);
    }

}

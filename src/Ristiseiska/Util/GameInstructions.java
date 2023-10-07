package Ristiseiska.Util;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Game.Game;
import Ristiseiska.Game.Table;
import Ristiseiska.Player.Player;

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
    public static void askPlayerToAddCardInstructions() {
        System.out.println("\nSelect which card add to table");
        System.out.println("h = heart, c = club, d = diamond, s = spade");
        System.out.println("a = ace, j = jack, q = queen, k = king");
        System.out.println("x = Skip turn");
        System.out.println("Type suit and card face value (example: s7 or hk)");
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

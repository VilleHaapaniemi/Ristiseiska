package Util;

import Cards.Card;
import Cards.Suit;
import Game.Game;
import Player.Player;

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
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void displayCurrentPlayerHand() {
        Player currentPlayer = Game.getCurrentTurnPlayer();
        Set<Card> hand = currentPlayer.getHand();
        System.out.println(hand);
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

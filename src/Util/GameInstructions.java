package Util;

import Cards.Card;
import Cards.Suit;
import Game.Game;

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
}

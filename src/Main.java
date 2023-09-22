import Cards.Card;
import Cards.Deck;
import Cards.Suit;
import Game.Game;
import Util.GameInstructions;
import Util.GameSelections;

public class Main {
    public static void main(String[] args) {
        GameInstructions.start();
        GameSelections.askPlayerCount();
        GameSelections.askPlayerNames();

        Game.setPlayers(GameSelections.getPlayerNames());
        Deck drawingDeck = new Deck();
        drawingDeck.generateBasicDeck();
        drawingDeck.shuffleDeck();
        Game.drawHandsToPlayers(drawingDeck);
        Game.resolveStartingPlayer();


        while (!Game.isGameFinished()) {
            System.out.println(new Card(Suit.Hearts, "J"));
            System.out.println(new Card(Suit.Clubs, "6"));

            Game.setGameFinished(true);
        }
    }
}

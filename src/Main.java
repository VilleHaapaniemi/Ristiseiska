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

        Game game = new Game(GameSelections.getPlayerNames());
        Deck drawingDeck = new Deck();
        drawingDeck.generateBasicDeck();
        drawingDeck.shuffleDeck();
        game.drawHandsToPlayers(drawingDeck);

        while (!game.isGameFinished()) {
            System.out.println(new Card(Suit.Hearts, "J"));
            System.out.println(new Card(Suit.Clubs, "6"));

            game.setGameFinished(true);
        }
    }
}

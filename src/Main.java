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
        Game.setPlayers(GameSelections.getPlayerNames()); // Set the inserted players to the Game

        // Generate new Deck of 52 Cards and shuffle it
        Deck drawingDeck = new Deck();
        drawingDeck.generateBasicDeck();
        drawingDeck.shuffleDeck();

        // Draw all the Cards to Players and resolve Player who start the game
        Game.drawHandsToPlayers(drawingDeck);
        Game.resolveStartingPlayer();
        GameInstructions.introduceStartingPlayer();

        // Remove starting Card from Player hand. Add Card to table
        Card club7 = new Card(Suit.CLUBS, "7");
        Game.getCurrentTurnPlayer().removeCardFromHand(club7);

        while (!Game.isGameFinished()) {


            Game.setGameFinished(true);
        }
    }
}

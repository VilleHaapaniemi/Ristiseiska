import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Deck;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Game.Game;
import Ristiseiska.Game.Table;
import Ristiseiska.Util.GameInstructions;
import Ristiseiska.Util.GameSelections;

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
        Table.addCardToTable(club7);
        Game.passTurnToNextPlayer();

        while (!Game.isGameFinished()) {
            Table.displayTableCards();
            GameInstructions.introduceCurrentPlayer();
            GameInstructions.clearConsole();
            Table.displayTableCards();
            GameInstructions.displayPlayerHand(Game.getCurrentTurnPlayer());
            GameInstructions.askPlayerToAddCardInstructions();

            boolean cardAdded = Game.letCurrentPlayerAddCardToTable();
            if (!cardAdded && !Game.isIsFirstRound()) {
                GameInstructions.clearConsole();
                Table.displayTableCards();
                Card givenCard = Game.getCardFromPreviousPlayer();
                Game.getCurrentTurnPlayer().addCardToHand(givenCard);
            }
            GameInstructions.clearConsole();
            Game.passTurnToNextPlayer();
            // Game.setGameFinished(true);
        }
    }
}

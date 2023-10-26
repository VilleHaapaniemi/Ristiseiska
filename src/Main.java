import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Deck;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Game.AddedCardResult;
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

            AddedCardResult addedCardResult = Game.letCurrentPlayerAddCardToTable();
            if (addedCardResult.equals(AddedCardResult.SKIP) && !Game.isIsFirstRound()) { // On first round no need to ask Card from others
                GameInstructions.clearConsole();
                Table.displayTableCards();
                Card givenCard = Game.getCardFromPreviousPlayer();
                Game.getCurrentTurnPlayer().addCardToHand(givenCard);
            }
            if (addedCardResult.equals(AddedCardResult.INSERTED_CUT)) { // Added card is Ace or King
                GameInstructions.clearConsole();
                boolean playerContinuesTurn = GameInstructions.askPlayerToContinueTurn();
                // If player wants to continue turn. Continue the main while loop without ending on pass turn to next player later
                if (playerContinuesTurn) {
                    continue;
                }
            }

            GameInstructions.clearConsole();
            Game.passTurnToNextPlayer();
        }
    }
}

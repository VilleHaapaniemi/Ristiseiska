import Cards.Card;
import Cards.Deck;
import Cards.Suit;
import Game.Game;
import Game.InsertCardStatus;
import Game.Table;
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
        Table.addCardToTable(club7);
        Game.passTurnToNextPlayer();

        while (!Game.isGameFinished()) {
            Table.displayTableCards();
            GameInstructions.introduceCurrentPlayer();
            GameInstructions.displayCurrentPlayerHand();
            GameInstructions.askPlayerToAddCardInstructions();

            // Ask Card from Player and loop while given Card could be added to Table without errors
            Card addedCard;
            boolean playerHaveCard;
            InsertCardStatus status = null;
            do {
                // Ask Player input to add Card to Table
                addedCard = Game.askPlayerToAddCard();
                // Check if Player have given Card
                playerHaveCard = Game.getCurrentTurnPlayer().handContainsCard(addedCard);
                if (!playerHaveCard) {
                    System.out.println("You don't have this card in your hand. Please try again.");
                    continue;
                } else {
                    status = Table.addCardToTable(addedCard); // Add is legal or illegal
                }
                if (status.equals(InsertCardStatus.ILLEGAL)) {
                    System.out.println("Illegal insertion. Please try again.");
                }
            } while (!playerHaveCard || !status.equals(InsertCardStatus.LEGAL));

            Game.getCurrentTurnPlayer().removeCardFromHand(addedCard);
            Game.passTurnToNextPlayer();
            //Game.setGameFinished(true);
        }
    }
}

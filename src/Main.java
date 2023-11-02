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
        GameSelections.askHumanPlayerCount();
        GameSelections.askPlayerNames();
        GameSelections.askAIPlayerCount();
        Game.setHumanPlayers(GameSelections.getPlayerNames());
        Game.setAIPlayers(GameSelections.getAIPlayerCount());

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

            AddedCardResult addedCardResult = Game.letCurrentPlayerAddCardToTable();
            if (addedCardResult.equals(AddedCardResult.SKIP) && !Game.isIsFirstRound()) { // On first round no need to ask Card from others
                GameInstructions.clearConsole();
                Table.displayTableCards();
                Card givenCard = Game.getCardFromPreviousPlayer();
                //TODO: Not check if other player have only one card left
                Game.getCurrentTurnPlayer().addCardToHand(givenCard);

            } else if (addedCardResult.equals(AddedCardResult.INSERTED_CUT)) { // Added card is Ace or King
                System.out.println();
                boolean playerContinuesTurn = Game.getCurrentTurnPlayer().askPlayerToContinueTurn();
                // If player wants to continue turn. Continue the main while loop without ending on pass turn to next player later
                if (playerContinuesTurn) {
                    continue;
                }

            } else if (addedCardResult.equals(AddedCardResult.WIN)) {
                Game.setGameFinished(true);
                break;
            }
            GameInstructions.clearConsole();
            Game.passTurnToNextPlayer();
        }
    }
}

package Ristiseiska.Player;

import Ristiseiska.Cards.Card;
import Ristiseiska.Game.InsertCardStatus;
import Ristiseiska.Game.Table;
import Ristiseiska.Util.GameInstructions;
import Ristiseiska.Util.GameSelections;

import java.util.Set;

public class HumanPlayer extends Player{
    public HumanPlayer(String name) {
        super(name);
    }

    public HumanPlayer(String name, Set<Card> hand) {
        super(name, hand);
    }

    @Override
    public Card giveCardToOtherPlayer() {
        GameInstructions.displayPlayerHand(this);
        GameInstructions.askPlayerToGiveCardInstructions();

        // Ask Card from Player and loop while given Card could be added to Table without errors
        Card givenCard;
        boolean cardFound = false;
        do {
            givenCard = GameSelections.askPlayerToAddCard();
            if (givenCard == null) {
                System.out.println("Illegal input. Please try again");
                continue;
            }
            if (handContainsCard(givenCard)) {
                cardFound = true;
            } else {
                System.out.println("You don't have this card in your hand. Please try again");
            }
        } while (!cardFound);

        removeCardFromHand(givenCard);
        return givenCard;
    }

    @Override
    public Card getCardFromPlayer() {
        Card addedCard;
        boolean playerHaveCard;
        InsertCardStatus status = null;

        do {
            // Ask Player input to add Card to Table
            addedCard = GameSelections.askPlayerToAddCard();
            // If Player don't have any Card to add, addedCard is null
            if (addedCard == null) {
                return null;
            }
            // Check if Player have given Card
            playerHaveCard = handContainsCard(addedCard);
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

        return addedCard;
    }

}

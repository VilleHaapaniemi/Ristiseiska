package Ristiseiska.Player;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Exceptions.IllegalCardInputException;
import Ristiseiska.Exceptions.IllegalCardInputLengthException;
import Ristiseiska.Game.InsertCardStatus;
import Ristiseiska.Game.Table;
import Ristiseiska.Util.GameInstructions;

import java.util.Scanner;
import java.util.TreeSet;

public class HumanPlayer extends Player{
    public HumanPlayer(String name) {
        super(name);
    }

    public HumanPlayer(String name, TreeSet<Card> hand) {
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
            givenCard = askCardFromPlayer();
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
        GameInstructions.displayPlayerHand(this);
        GameInstructions.askPlayerToAddCardInstructions();

        Card addedCard;
        boolean playerHaveCard;
        InsertCardStatus status = null;
        do {
            // Ask Player input to add Card to Table
            addedCard = askCardFromPlayer();
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

        removeCardFromHand(addedCard);
        return addedCard;
    }

    @Override
    public boolean askPlayerToContinueTurn() {
        return GameInstructions.askPlayerToContinueTurn();
    }

    private Card askCardFromPlayer() {
        Card addedCard;
        Scanner scanner = new Scanner(System.in);
        do {
            String cardInput = scanner.nextLine();
            // User input x means Player don't have any Card to add
            if (cardInput.equals("x")) {
                return null;
            }
            try {
                addedCard = checkCardUserInput(cardInput);
                if (addedCard != null) {
                    break;
                }
            } catch (RuntimeException e) { // Catch all errors which occurs when user try to add Card
                System.out.println(e.getMessage());
            }
        } while (true);
        return addedCard;
    }

    private static Card checkCardUserInput(String cardInput) {
        if (!(cardInput.length() == 2 || cardInput.length() == 3)) {
            throw new IllegalCardInputLengthException("Card input is not 2 or 3 characters long");
        }

        Character suitCharacter = cardInput.charAt(0);
        String faceValueString;
        if (cardInput.length() == 3) {
            if (cardInput.charAt(1) == '1' && cardInput.charAt(2) == '0') {
                faceValueString = "10";
            } else {
                throw new IllegalCardInputException("Illegal input");
            }
        } else {
            faceValueString = String.valueOf(cardInput.charAt(1)).toUpperCase();
        }

        Suit suit = Suit.getSuitByChar(suitCharacter);
        boolean faceValueInputValid = Card.checkFaceValueInput(faceValueString);
        if (!faceValueInputValid) {
            return null;
        }
        return new Card(suit, faceValueString);
    }
}

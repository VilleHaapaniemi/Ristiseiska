package Ristiseiska.Game;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Deck;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Exceptions.IllegalCardInputException;
import Ristiseiska.Exceptions.IllegalCardInputLengthException;
import Ristiseiska.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public final class Game {
    private static List<Player> players = new ArrayList<>();
    private static boolean gameFinished;
    private static boolean isFirstRound = true;
    private static Player currentTurnPlayer;
    private static Player startingPlayer;

    private Game() {}

    public List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> playersVal) {
        players = playersVal;
    }
    public static void setPlayers(Set<String> playerNames) {
        for (String name : playerNames) {
               players.add(new Player(name));
        }
    }

    public static Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public static void setCurrentTurnPlayer(Player currentTurnPlayer) {
        Game.currentTurnPlayer = currentTurnPlayer;
    }

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static void setGameFinished(boolean gameFinishedVal) {
        gameFinished = gameFinishedVal;
    }

    public static boolean isIsFirstRound() {
        return isFirstRound;
    }

    public static void setIsFirstRound(boolean isFirstRound) {
        Game.isFirstRound = isFirstRound;
    }

    public static void drawHandsToPlayers(Deck drawingDeck) {
        int i = 0;
        int playersCount = players.size();
        for (Card card : drawingDeck.getDeck()) {
            if (i == playersCount)
                i = 0;
            players.get(i).addCardToHand(card);
            i++;
        }
        drawingDeck.getDeck().clear();
    }
    public static void resolveStartingPlayer() {
        Card clubs7 = new Card(Suit.CLUBS, "7");
        for (Player player : players) {
            if (player.getHand().contains(clubs7)) {
                startingPlayer = player;
                currentTurnPlayer = player;
                break;
            }
        }
    }
    public static void passTurnToNextPlayer() {
        int currentPlayerIndex = players.indexOf(currentTurnPlayer);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Wrap beginning of list if last player in list
        currentTurnPlayer = players.get(nextPlayerIndex);

        // When whole round have passed set isFirstRound to false
        //TODO: Fix this
        if (isFirstRound && players.indexOf(startingPlayer) == currentPlayerIndex) {
            isFirstRound = false;
        }
    }
    public static boolean letCurrentPlayerAddCardToTable() {
        // Ask Card from Player and loop while given Card could be added to Table without errors
        Card addedCard;
        boolean playerHaveCard;
        InsertCardStatus status = null;
        do {
            // Ask Player input to add Card to Table
            addedCard = askPlayerToAddCard();
            // If Player don't have any Card to add, addedCard is null
            if (addedCard == null) {
                return false;
            }
            // Check if Player have given Card
            playerHaveCard = getCurrentTurnPlayer().handContainsCard(addedCard);
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

        getCurrentTurnPlayer().removeCardFromHand(addedCard);
        return true;
    }
    public static Card askPlayerToAddCard() {
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

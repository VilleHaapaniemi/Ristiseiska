package Ristiseiska.Game;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Deck;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Player.Player;
import Ristiseiska.Util.GameSelections;

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
        currentTurnPlayer = getNextTurnPlayer();

        // When whole round have passed set isFirstRound to false
        if (isFirstRound && currentTurnPlayer.equals(startingPlayer)) {
            isFirstRound = false;
        }
    }
    private static Player getNextTurnPlayer() {
        int currentPlayerIndex = players.indexOf(currentTurnPlayer);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Wrap beginning of list if last player in list
        return players.get(nextPlayerIndex);
    }
    public static AddedCardResult letCurrentPlayerAddCardToTable() {
        // Ask Card from Player and loop while given Card could be added to Table without errors
        Card addedCard;
        boolean playerHaveCard;
        InsertCardStatus status = null;
        do {
            // Ask Player input to add Card to Table
            addedCard = GameSelections.askPlayerToAddCard();
            // If Player don't have any Card to add, addedCard is null
            if (addedCard == null) {
                return AddedCardResult.SKIP;
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

        // Remove given Card from player hand
        getCurrentTurnPlayer().removeCardFromHand(addedCard);


        return true;
    }
    public static Card getCardFromPreviousPlayer() {
        System.out.println(currentTurnPlayer.getName() + " Skipped turn");
        Player nextPlayer = getNextTurnPlayer();
        System.out.println(nextPlayer.getName() + " can choose card from hand to give it to " + currentTurnPlayer.getName());
        System.out.println("Press enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return nextPlayer.giveCardToOtherPlayer();
    }
}

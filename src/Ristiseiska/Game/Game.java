package Ristiseiska.Game;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Deck;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Player.HumanPlayer;
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
               players.add(new HumanPlayer(name));
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
        Card addedCard = currentTurnPlayer.getCardFromPlayer();

        if (addedCard == null) {
            return AddedCardResult.SKIP;
        }

        // Remove given Card from player hand
        currentTurnPlayer.removeCardFromHand(addedCard);

        if (currentTurnPlayer.getHand().isEmpty()) {
            return AddedCardResult.WIN;
        }

        if (addedCard.getFaceValue().equals("A") || addedCard.getFaceValue().equals("K")) {
            return AddedCardResult.INSERTED_CUT;
        }

        return AddedCardResult.INSERTED;
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

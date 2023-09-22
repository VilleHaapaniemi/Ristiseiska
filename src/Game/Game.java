package Game;

import Cards.Card;
import Cards.Deck;
import Cards.Suit;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Game {
    private static List<Player> players = new ArrayList<>();
    private static boolean gameFinished;
    private static Player startingPlayer;

//    public Game(Set<String> playerNames) {
//        for (String name : playerNames) {
//            players.add(new Player(name));
//        }
//    }
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

    public static Player getStartingPlayer() {
        return startingPlayer;
    }

    public static void setStartingPlayer(Player startingPlayerVal) {
        startingPlayer = startingPlayerVal;
    }

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static void setGameFinished(boolean gameFinishedVal) {
        gameFinished = gameFinishedVal;
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
        Card clubs7 = new Card(Suit.Clubs, "7");
        for (Player player : players) {
            if (player.getHand().contains(clubs7)) {
                startingPlayer = player;
                break;
            }
        }
    }
}

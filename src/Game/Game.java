package Game;

import Cards.Card;
import Cards.Deck;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players = new ArrayList<>();
    private boolean gameFinished;

    public Game(Set<String> playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
    public void drawHandsToPlayers(Deck drawingDeck) {
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
}

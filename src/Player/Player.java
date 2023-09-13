package Player;

import Cards.Card;

import java.util.*;

public class Player {
    private String name;
    private Set<Card> hand = new TreeSet<>(Card.getPlayerHandComparator());

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Set<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Card> getHand() {
        return hand;
    }

    public void setHand(Set<Card> hand) {
        this.hand = hand;
    }
    public void addCardToHand(Card card) {
        this.hand.add(card);
    }
    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}

package Ristiseiska.Player;

import Ristiseiska.Cards.Card;
import Ristiseiska.Exceptions.CardNotFoundException;
import Ristiseiska.Exceptions.DuplicateCardException;

import java.util.Set;
import java.util.TreeSet;

public abstract class Player {
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
        boolean result = this.hand.add(card);
        if (!result) {
            throw new DuplicateCardException(card.toString());
        }
    }
    public void removeCardFromHand(Card card) {
        boolean result = this.hand.remove(card);
        if (!result) {
            throw new CardNotFoundException(card.toString());
        }
    }
    public boolean handContainsCard(Card card) {
        return hand.contains(card);
    }
    public abstract Card giveCardToOtherPlayer();
    public abstract Card getCardFromPlayer();
}

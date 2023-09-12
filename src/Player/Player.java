package Player;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, List<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    public void addCardToHand(Card card) {
        // Hand is ordered first by Suit and then rank
        Comparator<Card> cardComparator = Comparator
                .comparing(Card::getSuit)
                .thenComparingInt(Card::getRank);

        // TODO: Consider to throw exception if added card is found from hand

        // Use binary search to find correct index to add new Card to already ordered List hand
        int insertionIndex = Collections.binarySearch(this.hand, card, cardComparator);
        // Found index will always be negative because there is no same card
        // Convert index to corresponding positive value
        insertionIndex = -(insertionIndex + 1);
        this.hand.add(insertionIndex, card);
    }
    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
}

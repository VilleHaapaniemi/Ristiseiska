package Player;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
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
        // hand is ordered list. Check index with binary search where to add new Card
        int insertionIndex = Collections.binarySearch(this.hand, card);

        // Index will be corresponding negative value if not found equal rank
        if (insertionIndex < 0) {
            insertionIndex = -(insertionIndex + 1);
        }
        this.hand.add(insertionIndex, card);
    }
}

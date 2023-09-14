package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();


    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void generateBasicDeck() {
        List<Card> fullDeck = new ArrayList<>();
        Suit[] suits = Suit.values();
        String[] faceValues = getAllFaceValues();

        // Loop through all the suits and faceValue to add every Card to deck
        for (var suit : suits) {
            for (var faceValue : faceValues) {
                fullDeck.add(new Card(suit, faceValue));
            }
        }
        this.deck = fullDeck;
    }
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }
    private String[] getAllFaceValues() {
        return new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    }
}

package Cards;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();

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
    private String[] getAllFaceValues() {
        return new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    }
}

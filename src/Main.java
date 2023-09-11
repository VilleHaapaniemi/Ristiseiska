import Cards.Card;
import Cards.Deck;
import Cards.Suit;

public class Main {
    public static void main(String[] args) {
        var card = new Card(Suit.Hearts, "6");
        var deck = new Deck();
        deck.generateBasicDeck();
    }
}

import Cards.Card;
import Cards.Deck;
import Cards.Suit;
import Player.Player;

public class Main {
    public static void main(String[] args) {
        var card = new Card(Suit.Hearts, "6");
        var deck = new Deck();
        deck.generateBasicDeck();
        deck.shuffleDeck();
        System.out.println(card);
        Player player = new Player("Ville");
        player.addCardToHand(card);
        player.addCardToHand(new Card(Suit.Clubs, "6"));
        player.addCardToHand(new Card(Suit.Clubs, "9"));
        player.addCardToHand(new Card(Suit.Clubs, "A"));
        player.addCardToHand(new Card(Suit.Clubs, "7"));
        player.addCardToHand(new Card(Suit.Clubs, "5"));
        player.addCardToHand(new Card(Suit.Spades, "7"));
        player.addCardToHand(new Card(Suit.Clubs, "2"));
    }
}

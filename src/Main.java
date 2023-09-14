import Cards.Card;
import Cards.Suit;
import Util.GameInstructions;
import Util.GameSelections;

public class Main {
    public static void main(String[] args) {
        GameInstructions.start();
        GameSelections.askPlayerCount();
        GameSelections.askPlayerNames();

        while (!GameSelections.isGameFinished()) {
            System.out.println(new Card(Suit.Hearts, "J"));
            System.out.println(new Card(Suit.Clubs, "6"));

            GameSelections.setGameFinished(true);
        }
    }
}

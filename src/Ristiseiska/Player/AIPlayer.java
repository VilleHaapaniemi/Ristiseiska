package Ristiseiska.Player;

import Ristiseiska.Cards.Card;
import Ristiseiska.Game.InsertCardStatus;
import Ristiseiska.Game.Table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class AIPlayer extends Player {

    private static final HashSet<String> AIPlayerNamesList = new HashSet<>();

    static {
        String[] names = {"Luigi", "Cortana", "Sara", "Gabe", "Cliffe", "EVE", "Terminator", "Bishop"};
        AIPlayerNamesList.addAll(Arrays.asList(names));
    }

    public AIPlayer(String name) {
        super(name);
    }

    public AIPlayer(String name, TreeSet<Card> hand) {
        super(name, hand);
    }

    @Override
    public Card giveCardToOtherPlayer() {
        //TODO: Gives just first card without logic
        Card card = hand.first();
        removeCardFromHand(card);
        return card;
    }

    @Override
    public Card getCardFromPlayer() {
        //TODO: Brute force algorithm
        for (Card card : hand) {
            if (Table.addCardToTable(card).equals(InsertCardStatus.LEGAL)) {
                removeCardFromHand(card);
                return card;
            }
        }
        return null;
    }

    @Override
    public boolean askPlayerToContinueTurn() {
        //TODO: Implement logic to decide whether continue or not
        return false;
    }

    public static String getRandomAIPlayerName() {
        Iterator<String> iterator = AIPlayerNamesList.iterator();
        if (iterator.hasNext()) {
            String name = iterator.next();
            AIPlayerNamesList.remove(name);
            return name;
        } else {
            return null;
        }
    }
}

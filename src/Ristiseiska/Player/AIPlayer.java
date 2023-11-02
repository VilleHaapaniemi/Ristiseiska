package Ristiseiska.Player;

import Ristiseiska.Cards.Card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AIPlayer extends Player {

    private static HashSet<String> AIPlayerNamesList = new HashSet<>();

    static {
        String[] names = {"Luigi", "Cortana", "Sara", "Gabe", "Cliffe", "EVE", "Terminator", "Bishop"};
        AIPlayerNamesList.addAll(Arrays.asList(names));
    }

    public AIPlayer(String name) {
        super(name);
    }

    public AIPlayer(String name, Set<Card> hand) {
        super(name, hand);
    }

    @Override
    public Card giveCardToOtherPlayer() {
        return null;
    }

    @Override
    public Card getCardFromPlayer() {
        return null;
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

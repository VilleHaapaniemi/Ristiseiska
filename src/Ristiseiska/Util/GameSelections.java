package Ristiseiska.Util;

import Ristiseiska.Cards.Card;
import Ristiseiska.Cards.Suit;
import Ristiseiska.Exceptions.IllegalCardInputException;
import Ristiseiska.Exceptions.IllegalCardInputLengthException;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public final class GameSelections {
    private GameSelections() {}
    private static int playerCount;
    private static Set<String> playerNames = new LinkedHashSet<>();

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        GameSelections.playerCount = playerCount;
    }

    public static Set<String> getPlayerNames() {
        return playerNames;
    }

    public static void setPlayerNames(Set<String> playerNames) {
        GameSelections.playerNames = playerNames;
    }

    public static void askPlayerCount() {
        Scanner input = new Scanner(System.in);
        int playerCountInput;

        do {
            System.out.println(TextColor.ANSI_YELLOW + "How many players are playing (2-6)" + TextColor.ANSI_RESET);
            if (input.hasNextInt()) {
                playerCountInput = input.nextInt();
                if (playerCountInput >= 2 && playerCountInput <= 6) {
                    break;
                }
            }
            System.out.println(TextColor.ANSI_RED + "Invalid input. Please enter a number between 2 and 6." + TextColor.ANSI_RESET);
            input.nextLine(); // Consume the invalid input
        } while (true);
        playerCount = playerCountInput;
    }
    public static void askPlayerNames() {
        Scanner input = new Scanner(System.in);
        String nameInput;

        for (int i = 0; i < playerCount; i++) {
            System.out.println(TextColor.ANSI_YELLOW + "Enter Player " + (i + 1) + " name:" + TextColor.ANSI_RESET);
            nameInput = input.nextLine();

            if (nameInput.isEmpty()) {
                System.out.println(TextColor.ANSI_RED + "Name cannot be empty. Please try again."+ TextColor.ANSI_RESET);
                i--;
                continue;
            }
            if (playerNames.contains(nameInput)) {
                System.out.println(TextColor.ANSI_RED + "Name has already been entered. Please enter a different name." + TextColor.ANSI_RESET);
                i--;
                continue;
            }
            playerNames.add(nameInput);
        }
    }
    public static Card askPlayerToAddCard() {
        Card addedCard;
        Scanner scanner = new Scanner(System.in);
        do {
            String cardInput = scanner.nextLine();
            // User input x means Player don't have any Card to add
            if (cardInput.equals("x")) {
                return null;
            }
            try {
                addedCard = checkCardUserInput(cardInput);
                if (addedCard != null) {
                    break;
                }
            } catch (RuntimeException e) { // Catch all errors which occurs when user try to add Card
                System.out.println(e.getMessage());
            }
        } while (true);
        return addedCard;
    }
    private static Card checkCardUserInput(String cardInput) {
        if (!(cardInput.length() == 2 || cardInput.length() == 3)) {
            throw new IllegalCardInputLengthException("Card input is not 2 or 3 characters long");
        }

        Character suitCharacter = cardInput.charAt(0);
        String faceValueString;
        if (cardInput.length() == 3) {
            if (cardInput.charAt(1) == '1' && cardInput.charAt(2) == '0') {
                faceValueString = "10";
            } else {
                throw new IllegalCardInputException("Illegal input");
            }
        } else {
            faceValueString = String.valueOf(cardInput.charAt(1)).toUpperCase();
        }

        Suit suit = Suit.getSuitByChar(suitCharacter);
        boolean faceValueInputValid = Card.checkFaceValueInput(faceValueString);
        if (!faceValueInputValid) {
            return null;
        }
        return new Card(suit, faceValueString);
    }
}
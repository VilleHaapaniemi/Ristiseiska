package Ristiseiska.Util;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public final class GameSelections {
    private GameSelections() {}
    private static int humanPlayerCount;
    private static int AIPlayerCount;
    private static Set<String> playerNames = new LinkedHashSet<>();

    public static int getHumanPlayerCount() {
        return humanPlayerCount;
    }

    public static void setHumanPlayerCount(int humanPlayerCount) {
        GameSelections.humanPlayerCount = humanPlayerCount;
    }

    public static int getAIPlayerCount() {
        return AIPlayerCount;
    }

    public static void setAIPlayerCount(int AIPlayerCount) {
        GameSelections.AIPlayerCount = AIPlayerCount;
    }

    public static Set<String> getPlayerNames() {
        return playerNames;
    }

    public static void setPlayerNames(Set<String> playerNames) {
        GameSelections.playerNames = playerNames;
    }

    public static void askHumanPlayerCount() {
        Scanner input = new Scanner(System.in);
        int humanPlayerCountInput;

        do {
            System.out.println(TextColor.ANSI_YELLOW + "How many players are playing (1-6)" + TextColor.ANSI_RESET);
            if (input.hasNextInt()) {
                humanPlayerCountInput = input.nextInt();
                if (humanPlayerCountInput >= 1 && humanPlayerCountInput <= 6) {
                    break;
                }
            }
            System.out.println(TextColor.ANSI_RED + "Invalid input. Please enter a number between 1 and 6." + TextColor.ANSI_RESET);
            input.nextLine(); // Consume the invalid input
        } while (true);
        humanPlayerCount = humanPlayerCountInput;
    }

    public static void askAIPlayerCount() {
        if (humanPlayerCount == 6) { // Max players
            return;
        }

        Scanner input = new Scanner(System.in);
        int AIPlayerCountInput;
        int maxAIPlayersToAdd = 6 - humanPlayerCount;
        do {
            System.out.println(TextColor.ANSI_YELLOW + "How many AI players you want to add to the game? (0-"
                    + maxAIPlayersToAdd + ")?" + TextColor.ANSI_RESET);
            if (input.hasNextInt()) {
                AIPlayerCountInput = input.nextInt();
                if (AIPlayerCountInput >= 0 && AIPlayerCountInput <= maxAIPlayersToAdd) {
                    break;
                }
            }
            System.out.println(TextColor.ANSI_RED + "Invalid input. Please enter a number between 0 and "
                    + maxAIPlayersToAdd + TextColor.ANSI_RESET);
            input.nextLine(); // Consume the invalid input
        } while (true);
        AIPlayerCount = AIPlayerCountInput;
    }
    public static void askPlayerNames() {
        Scanner input = new Scanner(System.in);
        String nameInput;

        for (int i = 0; i < humanPlayerCount; i++) {
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
}

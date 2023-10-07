package Ristiseiska.Util;

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
}

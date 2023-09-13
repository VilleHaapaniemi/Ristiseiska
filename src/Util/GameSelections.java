package Util;

import java.util.Scanner;

public class GameSelections {
    private GameSelections() {}

    public static int askPlayerCount() {
        Scanner input = new Scanner(System.in);
        int playerCount;

        do {
            System.out.println(TextColor.ANSI_YELLOW + "How many players are playing (2-6)" + TextColor.ANSI_RESET);
            if (input.hasNextInt()) {
                playerCount = input.nextInt();
                if (playerCount >= 2 && playerCount <= 6) {
                    break;
                }
            }
            System.out.println(TextColor.ANSI_RED + "Invalid input. Please enter a number between 2 and 6." + TextColor.ANSI_RESET);
            input.nextLine(); // Consume the invalid input
        } while (true);

        input.close();
        return playerCount;
    }
}

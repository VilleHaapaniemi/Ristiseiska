import Util.GameInstructions;
import Util.GameSelections;

public class Main {
    public static void main(String[] args) {
        GameInstructions.start();
        GameSelections.askPlayerCount();
        GameSelections.askPlayerNames();

        while (!GameSelections.isGameFinished()) {


            GameSelections.setGameFinished(true);
        }
    }
}

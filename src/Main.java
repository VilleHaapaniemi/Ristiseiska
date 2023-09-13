import Util.GameInstructions;
import Util.GameSelections;

public class Main {
    public static void main(String[] args) {
        GameInstructions.start();
        int count = GameSelections.askPlayerCount();
        System.out.println(count);
    }
}

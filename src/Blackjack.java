import java.util.Scanner;

public class Blackjack {

    static int gameCount = 1;

    static void runGame() {
        Card.deckValues();

        System.out.print(Utilities.printLine(2));
        System.out.println("Game " + gameCount + ":\n");

        // Place bet
        Card.placeBet();

        // Initial deals
        Card.playerDeal();
        Card.dealerDeal();

        // Second move
        Card.additionalDeals();

        // Game over
        Card.printResults();
    }

    static void promptReplay() {
        Scanner scan = new Scanner(System.in);
        String userInput;

        userInput = scan.nextLine();

        if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("yes")) runGame();
        else if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("no")) Utilities.quitMessage();
        else {
            System.out.println("Invalid input");
            promptReplay();
        }
    }

    static void menu() {

        try {
            runGame();
        }
        catch (Exception except) {
            System.out.println("Fatal exception occurred..\nRestarting program.");
            runGame();
        }

        try {
            promptReplay();
        }
        catch (Exception except) {
            System.out.println("Invalid input, try again:");
            promptReplay();
        }
    }

    public static void main(String[] args) {
        Utilities.welcome();
        menu();
    }
}
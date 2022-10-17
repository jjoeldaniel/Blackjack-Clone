import java.util.Scanner;

public class Utilities {

    // Prints separator lines, 1 = normal 2 = thick
    static String printLine(int userInput) {
        String line = "undefined";
        switch (userInput) {
            case 1 -> line = "\n-------------------------------------\n";
            case 2 -> line = "\n=====================================\n";
            default -> System.out.println("Invalid input for printLine(int)");
        }
        return line;
    }

    // Prints rules
    static void printRules() {
        System.out.println(printLine(1));
        System.out.println("The premise of the game is simple, the first person to 21 wins!\nAlternatively, if no one can reach 21, the closest person will win.");
        System.out.println("Watch out though! If you go over 21, you lose!");
        System.out.println("\nThe game starts with every player being handed 2 cards by the dealer.");

        System.out.println("Special card values are as follows: \n");
        System.err.println("Ace = 11 (or 1 if bust)\nJack = 10\nQueen = 10\nKing = 10\n" + printLine(1));
    }

    // Yes or no prompt
    static void prompt() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        userInput = userInput.replaceAll("\\s+","");

        if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("yes")) {
            printRules();
        }
        else if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("no")) {
            System.out.println("\n\nThank you for playing!");
            System.exit(0);
        }
        else {
            System.out.println("Enter 'yes' or 'no' to continue: ");
            prompt();
        }
    }

    // Start message
    static void welcome() {

        System.out.println("\nWelcome to Joel's blackjack program!" + printLine(2));
        System.out.print("Would you like to see how to play?\n1) Yes\n2) No\n\n> ");

        prompt();

    }

    // Quit message
    static void quitMessage() {
        System.out.println("\nThank you for playing!\n");
        System.exit(0);
    }
}
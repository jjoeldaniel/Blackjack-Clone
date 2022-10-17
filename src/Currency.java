import java.util.Scanner;

public class Currency {

    // Variables
    static int bet = 0;
    static double balance = 500;

    // Win (Return rate of 150%)
    static void win() {
        double profit = bet * 1.5;
        System.out.println("\nYou won $" + profit + "!");
        balance += profit;
        bet = 0;

    }

    // Loss
    static void loss() {
        bet = 0;

        if (balance < 50) {
            System.out.println("\nYou went broke! Here's a free cash injection.");
            balance = 500;

        }
    }

    // Tie
    static void tie() {
        balance += bet;
        bet = 0;
    }

    // Bet options
    static void printBet() {
        Scanner scan = new Scanner(System.in);
        String option;

        option = scan.nextLine();

        switch (option) {
            case "1", "50" -> setBet(50);
            case "2", "100" -> setBet(100);
            case "3", "200" -> setBet(200);
            case "4", "300" -> setBet(300);
            case "5", "400" -> setBet(400);
            case "6", "500" -> setBet(500);
            default -> {
                System.out.println("Invalid input, try again:");
                printBet();
            }
        }

    }

    // Set bet
    static void setBet(int input) {
        bet = input;
        if (bet < 0) bet = 0;
        balance -= bet;

    }

    // Get balance
    static void getBalance() {
        System.out.println("Your current balance is $" + balance);
    }
}
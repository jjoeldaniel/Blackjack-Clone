import java.util.HashMap;
import java.util.Scanner;

public class Card {

    // Global variables
    static int cardNum = 0;
    static Integer card = 0;
    static String cardName = "undefined";
    static int dealerCardNum = 0;
    static HashMap<String, Integer> deck = new HashMap<>();
    static double totalBank = 0;

    // Holds cards and their values
    static void deckValues() {
        deck.put("Ace", 11);
        deck.put("King", 10);
        deck.put("Queen", 10);
        deck.put("Jack", 10);
        deck.put("2", 2);
        deck.put("3", 3);
        deck.put("4", 4);
        deck.put("5", 5);
        deck.put("6", 6);
        deck.put("7", 7);
        deck.put("8", 8);
        deck.put("9", 9);
        deck.put("10", 10);
    }

    // 1) Takes cardNum/dealerCardNum as input
    // 2) Takes 'player' and 'dealer' as input
    static void checkBlackjack(int cardNum, String userType) {
        if (cardNum == 21 && userType.equalsIgnoreCase("dealer")) {
            System.out.println("Blackjack! Dealer wins!");
            Currency.loss();
            Blackjack.gameCount++;
        }
        if (cardNum == 21 && userType.equalsIgnoreCase("player")) {
            System.out.println("Blackjack! You win!");
            Currency.win();
            Blackjack.gameCount++;
        }
    }

    // Assigns 'card' a random card
    static void randCard() {

        int randNum = (int)(1 + Math.random() * (13-1));
        switch (randNum) {
            case 1 -> {
                card = deck.get("Ace");
                cardName = "Ace";
                if (card + cardNum > 21) { // Checks for ace bust
                    card = 1;
                }
            }
            case 2 -> {
                card = deck.get("King");
                cardName = "King";
            }
            case 3 -> {
                card = deck.get("Queen");
                cardName = "Queen";
            }
            case 4 -> {
                card = deck.get("Jack");
                cardName = "Jack";
            }
            case 5 -> {
                card = deck.get("2");
                cardName = "2";
            }
            case 6 -> {
                card = deck.get("3");
                cardName = "3";
            }
            case 7 -> {
                card = deck.get("4");
                cardName = "4";
            }
            case 8 -> {
                card = deck.get("5");
                cardName = "5";
            }
            case 9 -> {
                card = deck.get("6");
                cardName = "6";
            }
            case 10 -> {
                card = deck.get("7");
                cardName = "7";
            }
            case 11 -> {
                card = deck.get("8");
                cardName = "8";
            }
            case 12 -> {
                card = deck.get("9");
                cardName = "9";
            }
            case 13 -> {
                card = deck.get("10");
                cardName = "10";
            }
            default -> System.out.println("Error");
        }

    }

    // Hit function
    static void hit() {
        randCard();
        cardNum += card;

        System.out.println("\nYour total is " + cardNum);

        if (cardNum > 21) printResults();

    }

    // Dealer hit
    static void dealerHit() {
        while (dealerCardNum < 17) {
            randCard();
            dealerCardNum += card;
        }
    }

    // Calculate results
    static void printResults() {

        System.out.println(Utilities.printLine(2) + "You had " + cardNum + ".");
        System.out.println("The dealer had " + dealerCardNum + ".\n");

        if (dealerCardNum > 21) { // Dealer bust
            System.out.println("Dealer busted. Congratulations, you win!");
            Currency.win();
            Blackjack.gameCount++;
        }
        else if (dealerCardNum > cardNum ) { // Loss to dealer
            System.out.println("You lost!");
            Currency.loss();
            Blackjack.gameCount++;
        }
        else if (dealerCardNum == cardNum) { // Tie
            System.out.println("You tied!");
            Currency.tie();
            Blackjack.gameCount++;
        }
        else if (cardNum > 21) { // Card bust
            System.out.println("You busted!");
            Currency.loss();
            Blackjack.gameCount++;
        }
        else {
            System.out.println("Congratulations, you won!");
            Currency.win();
            Blackjack.gameCount++;
        }
        cardNum = 0;
        dealerCardNum = 0;
        Currency.getBalance();

        System.out.print("\nPlay again?\n\n1) Yes\n2) No\n\n> ");

        // Updates balance
        totalBank = Currency.balance;

        Blackjack.promptReplay();
    }

    // Place bets
    static void placeBet() {
        System.out.println("Place your bets!\nBalance: $" + Currency.balance + "\n");
        System.out.print("1) $50\n2) $100\n3) $200\n4) $300\n5) $400\n5) $500\n\n> ");
        Currency.printBet();
    }

    // Initial deals
    static void playerDeal() {

        // User initial deal
        System.out.println("You received the following cards: ");

        randCard();
        System.out.print(cardName);
        cardNum += card;
        randCard();
        System.out.println(" and " + cardName);
        cardNum += card;

        checkBlackjack(cardNum, "player");

        System.out.println("\nYour total is " + cardNum);
    }
    static void dealerDeal() {
        randCard();
        dealerCardNum += card;
        randCard();
        dealerCardNum += card;
        checkBlackjack(dealerCardNum, "dealer");
    }

    // Additional deals
    static void additionalDeals() {
        Scanner scan = new Scanner(System.in);
        String userInput = "undefined";

        while (cardNum <= 21 && dealerCardNum < 21) {

            while (!userInput.equalsIgnoreCase("stand")) {
                // Prompt hit/stand
                System.out.println("Hit or stand?");
                userInput = scan.nextLine();
                userInput = userInput.replaceAll("\\s+","");

                if (userInput.equalsIgnoreCase("hit")) {
                    hit();
                    if (cardNum == 21) printResults();
                }
                else if (userInput.equalsIgnoreCase("stand")) {
                    System.out.println("Standing");
                    break;
                }
            }
            if (dealerCardNum <= 16) dealerHit();
            else break;
        }

    }

}
import java.util.Scanner;

class BankAccount {
    static int accountBalance = 100000;
}

public class AtmInterface extends BankAccount {

    static Scanner sc = new Scanner(System.in);

    void pin() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("\nPlease Enter your 4-digit PIN: ");

            // Fix: prevent crash if user enters non-integer
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.nextLine();
                continue;
            }

            int pin = sc.nextInt();
            sc.nextLine();

            if (pin >= 1000 && pin <= 9999) {
                System.out.println("\nPIN Accepted!");
                return;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect PIN! Attempts left: " + attempts);
                } else {
                    System.out.println("Card Blocked! Too many incorrect attempts.");
                    System.exit(0);
                }
            }
        }
    }

    void accountType() {
        while (true) {
            System.out.println("\nPlease Select Account Type:");
            System.out.println("CREDIT \nCURRENT \nSAVINGS");

            String accType = sc.nextLine();

            if (!(accType.equalsIgnoreCase("Credit") ||
                  accType.equalsIgnoreCase("Current") ||
                  accType.equalsIgnoreCase("Savings"))) {

                System.out.println("Invalid Account Type!");
            } else {
                System.out.println("Selected Account Type: " + accType.toUpperCase());
                break;
            }
        }
    }

    void withdraw() {
        int withdrawLimit = 5000;

        System.out.print("Enter the amount: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            sc.nextLine();
            return;
        }

        int amount = sc.nextInt();
        sc.nextLine();

        if (amount > withdrawLimit || amount <= 0) {
            System.out.println("Please Enter a Valid Amount (1 to ₹5000).");
            return;
        }

        pin();

        if (accountBalance < amount) {
            System.out.println("Insufficient Balance.");
            return;
        }

        accountBalance -= amount;
        System.out.println("\nTransaction Successful!");
        System.out.println("Remaining Balance: ₹" + accountBalance);
    }

    void deposit() {
        int depositLimit = 100000;

        System.out.print("Enter the amount: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            sc.nextLine();
            return;
        }

        int amount = sc.nextInt();
        sc.nextLine();

        if (amount > depositLimit || amount <= 0) {
            System.out.println("Please Enter a valid Amount (1 to 100000).");
            return;
        }

        pin();
        accountType();

        accountBalance += amount;

        System.out.println("\nTransaction Successful!");
        System.out.println("Total Balance: ₹" + accountBalance);
    }

    void balanceInquiry() {
        pin();
        accountType();

        System.out.println("\nTransaction Successful!");
        System.out.println("Available Balance: ₹" + accountBalance);
    }

    public static void main(String[] args) {

        boolean continueTransaction = true;
        AtmInterface obj = new AtmInterface();

        while (continueTransaction) {
            System.out.println("\n--- Welcome to the ATM! ---");
            System.out.println("\nTransaction Modes:");
            System.out.println("Withdraw \nDeposit \nBalance Inquiry");

            System.out.print("\nDear Customer, Please Select Transaction: ");
            String transaction = sc.nextLine();

            if (transaction.equalsIgnoreCase("Withdraw")) {
                obj.withdraw();
            } else if (transaction.equalsIgnoreCase("Deposit")) {
                obj.deposit();
            } else if (transaction.equalsIgnoreCase("Balance Inquiry")) {
                obj.balanceInquiry();
            } else {
                System.out.println("Please select a valid transaction mode.");
            }

            System.out.print("Do you want to perform another transaction? (yes/no): ");
            String again = sc.nextLine();

            if (!again.equalsIgnoreCase("Yes")) {
                continueTransaction = false;
            }
        }

        System.out.println("\nThank you for using our ATM. Have a great day!");
        sc.close();
    }
}
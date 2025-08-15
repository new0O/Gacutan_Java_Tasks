package Portfolio;
import java.util.Scanner;

class Account {
    String name;
    double balance;
    int pin;

    public Account(String name, double balance, int pin) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: ₱" + String.format("%.2f", balance));
        } else {
            System.out.println("Deposit failed: Amount must be positive.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: ₱" + String.format("%.2f", balance));
            } else {
                System.out.println("Withdrawal failed: Insufficient balance.");
            }
        } else {
            System.out.println("Withdrawal failed: Amount must be positive.");
        }
    }
}

public class BankingApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//--------------------------------------------------------------------------------------------------ACCOUNTS
        Account[] accounts = {
            new Account("Owen Tan", 69000.00,2468),
            new Account("Victor Cu", 72000.00, 1357),

        };
        Account currentUser = null; 
        boolean runProgram = true;

        while (runProgram) {
            currentUser = null; 
            boolean authenticated = false;
//--------------------------------------------------------------------------------------------LOGIN           
            while (!authenticated) {
                welcomeBanner();
                System.out.print("Please Enter your 4-digit Account PIN to login: ");
                int accountPin;
                try {
                    accountPin = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a 4-digit number.");
                    scanner.nextLine(); 
                    PressToContinue();
                    continue; 
                }
                scanner.nextLine(); 
                for (Account acc : accounts) {
                    if (acc.pin == accountPin) {
                        currentUser = acc;
                        authenticated = true;
                        System.out.println("Login successful! Welcome " + currentUser.name + ".");
                        break;
                    }
                }
                if (!authenticated) {
                    System.out.println("You have entered an INVALID PIN. Please try again or contact our support @ 87000");
                    PressToContinue();
                }
            }

            boolean signedOut = false;
            while (!signedOut) {
                printMainMenu(currentUser);
                System.out.print("Enter Number: ");
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number from the menu.");
                    scanner.nextLine(); 
                    PressToContinue();
                    continue; 
                }
                scanner.nextLine(); 
                switch (choice) {
                    case 1: 
                        System.out.println(currentUser.name + "'s Current Balance: ₱" + String.format("%.2f", currentUser.balance));
                        System.out.println();
                        break;
                    case 2: 
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = getDoubleInput();
                        if (depositAmount != -1) { 
                            currentUser.deposit(depositAmount);
                        }
                        break;
                    case 3: 
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = getDoubleInput();
                        if (withdrawAmount != -1) { 
                            currentUser.withdraw(withdrawAmount);
                        }
                        break;
                    case 4:
                        handleMoneyTransfer(currentUser, accounts);
                        break;
                    case 5: 
                        exitBanner();
                        runProgram = false;
                        signedOut = true; 
                        break;
                    case 6: 
                        System.out.println("Signed out from " + currentUser.name + "'s account.");
                        signedOut = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
        scanner.close(); 
    }
    private static void exitBanner() {
    System.out.println("               MABUHAY              ");
    System.out.println("         Thank you for using RAM       ");
    System.out.println("       Your Random Access Money bank       ");
    }

    private static void welcomeBanner() {
    System.out.println("               MABUHAY              ");
    System.out.println("          Random Access Money       ");
    System.out.println("                 Bank                  ");
}

    private static void printMainMenu(Account user) {
        System.out.println("\n----------------------------");
        System.out.println("Welcome " + user.name + " to RAM bank");
        System.out.println("----------------------------");
        System.out.println("1. Balance (₱" + String.format("%.2f", user.balance) + ")");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Money Transfer");
        System.out.println("5. Exit");
        System.out.println("6. Sign out");
        System.out.println("----------------------------------------");
    }

    private static void handleMoneyTransfer(Account sender, Account[] allAccounts) {
        System.out.println("\n--- Money Transfer ---");
        System.out.print("Enter recipient's name: ");
        String recipientName = scanner.nextLine();

        Account recipient = null;
        for (Account acc : allAccounts) {
            if (acc.name.equalsIgnoreCase(recipientName) && acc != sender) { 
                recipient = acc;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Recipient '" + recipientName + "' not found or cannot transfer to yourself.");
            return;
        }

        System.out.print("Enter amount to transfer to " + recipient.name + ": ");
        double amount = getDoubleInput();

        if (amount == -1) return; 

        if (amount <= 0) {
            System.out.println("Transfer failed: Amount must be positive.");
        } else if (sender.balance >= amount) {
            sender.withdraw(amount); 
            recipient.deposit(amount); 
            System.out.println("Successfully transferred $" + String.format("%.2f", amount) + " to " + recipient.name + ".");
        } else {
            System.out.println("Transfer failed: Insufficient balance. Your current balance is $" + String.format("%.2f", sender.balance));
        }
    }

    private static void PressToContinue() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine(); 
    }

    
    private static double getDoubleInput() {
        double value = -1;
        try {
            value = scanner.nextDouble();
            if (value < 0) {
                System.out.println("Amount cannot be negative.");
                value = -1; 
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
        } finally {
            scanner.nextLine(); 
        }
        return value;
    }
}

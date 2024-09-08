import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        System.out.println("\n********** Welcome to the Banking App **********");
        while (true) {
            // Displaying the options
            System.out.print("""
                    \n1. Check Balance
                    2. Deposit
                    3. Withdraw
                    4. Create new account
                    5. Exit
                    """);
            System.out.print("Select -> ");
            int choice = Util.scanner.nextInt();

            // Performing the action based on user choice
            switch (choice) {
                case 1:
                    System.out.println("Checking Balance...");
                    transaction.checkBalance();
                    break;

                case 2:
                    System.out.println("Ready to Deposit...");
                    transaction.makeDeposit();
                    break;

                case 3:
                    System.out.println("Ready to Withdraw...");
                    transaction.makeWithdraw();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    User.Main();
                    return;

                case 5:
                    System.out.println("Creating new account...");
                    Util.scanner.close();
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

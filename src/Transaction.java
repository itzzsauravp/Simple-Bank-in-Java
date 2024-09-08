import java.util.Scanner;

public class Transaction {
    private int loginAttempts;

    public Transaction() {
        this.loginAttempts = 1;
    }

    private String getName() {
        System.out.print("UserName: ");
        return Util.scanner.next();
    }

    private int getAmount() {
        System.out.print("Amount: ");
        return Util.scanner.nextInt();
    }

    private boolean loginValidation(String userName) {
        if (Util.checkLogin(userName)) {
            return true;
        } else {
            if (loginAttempts == 5) {
                System.out.println("No tries remaining. Exiting..");
                return false;
            }
            System.out.printf("Password Incorrect!!!\nTry Again Attempts Left: %d\n\n", 5 - loginAttempts++);
            return loginValidation(userName);  // Retry login
        }
    }

    public void checkBalance() {
        String userName = getName();
        if (loginValidation(userName)) {
            int activeBalanceAmt = Util.readFile(userName, "Balance");
            System.out.printf("\nUsername: %s, Active Balance: %d\n", userName, activeBalanceAmt);
        }
    }

    public void makeDeposit() {
        String userName = getName();
        if (loginValidation(userName)) {
            int depositAmt = getAmount();
            int activeBalanceAmt = Util.readFile(userName, "Balance");
            int finalBalanceAmt = activeBalanceAmt + depositAmt;
            Util.writeFile(userName, finalBalanceAmt);
        }
    }

    public void makeWithdraw() {
        String userName = getName();
        if (loginValidation(userName)) {
            int withdrawAmt = getAmount();
            int activeBalanceAmt = Util.readFile(userName, "Balance");
            if (withdrawAmt > activeBalanceAmt) {
                System.out.println("\nSorry!! Insufficient Funds");
            } else {
                int finalBalanceAmt = activeBalanceAmt - withdrawAmt;
                Util.writeFile(userName, finalBalanceAmt);
            }
        }
    }
}

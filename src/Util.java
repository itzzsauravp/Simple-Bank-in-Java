import java.io.*;
import java.util.Scanner;

public class Util {

    static final Scanner scanner = new Scanner(System.in);

    public static String getFilePath(String userName, String fileType) {
        return "./src/UserData/" + userName + "/" + userName + fileType + ".txt";
    }

    public static String getFolderPath(String userName) {
        return "./src/UserData/" + userName;
    }

    public static int readFile(String userName, String fileType) {
        String fileNameUserBalance = getFilePath(userName, fileType);
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameUserBalance))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }

    public static void writeFile(String userName, int amount) {
        String fileNameUserBalance = getFilePath(userName, "Balance");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameUserBalance))) {
            bw.write(String.valueOf(amount));
            System.out.println("Operation was Successful");
        } catch (IOException e) {
            System.out.println("Operation was Unsuccessful: " + e.getMessage());
        }
    }

    public static boolean checkLogin(String userName) {
        System.out.print("Password: ");
        int passwordHash = scanner.next().hashCode();  // Should use secure hashing
        int savedPasswordHash = readFile(userName, "Password");
        return passwordHash == savedPasswordHash;
    }
}

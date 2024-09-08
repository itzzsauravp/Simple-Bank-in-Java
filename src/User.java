import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class User {

    public static void Main() {
        User newUser = new User();
        newUser.createUser();
    }

    public void createPassword(String userName) {
        System.out.print("Enter Password: ");
        String password = Util.scanner.next();
        String filePath = Util.getFilePath(userName, "Password");
        try (FileWriter fwPassword = new FileWriter(filePath)) {
            fwPassword.write(String.valueOf(password.hashCode()));  // Use a secure hash in real applications
        } catch (IOException e) {
            System.out.println("Error setting the password: " + e.getMessage());
        }
    }

    // this resembles the main function here.
    public void createUser() {
        Map<String, String> userInformation = getUserData();
        makeUsersDirectory(userInformation.get("UserName"));

        try (
                FileWriter fwInfo = new FileWriter(Util.getFilePath(userInformation.get("UserName"), "Info"));
                FileWriter fwBalance = new FileWriter(Util.getFilePath(userInformation.get("UserName"), "Balance"))
        ) {
            fwInfo.write(userInformation.toString());
            fwBalance.write("0");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        createPassword(userInformation.get("UserName"));
    }

    public Map<String, String> getUserData() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> userInfoList = new LinkedHashMap<>();

        System.out.print("FirstName: ");
        userInfoList.put("FirstName", scanner.next());

        System.out.print("LastName: ");
        userInfoList.put("LastName", scanner.next());

        System.out.print("UserName: ");
        userInfoList.put("UserName", scanner.next());

        System.out.print("Email: ");
        userInfoList.put("Email", scanner.next());

        System.out.print("SourceOfIncome: ");
        userInfoList.put("SourceOfIncome", scanner.next());

        System.out.print("Age: ");
        userInfoList.put("Age", String.valueOf(scanner.nextInt()));

        System.out.print("NationalIDNumber: ");
        userInfoList.put("NationalIDNumber", String.valueOf(scanner.nextLong()));

        System.out.print("ContactNumber: ");
        userInfoList.put("ContactNumber", String.valueOf(scanner.nextLong()));

        System.out.print("MonthlySalary: ");
        userInfoList.put("MonthlySalary", String.valueOf(scanner.nextInt()));

        return userInfoList;
    }

    public void makeUsersDirectory(String userName) {
        String path = Util.getFolderPath(userName);
        File file = new File(path);
        if (!file.mkdir()) {
            System.out.println("Error creating user's directory");
        }
    }
}

import java.util.Scanner;

public class UserSignup {
    public static void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Sign up user");
        System.out.println("2. List signed-up users");
        System.out.println("3. Exit");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;

        displayMenu();

        System.out.print("Enter the option: ");
        option = input.nextInt();
aaa
        input.close();
    }
}
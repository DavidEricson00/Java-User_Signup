import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserSignup {
    static ArrayList<User> users = new ArrayList<>();

    public static void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Sign up user");
        System.out.println("2. List signed-up users");
        System.out.println("3. Exit");
    }

    public static void signUpUser(Scanner input) {
        try {
            System.out.print("Enter your name: ");
            String name = input.nextLine();
            if (name == null || name.trim().isEmpty()) {
                throw new User.InvalidNameException("Name cannot be empty or null.");
            }

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(input.nextLine());
            if (age < 0 || age > 120) {
                throw new User.InvalidAgeException("Age must be between 0 and 120.");
            }

            System.out.print("Enter your email: ");
            String email = input.nextLine();
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new User.InvalidEmailException("Invalid email format.");
            }

            User user = new User(name, age, email);
            users.add(user);
            System.out.println("\nUser signed up successfully!");
            System.out.println("  Name : " + name);
            System.out.println("  Age  : " + age);
            System.out.println("  Email: " + email + "\n");

        } catch (User.InvalidNameException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (User.InvalidAgeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (User.InvalidEmailException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid age format. Please enter a number.");
        }
    }

    public static void listUsers() {
        if (users.isEmpty()) {
            System.out.println("\nNo users have signed up yet.");
        } else {
            System.out.println("\n--- List of Signed-up Users ---");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                System.out.println((i + 1) + ". " + user.getName() + " | Age: " + user.getAge() + " | Email: " + user.getEmail());
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            try {
                displayMenu();
                System.out.print("Enter the option: ");
                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        signUpUser(input);
                        break;
                    case 2:
                        listUsers();
                        break;
                    case 3:
                        System.out.println("Exiting the system...");
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Please enter a number.");
                input.nextLine();
                option = 0;
            } finally {
                if (option != 3)
                    System.out.println("Returning to main menu...\n");
            }
        } while (option != 3);

        input.close();
    }
}

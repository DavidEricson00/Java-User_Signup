import java.util.Scanner;

public class UserSignup {
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
            System.out.println("User signed up successfully: " + user);

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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            displayMenu();
            System.out.print("Enter the option: ");
            option = input.nextInt();
            input.nextLine();
            
            switch (option) {
                case 1:
                    signUpUser(input);
                    break;
                case 2:
                    System.out.println("You chose: List signed-up users");
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (option != 3);

        input.close();
    }
}
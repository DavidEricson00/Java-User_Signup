import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class UserSignup {
    static ArrayList<User> users = new ArrayList<>();

    public static void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Sign up user");
        System.out.println("2. List signed-up users");
        System.out.println("3. Load data");
        System.out.println("4. Save data");
        System.out.println("5. Exit");
    }

    public static void signUpUser(Scanner input) 
    throws User.InvalidNameException, User.InvalidAgeException, User.InvalidEmailException, NumberFormatException {
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
	        } catch(NumberFormatException e) {
	        	throw new NumberFormatException("Invalid age format. Please enter a number.");
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
    
    public static void loadData() 
    throws IOException, User.InvalidNameException, User.InvalidAgeException, User.InvalidEmailException {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            users.clear();
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String name = userData[0];
                    int age = Integer.parseInt(userData[1]);
                    String email = userData[2];
                    User user = new User(name, age, email);
                    users.add(user);
                }
            }
            System.out.println("Users loaded from file successfully.");
        }
    }

    public static void saveData() throws IOException {
        try (FileWriter writer = new FileWriter("users.txt")) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getAge() + "," + user.getEmail() + "\n");
            }
            System.out.println("Users saved to file successfully.");
        }
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
                        try {
                            signUpUser(input);
                        } catch (User.InvalidNameException | User.InvalidAgeException | User.InvalidEmailException e) {
                            System.err.println("Error: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;
                        
                    case 2:
                        listUsers();
                        break;
                        
                    case 3:
                    	try {
                        	loadData();
                        } catch (IOException e) {
                            System.err.println("Error loading users from file: " + e.getMessage());
                        } catch (User.InvalidNameException | User.InvalidAgeException | User.InvalidEmailException e) {
                            System.err.println("Error: Invalid data in file - " + e.getMessage());
                        }
                        break;
                        
                    case 4:
                    	try {
                        	saveData();
                    	} catch (IOException e) {
                            System.err.println("Error saving users to file: " + e.getMessage());
                        }
                        break;
                        
                    case 5:
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
                if (option != 5)
                    System.out.println("Returning to main menu...\n");
            }
        } while (option != 5);

        input.close();
    }
}

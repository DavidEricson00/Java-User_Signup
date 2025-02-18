public class User {
    private String name;
    private int age;
    private String email;

    // Constructor
    public User(String name, int age, String email) throws InvalidNameException, InvalidAgeException, InvalidEmailException {
        setName(name);
        setAge(age);
        setEmail(email);
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }

    // Setters com validação
    public void setName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be empty or null.");
        }
        this.name = name;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age <= 0) {
            throw new InvalidAgeException("Age must be greater than 0.");
        }
        this.age = age;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailException("Invalid email format.");
        }
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", email=" + email + "]";
    }

    // Exceptions
    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }

    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}
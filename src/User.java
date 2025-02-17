public class User {
	private String name;
	private int age;
	private String email;
	
	// Constructor
	public User(String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
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
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//toString
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", email=" + email + "]";
	}
}

package petShop.model;

// Represents a user in the system (customer, employee, manager)
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role; // CUSTOMER, EMPLOYEE, MANAGER

    // User authentication methods
    public boolean signIn(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void signUp(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

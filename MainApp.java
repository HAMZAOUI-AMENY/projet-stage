import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your matricule:");
        int matricule = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        UserService userService = new UserService();

        if (userService.authenticateUser(matricule, password)) {
            UserSession session = UserSession.getInstance(matricule);
            String profile = userService.getUserProfile(matricule);

            System.out.println("User with matricule " + session.getMatricule() + " is logged in.");

            if ("admin".equalsIgnoreCase(profile)) {
                AdminPage adminPage = new AdminPage();
                adminPage.display();
            } else if ("user".equalsIgnoreCase(profile)) {
                UserPage userPage = new UserPage();
                userPage.display();
            } else {
                System.out.println("Unknown profile: " + profile);
            }

            session.clearSession();
            System.out.println("User session cleared.");
        } else {
            System.out.println("Authentication failed.");
        }

        scanner.close();
    }
}

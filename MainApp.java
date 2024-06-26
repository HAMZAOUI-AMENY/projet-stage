public class MainApp {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Exemple de tentative de connexion
        if (userService.authenticateUser("user1", "password1")) {
            System.out.println("User " + UserSession.getInstance().getUsername() + " is logged in.");
        } else {
            System.out.println("Authentication failed.");
        }

        // Vérifier l'utilisateur connecté
        if (UserSession.getInstance() != null) {
            System.out.println("Currently logged in user: " + UserSession.getInstance().getUsername());
        } else {
            System.out.println("No user is currently logged in.");
        }

        // Exemple de déconnexion
        UserSession.getInstance().clearSession();
        System.out.println("User session cleared.");
    }
}

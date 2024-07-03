public class UserSession {
    private static UserSession instance;
    private String username;
   

    UserSession(String username) {
        this.username = username;
        

    }

    public static UserSession getInstance(String username) {
        if (instance == null) {
            instance = new UserSession(username);
        }
        return instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }
    /*public String getprofil() {
        return profil;
    }*/
    public void clearSession() {
        instance = null;
    }
    
}

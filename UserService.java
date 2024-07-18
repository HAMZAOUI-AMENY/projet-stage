import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean authenticateUser(int matricule, String password) {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT motdepasse FROM User WHERE matricule = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matricule);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHashedPassword = rs.getString("motdepasse");
                        if (PasswordUtil.checkPassword(password, storedHashedPassword)) {
                            UserSession.getInstance(matricule); // Initialize the session with the matricule
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getUserProfile(int matricule) {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT profil FROM User WHERE matricule = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matricule);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("profil");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



    

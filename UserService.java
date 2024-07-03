import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean authenticateUser(String username, String password) {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT motdepasse FROM User WHERE nom = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHashedPassword = rs.getString("motdepasse");
                        if (PasswordUtil.checkPassword(password, storedHashedPassword)) {
                            new UserSession(username); // Initialize the session with the username
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
    public String getUsername(UserSession session) {
        return session.getUsername();
    }
}
